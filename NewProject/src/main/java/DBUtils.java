import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
    static final String dbURl= "jdbc:mysql://localhost:3306/footballdb?serverTimezone=UTC";
    static final String username= "root";
    static final String password= "root";

    static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbURl,username,password);
    }
}
