package Repository;

import Domain.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RentRepo extends AbstractRepo<String, Rent> {
    private  String fileN;
    private ClientRepo clientRepo;
    private BookRepo bookRepo;
    private DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public RentRepo(String fileN, ClientRepo clientRepo, BookRepo bookRepo) {
        this.fileN=fileN;

        this.clientRepo = clientRepo;
        this.bookRepo = bookRepo;
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
                if(args.length==3)
                {
                    Book book= bookRepo.findOne(args[0]);
                    Client client= clientRepo.findOne( args[1]);

                    Rent s=new Rent(book,client, LocalDate.parse(args[2],formatter));
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
