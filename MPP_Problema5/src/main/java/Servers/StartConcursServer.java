package Servers;

import Model.Participant;
import Model.Proba;
import Networking.utils.AbstractServer;
import Networking.utils.ObjectConcurrentServer;
import Networking.utils.ServerException;
import Repository.InscriereDbRepository;
import Repository.LoginRepository;
import Repository.ParticipantDbRepository;
import Repository.ProbaDbRepository;

import java.io.*;
import java.util.Properties;

public class StartConcursServer {
    private static int defaultPort=55555;
    public static void main(String[] args) throws IOException, ServerException {
        Properties props=new Properties();
        props.load(new FileReader("bd.config"));
        LoginRepository loginRepository=new LoginRepository(props);
        ParticipantDbRepository participantDbRepository = new ParticipantDbRepository(props);
        ProbaDbRepository probaDbRepository=new ProbaDbRepository(props);
        InscriereDbRepository inscriereDbRepository=new InscriereDbRepository(props);
        ConcursServer concursServer =new ConcursServer(loginRepository,participantDbRepository,probaDbRepository,inscriereDbRepository);


        AbstractServer server=new ObjectConcurrentServer(defaultPort,concursServer);
        server.start();


    }


}
