import Domain.Nota;
import Domain.Student;
import Domain.Tema;
import Repository.NoteRepository;
import Validator.NotaValidator;
import Validator.ValidationException;
import org.junit.Test;

import static org.junit.Assert.*;

public class NoteRepositoryTest {
    @Test
    public void Test() {

        NotaValidator validator=new NotaValidator();
        NoteRepository repo=new NoteRepository(validator);
        Nota nota = new Nota(" "," ",1);
        try
        {
            repo.save(nota);
            assert (false);
        }
        catch (ValidationException e)
        {
            assertTrue(true);
        }
    }
}