package Repository;

import Model.Participant;
import Model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class LoginRepository implements JRepository<User,String> {

    private JdbcUtils dbUtils;

    private static final Logger logger= LogManager.getLogger();

    public LoginRepository(Properties props) {
        this.dbUtils =new JdbcUtils(props);
    }

    @Override
    public User findOne(String s) {
        return null;
    }

    public User verifUser(User user)
    {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        //List<Participant> all=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from Users where ID=? and Password=?")) {
            preStmt.setString(1,user.getID());
            preStmt.setString(2,user.getPassword());
            try(ResultSet result=preStmt.executeQuery()) {
                if (result.next()) {
                    User user1 = getUser(result);
                    logger.traceExit(user);
                    return user1;
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error DB "+e);
        }
        finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        logger.traceExit(null);
        return null;
    }

    private User getUser(ResultSet result) throws SQLException {
        String id = result.getString(1);
        String pass=result.getString(2);
        return new User(id,pass);
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public void delete(String s) {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterable<User> findAll() {
        return null;
    }
}
