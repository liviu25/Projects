package Repository;

import Domain.Client;
import Domain.Laptop;
import Domain.Type;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.Date;

public class LaptopRepo extends AbstractRepo<String, Laptop> {
    private String fileN;
    public LaptopRepo(String fileN) {
        this.fileN=fileN;
        loadFromFile();
    }

    private void loadFromFile()
    {

        String line,args[];

        try(BufferedReader reader=new BufferedReader(new FileReader(fileN)))
        {
            while((line=reader.readLine())!=null)
            {

                args=line.split("\\,");
                if(args.length==5)
                {
                    Laptop s=new Laptop(args[0],args[1],args[2], Type.valueOf( args[3]),Float.parseFloat( args[4]));
                    super.save(s);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
}
