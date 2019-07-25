package Networking.objectProtocol;

import Model.Participant;
import Model.Proba;
import Model.User;
import Networking.dto.InscriereDTO;
import Service.ConcursException;
import Service.IObserver;
import Service.IServer;
import javafx.util.Pair;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ServerObjectProxy implements IServer {
    private String host;
    private int port;

    private IObserver client;

    private ObjectInputStream input;
    private ObjectOutputStream output;
    private Socket connection;

    private BlockingQueue<Response> qresponses;
    private volatile boolean finished;
    public ServerObjectProxy(String host, int port) {
        this.host = host;
        this.port = port;
        qresponses=new LinkedBlockingDeque<>();
    }
    @Override
    public void login(User user, IObserver client) throws ConcursException {
        initializeConnection();
        sendRequest(new LoginRequest(user));
        Response response=readResponse();
        if (response instanceof OkResponse){
            this.client=client;
            return;
        }
        if (response instanceof ErrorResponse){
            ErrorResponse err=(ErrorResponse)response;
            closeConnection();
            throw new ConcursException(err.getMessage());
        }
    }

    @Override
    public void logout(User user, IObserver client) throws ConcursException {
        sendRequest(new LogoutRequest(user));
        Response response=readResponse();
        closeConnection();
        if (response instanceof ErrorResponse){
            ErrorResponse err=(ErrorResponse)response;
            throw new ConcursException(err.getMessage());
        }
    }

    @Override
    public Iterable<Proba> getProbeAndNrParticipanti() throws ConcursException {

        sendRequest(new GetProbeRequest());
        Response response=readResponse();
        if (response instanceof ErrorResponse){
            ErrorResponse err=(ErrorResponse)response;
            closeConnection();
            throw new ConcursException(err.getMessage());
        }
        GetProbeResponse getProbeResponse= (GetProbeResponse) response;
        return getProbeResponse.getProbe();
    }

    @Override
    public Iterable<Participant> getParticipantiByProba(Integer idProba) throws ConcursException {
        sendRequest(new GetParticipantiRequest(idProba));
        Response response=readResponse();
        if (response instanceof ErrorResponse){
            ErrorResponse err=(ErrorResponse)response;
            closeConnection();
            throw new ConcursException(err.getMessage());
        }
        GetParticipantiResponse getParticipantiResponse= (GetParticipantiResponse) response;
        return getParticipantiResponse.getParticipants();
    }

    @Override
    public void inscriereParticipant(Participant participant, String tipProba1, String tipProba2) throws ConcursException {
        InscriereDTO inscriereDTO=new InscriereDTO(participant,tipProba1,tipProba2);
        sendRequest(new InscriereRequest(inscriereDTO));
        Response response=readResponse();
        if (response instanceof ErrorResponse){
            ErrorResponse err=(ErrorResponse)response;
            closeConnection();
            throw new ConcursException(err.getMessage());
        }
        if(response instanceof OkResponse)
        {
            return;
        }
    }

    private void closeConnection() {
        finished=true;
        try {
            input.close();
            output.close();
            connection.close();
            client=null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendRequest(Request request)throws ConcursException {
        try {
            output.writeObject(request);
            output.flush();
        } catch (IOException e) {
            throw new ConcursException("Error sending object "+e);
        }

    }

    private Response readResponse() {
        Response response=null;
        try{

            response=qresponses.take();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }
    private void initializeConnection() {
        try {
            connection=new Socket(host,port);
            output=new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            input=new ObjectInputStream(connection.getInputStream());
            finished=false;
            startReader();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startReader(){
        Thread tw=new Thread(new ReaderThread());
        tw.start();
    }

    private void handleUpdate(UpdateResponse update)
    {
        if(update instanceof ProbeUpdatedResponse)
        {

            try {
                client.probeUpdated();
            } catch (ConcursException e) {
                e.printStackTrace();
            }
        }
    }

    private class ReaderThread implements Runnable{
        public void run() {
            while(!finished){
                try {
                    Object response=input.readObject();
                    System.out.println("response received "+response);
                    if (response instanceof UpdateResponse){
                        handleUpdate((UpdateResponse)response);
                    }
                    else{
                        try {
                            qresponses.put((Response)response);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                } catch (IOException e) {
                    System.out.println("Reading error "+e);
                } catch (ClassNotFoundException e) {
                    System.out.println("Reading error "+e);
                }
            }
        }
    }

}
