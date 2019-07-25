import Domain.Tema;
import org.junit.Test;

import static org.junit.Assert.*;

public class TemaTest {
    Tema tema=new Tema("1","d",2,1);
    @Test
    public void setId() {
        tema.setId("1");
        assertTrue(tema.getId()=="1");
        assertTrue(tema.toString()!=" ");
    }

    @Test
    public void setDescriere() {
        tema.setDescriere("d");
        assertTrue(tema.getDescriere()=="d");
    }

    @Test
    public void setDeadline() {
        tema.setDeadline(2);
        assertTrue(tema.getDeadline()==2);
    }

    @Test
    public void setNrSaptamanaPrimire() {
        tema.setNrSaptamanaPrimire(1);
        assertTrue(tema.getNrSaptamanaPrimire()==1);
    }

    @Test
    public void setID() {
        tema.setID("1");
        assertTrue(tema.getID()=="1");
    }
}