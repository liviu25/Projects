package Repository;

import Domain.Tema;
import Validator.TemaValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TemeFileRepositoryTest {
    TemaValidator validator;
    TemeFileRepository repository;
    @Before
    public void setUp() throws Exception {
        validator= new TemaValidator();
        repository=new TemeFileRepository(validator,"testTeme.txt");
    }

    @After
    public void tearDown() throws Exception {
        repository.deleteAll();
    }

    @Test
    public void save() {
        Tema tema=new Tema("1","descr",5,3);
        repository.save(tema);
        assert (repository.size()==1);
    }

    @Test
    public void update() {
        Tema tema=new Tema("2","descr",5,3);
        repository.update(tema);
        assert (repository.size()==1);
    }

    @Test
    public void delete() {
        Tema tema=new Tema("2","descr",5,3);
        repository.delete(tema.getID());
        assert (repository.size()==0);
    }
}