package Networking.rpcProtocol;

import Model.Participant;
import Model.Proba;
import Model.User;
import Networking.objectProtocol.*;
import Service.ConcursException;
import Service.IObserver;
import Service.IServer;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ClientRpcWorker implements Runnable, IObserver {
    private IServer concursServer;
    private Socket connection;
    private BufferedReader input;
    private PrintWriter output;
    private volatile boolean connected;

    public ClientRpcWorker(IServer concursServer, Socket connection) {
        this.concursServer = concursServer;
        this.connection = connection;
        try{
            output=new PrintWriter(new OutputStreamWriter(connection.getOutputStream()) {
            });
            output.flush();
            input=new BufferedReader(new InputStreamReader( connection.getInputStream()));
            connected=true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(connected){
            try {
                Object request=input.readLine();
                handleRequest(request.toString());

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

    private void handleRequest(String request) throws IOException, ClassNotFoundException {

        if (request.equals("LoginRequest")){
            System.out.println("Login request ...");

            Object id=input.readLine();
            Object password=input.readLine();
            User user=new User(id.toString(),password.toString());
            try {
                concursServer.login(user, this);
                sendResponse("OkResponse");
            } catch (ConcursException e) {
                connected=false;
                sendResponse("OkResponse");
            }
        }
        if (request.equals("LogoutRequest")){
            System.out.println("Logout request");
            Object id=input.readLine();
            Object password=input.readLine();
            User user=new User(id.toString(),password.toString());
            try {
                concursServer.logout(user, this);
                connected=false;
                sendResponse("OkResponse");

            } catch (ConcursException e) {
                sendResponse("ErrorResponse");
            }
        }
        if(request.equals("GetProbeRequest"))
        {
            System.out.println("Get Probe Request");

            try{
                Iterable<Proba> probe=concursServer.getProbeAndNrParticipanti();
                sendResponse("GetProbeResponse");
                int size=0;
                for (Proba proba : probe) {
                    size++;
                }
                sendResponse(String.valueOf(size));
                for (Proba proba : probe) {
                    sendResponse(proba.getID().toString());
                    sendResponse(proba.getTipProba().toString());
                    sendResponse(proba.getVarstaMin().toString());
                    sendResponse(proba.getVarstaMax().toString());
                    sendResponse(proba.getNrParticipanti().toString());
                }
            } catch (ConcursException e) {
                sendResponse("ErrorResponse");
            }
        }
        if(request.equals("GetParticipantiRequest"))
        {
            System.out.println("Get Participanti Request");
            Object idProba=input.readLine();
            try{
                Iterable<Participant> participants=concursServer.getParticipantiByProba(Integer.valueOf(idProba.toString()));
                sendResponse("GetParticipantiResponse");
                int size=0;
                for (Participant participant : participants) {
                    size++;
                }

                sendResponse(String.valueOf(size));
                for (Participant participant : participants) {
                    System.out.println(participant);
                    sendResponse(participant.getID().toString());
                    sendResponse(participant.getNume().toString());
                    sendResponse(participant.getPrenume().toString());
                    sendResponse(participant.getVarsta().toString());
                }
            } catch (ConcursException e) {
                sendResponse("ErrorResponse");
            }
        }
        if(request.equals("InscriereRequest"))
        {
            System.out.println("Inscriere Request");
            try{
                Integer id= Integer.valueOf(input.readLine());
                String nume=input.readLine();
                String prenume=input.readLine();
                Integer varsta= Integer.valueOf(input.readLine());
                Participant participant=new Participant(id,nume,prenume,varsta);
                String tipProba1=input.readLine();
                String tipProba2= input.readLine();
                System.out.println(tipProba1);
                System.out.println(tipProba2);
                concursServer.inscriereParticipant(participant,tipProba1,tipProba2);
                sendResponse("OkResponse");
            } catch (ConcursException e) {
                sendResponse("ErrorResponse");
            }
        }


    }

    private void sendResponse(String response) throws IOException{
        System.out.println("sending response "+response);

        output.println(response);
        output.flush();
    }

    @Override
    public void probeUpdated() throws ConcursException {
        System.out.println("Probe Updated");
        try {
            sendResponse("ProbeUpdatedResponse");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
