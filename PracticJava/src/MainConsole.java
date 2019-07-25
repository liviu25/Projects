import Console.ConsoleUI;
import Repository.ClientRepo;
import Repository.BookRepo;
import Repository.RentRepo;
import Service.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MainConsole {


    public static void main(String[] args) throws IOException {
        ClientRepo clientRepo = new ClientRepo("C:\\Users\\bud_l\\IdeaProjects\\PracticJava\\src\\clients.txt");
        BookRepo bookRepo = new BookRepo("C:\\Users\\bud_l\\IdeaProjects\\PracticJava\\src\\books.txt");
        RentRepo rentRepo = new RentRepo("C:\\Users\\bud_l\\IdeaProjects\\PracticJava\\src\\rent.txt", clientRepo, bookRepo);

        //clientRepo.findAll().forEach(x-> System.out.println(x));
        //bookRepo.findAll().forEach(x-> System.out.println(x));
        rentRepo.findAll().forEach(x-> System.out.println(x));

        Service service = new Service(clientRepo, bookRepo, rentRepo);
        ConsoleUI ui = new ConsoleUI(service);
        ui.start();

    }

}
