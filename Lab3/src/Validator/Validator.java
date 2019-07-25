package Validator;

public interface Validator<E> {
    /**
     *
     * @param e elemntul care in validam
     * @return un mesaj cu erori
     */
    void validate(E e) throws ValidationException;
}
