import Domain.Nota;
import Domain.Student;
import Domain.Tema;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class NotaTest {
    Nota nota=new Nota("123","1",10);
    Nota nota2=new Nota("12-2","12","2",10,LocalDateTime.now());
    @Test
    public void setID() {
        nota.setID("123-1");
        assertTrue(nota.getID().equals("123-1"));
    }

    @Test
    public void setIDStudent() {
        nota.setIdStudent("123");
        assertTrue(nota.getIdStudent().equals("123"));
    }

    @Test
    public void setIDTema() {
        nota.setIdTema("1");
        assertTrue(nota.getIdTema().equals("1"));
    }

    @Test
    public void setValoare() {
        nota.setValoare(9);
        assertTrue(nota.getValoare()==9);
    }

    @Test
    public void setData() {
        nota.setData(LocalDateTime.of(2018,11,13,0,0));
        assertTrue(nota.getData().getYear()==2018);
    }

    @Test
    public void setIdNota() {
        nota2.setIdNota("12-2");
        assertTrue(nota2.getIdNota().equals("12-2"));
    }
}