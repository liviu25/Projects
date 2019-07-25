package Repository;

import Domain.Nota;
import Domain.Student;
import Validator.*;

import java.io.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class NoteFileRepository extends NoteRepository {
    public String fileN;
    public NoteFileRepository(Validator<Nota> v,String fileN) {
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

                    Nota s=new Nota(args[0],args[1],args[2],Integer.parseInt(args[3]),LocalDateTime.parse(args[4]));
                    super.save(s);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        //super.findAll().forEach(System.out::println);
    }
    private void StoreToFile()
    {
        try(PrintWriter writer=new PrintWriter(new FileWriter(fileN))) {
            for (Nota nota : super.findAll()) {
                writer.println(nota.getIdNota() + "|" + nota.getIdStudent() + "|" + nota.getIdTema() + "|" + nota.getValoare() + "|" + nota.getData().toString());
            }
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
    public Nota save(Nota nota) throws ValidationException{
        nota= super.save(nota);
        StoreToFile();
        return nota;
    }

    @Override
    public Nota update(Nota nota)throws ValidationException {
        nota= super.update(nota);
        StoreToFile();
        return nota;
    }

    @Override
    public Nota delete(String s) throws ValidationException{
        Nota nota= super.delete(s);
        StoreToFile();
        return nota;
    }
}
