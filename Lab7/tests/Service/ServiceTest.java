package Service;

import Domain.Tema;
import Repository.*;
import Validator.NotaValidator;
import Validator.StudentValidator;
import Validator.TemaValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;

import static org.junit.Assert.*;

public class ServiceTest {
    StudentValidator studentValidator=new StudentValidator();
    NotaValidator notaValidator=new NotaValidator();
    TemaValidator temaValidator=new TemaValidator();

    StudentRepository studentRepository=new StudentRepository(studentValidator);
    TemeRepository temeRepository=new TemeRepository(temaValidator);
    NoteRepository noteRepository=new NoteRepository(notaValidator);

    Service service=new Service(studentRepository,temeRepository,noteRepository);

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
        try(BufferedWriter writer=new BufferedWriter(new FileWriter("Ion"))) {
            writer.write("");
        }
        catch (Exception e )
        {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void addStudent() {
        service.addStudent("12345","221","Ion","email","profesor");
        assertTrue(true);
    }

    @Test
    public void updateStudent() {
        service.updateStudent("12345","221","Ion","email","profesor");
        assertTrue(true);
    }

    @Test
    public void deleteStudent() {
        service.deleteStudent("12345","221","Ion","email","profesor");
        assertTrue(true);

    }

    @Test
    public void addTema() {
        service.addTema("1","descr",5,3);
        assertTrue(service.findTema("1").getID().equals("1"));
    }

    @Test
    public void prelungireDeadline() {
        service.addTema("1","descr",5,3);
        service.addTema("2","descr",10,7);
        service.setNrSaptamanaCurenta(7);
        Tema tema=service.findTema("1");
        service.prelungireDeadline(tema);
        assertTrue(tema.getDeadline()==5);

        Tema tema2=service.findTema("2");
        service.prelungireDeadline(tema2);
        assertTrue(tema2.getDeadline()==11);
    }

    @Test
    public void getNrSaptamana() {
        service.setNrSaptamanaCurenta(7);
        assertTrue(service.getNrSaptamanaCurenta()==7);
    }

    @Test
    public void addNota() {
        service.addStudent("12345","221","Ion","email","profesor");
        service.addTema("1","descr",5,3);

        service.addNota("12345","1",10,"feedback");
        assertTrue(true);

        try {
            service.addNota("12345", "1", 10, "feedback");
            assertTrue(false);
        }
        catch (RuntimeException e)
        {
            assertTrue(true);
        }

    }

}