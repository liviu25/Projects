package Networking.objectProtocol;

import Model.Participant;
import Model.Proba;
import Model.User;
import Service.ConcursException;
import Service.IObserver;
import Service.IServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientObjectWorker implements Runnable, IObserver {
    private IServer concursServer;
    private Socket connection;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private volatile boolean connected;

    public ClientObjectWorker(IServer concursServer, Socket connection) {
        this.concursServer = concursServer;
        this.connection = connection;
        try{
            output=new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            input=new ObjectInputStream(connection.getInputStream());
            connected=true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(connected){
            try {
                Object request=input.readObject();
                Object response=handleRequest((Request)request);
                if (response!=null){
                    sendResponse((Response) response);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            input.close();
            output.close();
            connection.close();
        } catch (IOException e) {
            System.out.println("Error "+e);
        }
    }

    private Response handleRequest(Request request) {
        Response response=null;
        if (request instanceof LoginRequest){
            System.out.println("Login request ...");
            LoginRequest logReq=(LoginRequest)request;
            User user=logReq.getUser();
            try {
                concursServer.login(user, this);
                return new OkResponse();
            } catch (ConcursException e) {
                connected=false;
                return new ErrorResponse(e.getMessage());
            }
        }
        if (request instanceof LogoutRequest){
            System.out.println("Logout request");
            LogoutRequest logReq=(LogoutRequest)request;
            User user=logReq.getUser();
            try {
                concursServer.logout(user, this);
                connected=false;
                return new OkResponse();

            } catch (ConcursException e) {
                return new ErrorResponse(e.getMessage());
            }
        }
        if(request instanceof GetProbeRequest)
        {
            System.out.println("Get Probe Request");
            GetProbeRequest getProbeRequest= (GetProbeRequest) request;
            try{
                Iterable<Proba> probe=concursServer.getProbeAndNrParticipanti();
                return new GetProbeResponse(probe);
            } catch (ConcursException e) {
                return new ErrorResponse(e.getMessage());
            }
        }
        if(request instanceof GetParticipantiRequest)
        {
            System.out.println("Get Participanti Request");
            GetParticipantiRequest getParticipantiRequest= (GetParticipantiRequest) request;
            try{
                Iterable<Participant> participants=concursServer.getParticipantiByProba(getParticipantiRequest.getIdProba());
                return new GetParticipantiResponse(participants);
            } catch (ConcursException e) {
                return new ErrorResponse(e.getMessage());
            }
        }
        if(request instanceof InscriereRequest)
        {
            System.out.println("Inscriere Request");
            InscriereRequest inscriereRequest= (InscriereRequest) request;
            try{
                Participant participant = inscriereRequest.getInscriereDTO().getParticipant();
                String tipProba1 = inscriereRequest.getInscriereDTO().getTipProba1();
                String tipProba2 = inscriereRequest.getInscriereDTO().getTipProba2();
                concursServer.inscriereParticipant(participant,tipProba1,tipProba2);
                return new OkResponse();
            } catch (ConcursException e) {
                return new ErrorResponse(e.getMessage());
            }
        }
        return response;
    }

    private void sendResponse(Response response) throws IOException{
        System.out.println("sending response "+response);
        output.writeObject(response);
        output.flush();
    }

    @Override
    public void probeUpdated() throws ConcursException {
        System.out.println("Probe Updated");
        try {
            sendResponse(new ProbeUpdatedResponse());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
