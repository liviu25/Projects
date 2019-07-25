package Repository;

import Domain.Student;
import Validator.Validator;

import java.io.*;

public class StudentFileRepository extends StudentRepository {
    private String fileN;
    /**
     * constructor
     *
     * @param v validator pentru student
     */
    public StudentFileRepository(Validator<Student> v, String fileN) {
        super(v);
        this.fileN=fileN;
        LoadFromFile();
    }
    private void LoadFromFile()
    {

        String line,args[];

        try(BufferedReader reader=new BufferedReader(new FileReader(fileN)))
        {
            while((line=reader.readLine())!=null)
            {

                args=line.split("\\|");
                if(args.length==5)
                {
                    Student s=new Student(args[0],args[1],args[2],args[3],args[4]);
                    super.save(s);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
    private void StoreToFile()
    {
        try(PrintWriter writer=new PrintWriter(new FileWriter(fileN))) {
            super.findAll().forEach(s-> writer.println( s.getIdStudent() + "|" + s.getNume() + "|" + s.getGrupa() + "|" + s.getEmail() + "|" + s.getProfesor()));
            /*
            for (Student s : super.findAll()) {
                writer.println(s.getIdStudent() + "|" + s.getNume() + "|" + s.getGrupa() + "|" + s.getEmail() + "|" + s.getProfesor());
            }
            */
        }
        catch (Exception e )
        {
            System.out.println(e.getMessage());
        }
    }

    public void deleteAll()
    {
        try(BufferedWriter writer=new BufferedWriter(new FileWriter(fileN))) {
            writer.write("");
        }
        catch (Exception e )
        {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public Student save(Student student) {
        Student s=super.save(student);
        StoreToFile();
        return s;
    }

    @Override
    public Student update(Student student) {
        Student s = super.update(student);
        StoreToFile();
        return s;
    }

    @Override
    public Student delete(String s) {
        Student student = super.delete(s);
        StoreToFile();
        return student;
    }
}
