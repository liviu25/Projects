package Repository;

import Domain.Tema;
import Validator.Validator;

public class TemeRepository extends AbstractJRepository<Tema,String>{
    /**
     * constructor
     * @param v validator pentru tema
     */
    public TemeRepository(Validator<Tema> v) {
        super(v);
    }
}
