package practic.repository;

import org.hibernate.SessionFactory;
import practic.model.Player;

public class PlayerRepository extends AbstractRepository<Player>{
    public PlayerRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
        setClazz(Player.class);
    }
}
