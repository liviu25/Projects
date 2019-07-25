import Model.Pizza;
import Repository.RepositoryDB;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class Main {
    //private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) {

        RepositoryDB repositoryDB=new RepositoryDB();
        Pizza pizza=new Pizza(3,"Salami",11);
        repositoryDB.insert(pizza);
        //repositoryDB.delete(1);
        for (Pizza p : repositoryDB.findAll()) {
            System.out.println(p);
        }

    }
}
