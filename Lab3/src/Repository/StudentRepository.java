package Repository;

import Domain.Student;
import Validator.Validator;

public class StudentRepository extends AbstractJRepository<Student,String> {
    /**
     * constructor
     * @param v validator pentru student
     */
    public StudentRepository(Validator<Student> v) {
        super(v);
    }
}
