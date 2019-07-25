package Repository;

import Domain.Client;
import Domain.Laptop;
import Domain.Vanzare;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;

public class VanzareRepo extends AbstractRepo<String, Vanzare> {
    private  String fileN;
    ClientRepository clientRepository;
    LaptopRepo laptopRepo;

    public VanzareRepo(String fileN, ClientRepository clientRepository, LaptopRepo laptopRepo) {
        this.fileN=fileN;

        this.clientRepository = clientRepository;
        this.laptopRepo=laptopRepo;
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
                    Client client= clientRepository.findOne(Long.parseLong( args[0]));
                    Laptop laptop=laptopRepo.findOne(args[1]);
                    Vanzare s=new Vanzare(client,laptop, LocalDateTime.parse(args[2]));
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
