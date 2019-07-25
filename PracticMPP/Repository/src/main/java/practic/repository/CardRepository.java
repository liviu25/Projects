package practic.repository;

import org.hibernate.SessionFactory;
import practic.model.Card;

public class CardRepository extends AbstractRepository<Card> {
    public CardRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
        setClazz(Card.class);
    }
}
