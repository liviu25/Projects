package Repository;

public class RepositoryException extends RuntimeException{
    /**
     *
     * @param message mesajul exceptiei
     */
    public RepositoryException(String message) {
        super(message);
    }

    /**
     * contructor fara parametrii
     */
    public RepositoryException() {
    }
}
