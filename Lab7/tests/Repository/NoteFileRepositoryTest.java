package Repository;

import Domain.Nota;
import Validator.NotaValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NoteFileRepositoryTest {
    NotaValidator validator;
    NoteFileRepository repository;
    Nota nota=new Nota("123","1",10);
    @Before
    public void setUp() throws Exception {
        validator=new NotaValidator();
        repository=new NoteFileRepository(validator,"testNote.txt");
    }

    @After
    public void tearDown() throws Exception {
        repository.deleteAll();
    }

    @Test
    public void save() {
        repository.save(nota);
        assertTrue(repository.size()==1);
    }

    @Test
    public void update() {
        repository.update(nota);
        assertTrue(repository.size()==1);
    }

    @Test
    public void delete() {
        repository.delete(nota.getID());
        assertTrue(repository.size()==0);
    }
}