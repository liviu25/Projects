package practic.repository;

import org.hibernate.SessionFactory;
import practic.model.Game;

public class GameRepository extends AbstractRepository<Game> {
    public GameRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
        setClazz(Game.class);
    }
}
