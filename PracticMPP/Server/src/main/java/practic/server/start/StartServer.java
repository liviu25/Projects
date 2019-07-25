package practic.server.start;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class StartServer {

    public static void main(String[] args) {
        ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring-server.xml");



//        JocRepo jocRepo = (JocRepo) factory.getBean("jocRepo");

//        Joc joc=new Joc();
//        int[][] board=new int[3][3];
//        board[0][0]=1;
//        joc.setBoard(board);
//        jocRepo.save(joc);

//        for (Joc joc : jocRepo.findAll()) {
//            System.out.println(Arrays.deepToString(joc.getBoard()));
//        }
    }
}
