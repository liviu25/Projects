package concurs.repository;


import concurs.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginRepository implements JRepository<User,String> {

    private SessionFactory sessionFactory;

    public LoginRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public User findOne(String s) {
        return null;
    }

    public User verifUser(User user)
    {
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();

                User user1=session.get(User.class,user.getID());

                tx.commit();
                if(!user1.getPassword().equals(user.getPassword()))
                    return null;
                return user;
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
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
