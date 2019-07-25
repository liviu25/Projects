package Service;

import static java.lang.Math.toIntExact;

import Domain.Nota;
import Domain.Student;
import Domain.Tema;
import Repository.*;
import Validator.ValidationException;
import javafx.print.Collation;
import utils.NotaEvent;
import utils.Observable;
import utils.Observer;
import utils.STEType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Service implements Observable<NotaEvent> {


    private int nrSaptamanaCurenta;
    private StudentRepository studentRepository;
    private TemeRepository temeRepository;
    private NoteRepository noteRepository;

    public int getNrSaptamanaCurenta()
    {
        return nrSaptamanaCurenta;
    }
    public void setNrSaptamanaCurenta(int s)
    {
        nrSaptamanaCurenta=s;
    }
    /**
     * constructor
     * @param studentRepository repository pentru studenti
     * @param temeRepository repository pentru teme
     * @param noteRepository repository pentru note
     */
    public Service(StudentRepository studentRepository, TemeRepository temeRepository, NoteRepository noteRepository) {
        this.studentRepository = studentRepository;
        this.temeRepository = temeRepository;
        this.noteRepository = noteRepository;

        LocalDateTime primaSaptamana=LocalDateTime.of(2018,10,1,0,0);
        LocalDateTime saptamanaCurenta=LocalDateTime.now();
        long nrsapt= Duration.between(primaSaptamana,saptamanaCurenta).toDays()/7;
        nrSaptamanaCurenta=Math.toIntExact(nrsapt)+1;
    }



    /**
     * adauga un student
     * @param idStudent String id-ul studentului
     * @param grupa String
     * @param nume String
     * @param email String
     * @param profesor String
     */
    public void addStudent(String idStudent,String grupa,String nume,String email,String profesor) throws ValidationException
    {
        Student s=new Student(idStudent,grupa,nume,email,profesor);
        studentRepository.save(s);
    }

    /**
     * actualizeaza un student
     * @param idStudent String
     * @param grupa String
     * @param nume String
     * @param email String
     * @param profesor String
     */
    public void updateStudent(String idStudent,String grupa,String nume,String email,String profesor)
    {
        Student s=new Student(idStudent,grupa,nume,email,profesor);
        studentRepository.update(s);
    }

    /**
     * sterge un student
     * @param idStudent String
     * @param grupa String
     * @param nume String
     * @param email String
     * @param profesor String
     */
    public void deleteStudent(String idStudent,String grupa,String nume,String email,String profesor)
    {
        Student s=new Student(idStudent,grupa,nume,email,profesor);
        studentRepository.delete(idStudent);
    }

    /**
     * adauga o tema
     * @param id String
     * @param descriere String
     * @param deadline int
     * @param nrSaptamanaPrimire int
     */
    public void addTema(String id, String descriere, int deadline, int nrSaptamanaPrimire)
    {
        Tema tema=new Tema(id,descriere,deadline,nrSaptamanaPrimire);
        temeRepository.save(tema);
    }

    /**
     * cauta o tema dupa id
     * @param id String
     * @return tema
     */
    public Tema findTema(String id)
    {
        Tema tema =temeRepository.findOne(id);
        return tema;
    }

    /**
     * prelungeste deadline-ul cu o saptamana
     * @param tema Tema
     */
    public boolean prelungireDeadline(Tema tema)
    {
        if(nrSaptamanaCurenta<=tema.getDeadline())
        {
            tema.setDeadline(tema.getDeadline()+1);
            temeRepository.update(tema);
            return true;
        }
        return false;
    }


    private void saveNotaToFile(String nume,Nota nota,String feedback)
    {
        String fileN=nume+".txt";
        try(PrintWriter writer=new PrintWriter(new FileWriter(fileN,true)))
        {
            Tema tema=temeRepository.findOne(nota.getIdTema());
            writer.append("Tema: "+tema.getID()+"\n");
            writer.append("Nota: "+nota.getValoare()+"\n");
            writer.append("Predata in saptamana: "+nrSaptamanaCurenta+"\n");
            writer.append("Deadline: "+tema.getDeadline()+"\n");
            writer.append("Feedback: "+feedback+"\n");
            writer.append("\n");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void addNota(String idStudent, String idTema, int valoare,String feedback)
    {

        Nota nota = new Nota(idStudent, idTema, valoare);

        //if(studentRepository.findOne(idStudent)!=null && temeRepository.findOne(idTema)!=null && noteRepository.findOne(nota.getID())==null  ) {


            Student student = studentRepository.findOne(idStudent);
            Tema tema = temeRepository.findOne(idTema);
            int penalizare=0,val=0;
            if(nrSaptamanaCurenta>tema.getDeadline())
                penalizare=nrSaptamanaCurenta-tema.getDeadline();
            if(penalizare>2)
                nota.setValoare(1);
            else {
                val=nota.getValoare()-penalizare*2;
                if(val<1)
                    nota.setValoare(1);
                else
                    nota.setValoare(val);
            }

            noteRepository.save(nota);
            saveNotaToFile(student.getNume(), nota, feedback);
        notifyObservers(new NotaEvent(STEType.ADD,nota));

        //}
        //else
           // throw new RuntimeException("Studentul are nota la aceasta tema");
    }

    public Iterable<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Iterable<Tema> getTeme() {
        return temeRepository.findAll();
    }

    public Iterable<Nota> getNote() {
        return noteRepository.findAll();
    }

    public Student findStudent(String id) {
        return studentRepository.findOne(id);
    }

    public List<Nota> filterNotaTema(String idTema)
    {
        Iterable<Nota> iterable=noteRepository.findAll();
        List<Nota> lista=new ArrayList<Nota>();
        iterable.forEach(x->lista.add(x));
        iterable.forEach(x-> System.out.println(x));
        Map<String, List<Nota>> notaTema= lista.stream().collect(Collectors.groupingBy(Nota::getIdTema,Collectors.toList()));

        List<Nota> rez=new ArrayList<Nota>();

        for(Map.Entry<String,List<Nota>> entry: notaTema.entrySet())
        {
            if(entry.getKey().equals(idTema))
            //System.out.println(entry.getKey());
            {
                for(Nota nota:entry.getValue())
                {
                    rez.add(nota);
                    //System.out.println(nota);
                }
            }

        }
        return rez;
    }

    public List<Nota> filterStudentTema(String idStudent)
    {
        Iterable<Nota> iterable=noteRepository.findAll();
        List<Nota> lista=new ArrayList<Nota>();
        iterable.forEach(x->lista.add(x));

        Map<String, List<Nota>> studentNote= lista.stream().collect(Collectors.groupingBy(Nota::getIdStudent,Collectors.toList()));

        List<Nota> rez=new ArrayList<Nota>();

        for(Map.Entry<String,List<Nota>> entry: studentNote.entrySet())
        {
            if(entry.getKey().equals(idStudent))
            //System.out.println(entry.getKey());
            {
                for(Nota nota:entry.getValue())
                {
                    rez.add(nota);
                    //System.out.println(nota);
                }
            }

        }
        return rez;
    }
    public List<Nota> filterGrupaTema(String grupa,String idTema)
    {

        List<Nota> lista=new ArrayList<Nota>();
        noteRepository.findAll().forEach(x->lista.add(x));

        Map<String, List<Nota>> studentNote= lista.stream().collect(Collectors.groupingBy(Nota::getIdTema,Collectors.toList()));

        List<Nota> rez=new ArrayList<Nota>();

        for(Map.Entry<String,List<Nota>> entry: studentNote.entrySet())
        {

            if(entry.getKey().equals(idTema))
            {
                for(Nota nota:entry.getValue())
                {
                    String grupaS=studentRepository.findOne(nota.getIdStudent()).getGrupa();
                    //System.out.println(grupaS);
                    if(grupa.equals(grupaS))
                        rez.add(nota);
                }
            }

        }
        return rez;
    }



    public List<Nota> filterNoteSaptamana(int sapt)
    {

        List<Nota> lista=new ArrayList<Nota>();
        noteRepository.findAll().forEach(x->lista.add(x));
        List<Nota> rez=new ArrayList<Nota>();

        for(Nota nota: noteRepository.findAll())
        {
            LocalDateTime primaSaptamana=LocalDateTime.of(2018,10,1,0,0);
            long nrsapt= Duration.between(primaSaptamana,nota.getData()).toDays()/7;
            int saptamna=Math.toIntExact(nrsapt)+1;
            if(sapt==saptamna)
            {
                rez.add(nota);
            }
        }

        return rez;
    }
    private List<Observer<NotaEvent>> observers=new ArrayList<>();

    @Override
    public void addObserver(Observer<NotaEvent> e) {
        observers.add(e);
    }

    @Override
    public void removeObserver(Observer<NotaEvent> e) {
        observers.remove(e);
    }

    @Override
    public void notifyObservers(NotaEvent t) {
        observers.stream().forEach(x->x.update(t));
    }
}
