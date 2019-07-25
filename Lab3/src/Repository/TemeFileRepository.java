package Repository;

import Domain.Tema;
import Validator.Validator;

import java.io.*;

public class TemeFileRepository extends TemeRepository {
    private String fileN;

    public TemeFileRepository(Validator<Tema> v, String fileN) {
        super(v);
        this.fileN = fileN;
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
                if(args.length==4)
                {
                    Tema s=new Tema(args[0],args[1],Integer.parseInt(args[2]),Integer.parseInt(args[3]));
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
            for (Tema t : super.findAll()) {
                writer.println(t.getID() + "|" + t.getDescriere() + "|" + t.getDeadline() + "|" + t.getNrSaptamanaPrimire());
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

        }
    }

    @Override
    public Tema save(Tema tema) {

        Tema t= super.save(tema);
        StoreToFile();
        return t;
    }

    @Override
    public Tema update(Tema tema) {
        Tema t= super.update(tema);
        StoreToFile();
        return t;
    }

    @Override
    public Tema delete(String s) {
        Tema t= super.delete(s);
        StoreToFile();
        return t;
    }

    @Override
    public Tema findOne(String s) {
        return super.findOne(s);
    }

    @Override
    public int size() {
        return super.size();
    }


}
