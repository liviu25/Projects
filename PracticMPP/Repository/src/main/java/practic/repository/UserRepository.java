package practic.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import practic.model.User;


public class UserRepository extends AbstractRepository<User> {
    public UserRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.setClazz(User.class);
    }

    public User findOne(String username){
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                User entity=session.get(User.class,username);

                tx.commit();
                return entity;
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
        return null;
    }
}
