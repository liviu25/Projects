package Repository;

import Domain.Nota;
import Repository.AbstractJRepository;
import Validator.Validator;

public class NoteRepository extends AbstractJRepository<Nota,String> {
    /**
     * constructor
     * @param v validator pentru nota
     */
    public NoteRepository(Validator<Nota> v) {
        super(v);
    }
}
