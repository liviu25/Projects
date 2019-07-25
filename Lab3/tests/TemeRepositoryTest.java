import Domain.Tema;
import Repository.TemeRepository;
import Validator.TemaValidator;
import Validator.ValidationException;
import org.junit.Test;

import static org.junit.Assert.*;

public class TemeRepositoryTest {
    @Test
    public void Test() {
        TemaValidator validator=new TemaValidator();
        TemeRepository repo=new TemeRepository(validator);
        Tema tema=new Tema(" ","d",2,1);
        try
        {
            repo.save(tema);
        }
        catch (ValidationException e)
        {
            assertTrue(true);
        }
    }

}