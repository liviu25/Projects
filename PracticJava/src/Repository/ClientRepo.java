package Repository;

import Domain.Client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

public class ClientRepo extends AbstractRepo<String, Client> {
    private  String fileN;
    public ClientRepo(String fileN) {
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
                if(args.length==2)
                {
                    Client s=new Client(args[0],args[1]);
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
