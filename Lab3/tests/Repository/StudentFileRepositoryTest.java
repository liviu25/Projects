package Repository;

import Domain.Student;
import Validator.StudentValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class StudentFileRepositoryTest {
    StudentValidator studentValidator;
    StudentFileRepository studentFileRepository;
    @Before
    public void setUp() throws Exception {
        studentValidator=new StudentValidator();
        studentFileRepository =new StudentFileRepository(studentValidator,"testStudenti.txt");

    }

    @After
    public void tearDown() throws Exception {
        studentFileRepository.deleteAll();
    }

    @Test
    public void save() {
        Student s=new Student("12345","221","Ion","email","profesor");
        studentFileRepository.save(s);
        assertTrue(studentFileRepository.size()==1);
    }

    @Test
    public void update() {
        Student s=new Student("12346","221","Ion","email","profesor");
        studentFileRepository.update(s);
        assertTrue(studentFileRepository.size()==1);
    }

    @Test
    public void delete() {
        Student s=new Student("12346","221","Ion","email","profesor");
        studentFileRepository.delete(s.getIdStudent());
        assertTrue(studentFileRepository.size()==0);
    }
}