package Service;

public class ConcursException extends Exception {
    public ConcursException() {
        super();
    }

    public ConcursException(String message) {
        super(message);
    }

    public ConcursException(String message, Throwable cause) {
        super(message, cause);
    }
}
