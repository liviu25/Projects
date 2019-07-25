package Console;

import Service.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleUI {
    private Service service;

    public ConsoleUI(Service service) {
        this.service = service;
    }

    public void start() throws IOException
    {
        while(true)
        {
            System.out.println("Filtrare 1 ");
            System.out.println("Filtrare 2 ");
            System.out.println("Filtrare 3 ");
            System.out.println("Iesire\n");

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String cmd;
            System.out.print("cmd = ");
            cmd = reader.readLine();
            if(cmd.equals("1"))
            {
                service.filterBookByTypeAndAuthor()
                .forEach(x-> System.out.println(x));
            }
            else if(cmd.equals("2"))
            {
                System.out.println("nume= ");
                String nume=reader.readLine();
                service.filterRentsByName(nume).forEach(x-> System.out.println(x));
            }
            else if(cmd.equals("3"))
            {
                System.out.println("an= ");
                int an=Integer.parseInt( reader.readLine());
                service.filterBookbyRents(an).forEach(x-> System.out.println(x));
            }
            else if(cmd.equals("0"))
            {
                break;
            }
        }
    }
}
