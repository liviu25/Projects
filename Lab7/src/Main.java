import Repository.*;
import Service.Service;
import UI.UI;
import Validator.NotaValidator;
import Validator.StudentValidator;
import Validator.TemaValidator;
import Validator.ValidationException;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ValidationException {
	// write your code here
        StudentValidator studentValidator=new StudentValidator();
        NotaValidator notaValidator=new NotaValidator();
        TemaValidator temaValidator=new TemaValidator();

        StudentFileRepository studentRepository=new StudentFileRepository(studentValidator,"Studenti.txt");
        TemeFileRepository temeRepository=new TemeFileRepository(temaValidator,"Teme.txt");
        //NoteFileRepository noteRepository=new NoteFileRepository(notaValidator,"C:\\Users\\bud_l\\IdeaProjects\\Lab3\\Note.txt");
        NoteXMLRepository noteRepository=new NoteXMLRepository(notaValidator,"Note.xml");

        Service service=new Service(studentRepository,temeRepository,noteRepository);


        UI ui = new UI(service);
        //ui.ConsoleUI();



        service.filterGrupaTema("221","3").forEach(x-> System.out.println(x));

        //service.getAllStudents().forEach(x-> System.out.println(x));

    }
}
