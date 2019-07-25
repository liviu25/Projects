import Domain.Laptop;
import Domain.Vanzare;
import Repository.ClientRepository;
import Repository.LaptopRepo;
import Repository.VanzareRepo;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) {
        ClientRepository clientRepository =new ClientRepository("C:\\Users\\bud_l\\IdeaProjects\\Laptopuri\\src\\clients.txt");
        LaptopRepo laptopRepo=new LaptopRepo("C:\\Users\\bud_l\\IdeaProjects\\Laptopuri\\src\\laptops.txt");
        VanzareRepo vanzareRepo=new VanzareRepo("C:\\Users\\bud_l\\IdeaProjects\\Laptopuri\\src\\vanzari.txt", clientRepository,laptopRepo);

        //System.out.println(LocalDateTime.now());
//        clientRepository.findAll().forEach(x-> System.out.println(x.getID()));
//        clientRepository.findAll().forEach(x-> System.out.println(x.getID()));
        //vanzareRepo.findAll().forEach(x-> System.out.println(x.getID()));


        Map<String,List<Laptop>> map= laptopRepo.findAll().stream().collect(Collectors.groupingBy(Laptop::getProducator,Collectors.toList()));
        //System.out.println(map);
        map.entrySet().forEach(
                x-> {
                    System.out.println(x.getKey());
                    x.getValue().forEach(value-> System.out.println(value));
                }

        );
        System.out.println("--------------");

        LocalDateTime dateTime= LocalDateTime.parse("2018-12-11T16:21:42.753");
        for(Vanzare vanzare:vanzareRepo.findAll())
        {
            //System.out.println(vanzare.getID());
            if(vanzare.getLaptop().getTip().toString().equals("gaming"))
                System.out.println(vanzare.getID());
        }

        System.out.println("------------");

        vanzareRepo.findAll().stream().filter(x->x.getLaptop().getTip().toString().equals("gaming"))
                .filter(x->x.getDate().equals(dateTime))
                .collect(Collectors.toList())
                .forEach(x-> System.out.println(x));


        Date date=new Date(2018,12,11);



    }


}
