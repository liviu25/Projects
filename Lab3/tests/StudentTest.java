import Domain.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StudentTest {
    Student s=new Student("12345","221","Ion","email","profesor");

    @Test
    public void toSString() {
        assertTrue(s.toString().equals("12345 221 Ion email profesor"));
    }

    @Test
    public void setID() {
        s.setID("12345");
        assertTrue(s.getID()=="12345");
    }

    @Test
    public void setIdStudent() {
        s.setIdStudent("12345");
        assertTrue(s.getIdStudent()=="12345");
    }

    @Test
    public void setGrupa() {
        s.setGrupa("221");
        assertTrue(s.getGrupa()=="221");
    }

    @Test
    public void setNume() {
        s.setNume("Ion");
        assertTrue(s.getNume()=="Ion");
    }

    @Test
    public void setEmail() {
        s.setEmail("email");
        assertTrue(s.getEmail()=="email");
    }

    @Test
    public void setProfesor() {
        s.setProfesor("profesor");
        assertTrue(s.getProfesor()=="profesor");
    }
}