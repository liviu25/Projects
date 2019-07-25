package Repository;

import Domain.Book;
import Domain.Type;

import java.io.BufferedReader;
import java.io.FileReader;

public class BookRepo extends AbstractRepo<String, Book> {
    private String fileN;
    public BookRepo(String fileN) {
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
                if(args.length==4)
                {
                    Book s=new Book(args[0],args[1],args[2], Type.valueOf( args[3]));
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
