package Validator;

public class ValidationException extends RuntimeException{
    /**
     *
     * @param message mesajul exceptiei
     */
    public ValidationException(String message) {
        super(message);
    }

    /**
     * contructor fara parametrii
     */
    public ValidationException() {
    }
}
