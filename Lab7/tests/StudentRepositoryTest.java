import Domain.Student;
import Repository.StudentRepository;
import Validator.StudentValidator;
import Validator.ValidationException;
import org.junit.Test;

import static org.junit.Assert.*;

public class StudentRepositoryTest {
    @Test
    public void testRepo() {
        StudentValidator v=new StudentValidator();
        StudentRepository repo=new StudentRepository(v);
        Student s=new Student("12345","221","Ion","email","profesor");
        Student s2=new Student("54321","222","Bogdan","email2","profesor2");
        Student s3=new Student(" ","222","Bogdan","email2","profesor2");;
        repo.save(s);
        s=repo.delete("12345");
        assert(s.getIdStudent().equals("12345"));
        repo.delete("2");
        repo.save(s);
        repo.update(s);
        repo.update(s2);
        try
        {
            repo.save(s3);
        }
        catch (ValidationException e)
        {
            assertTrue(true);
        }
        try
        {
            repo.update(s3);
        }
        catch (ValidationException e)
        {
            assertTrue(true);
        }

        Iterable<Student> iterable=repo.findAll();

        assertTrue(repo.size()==2);
    }
}