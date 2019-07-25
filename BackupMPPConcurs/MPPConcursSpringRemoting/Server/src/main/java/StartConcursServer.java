import concurs.model.Proba;
import concurs.model.User;
import concurs.repository.InscriereDbRepository;
import concurs.repository.LoginRepository;
import concurs.repository.ParticipantDbRepository;
import concurs.repository.ProbaDbRepository;
import concurs.server.ConcursServer;
import concurs.service.ConcursException;
import concurs.service.IServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Properties;

public class StartConcursServer {
    private static int defaultPort=55555;
    public static void main(String[] args) throws IOException, ConcursException {


        ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring-server.xml");



    }
}
