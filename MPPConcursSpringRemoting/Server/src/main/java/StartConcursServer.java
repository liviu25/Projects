import concurs.service.ConcursException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;

public class StartConcursServer {
    private static int defaultPort=55555;
    public static void main(String[] args) throws IOException, ConcursException {


        ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring-server.xml");

    }
}
