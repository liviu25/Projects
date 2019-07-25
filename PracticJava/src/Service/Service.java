package Service;

import Domain.Book;
import Domain.Rent;
import Domain.Type;
import Repository.BookRepo;
import Repository.ClientRepo;
import Repository.RentRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Service {
    private ClientRepo clientRepo;
    private BookRepo bookRepo;
    private RentRepo rentRepo;

    public Service(ClientRepo clientRepo, BookRepo bookRepo, RentRepo rentRepo) {
        this.clientRepo = clientRepo;
        this.bookRepo = bookRepo;
        this.rentRepo = rentRepo;
    }

    public List<Book> filterBookByTypeAndAuthor()
    {
        List<Book> rez=new ArrayList<>();
        List<Book> list=bookRepo.findAll().stream()
                .sorted((x,y)->y.getType().toString().compareTo(x.getType().toString()))
                .collect(Collectors.toList());
        Map<Type,List<Book>> map=list.stream().collect(Collectors.groupingBy(Book::getType));
        Map<Type,List<Book>> map2= map.entrySet().stream()
                .sorted((x,y)->y.getKey().toString().compareTo(x.getKey().toString()))
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));

        for (Map.Entry<Type, List<Book>> entry : map2.entrySet()) {
            entry.getValue().stream().sorted((x,y)->y.getAuthor().compareTo(x.getAuthor()))
                    .collect(Collectors.toList()).forEach(x->rez.add(x));
        }
        return rez;
    }

    public List<Rent> filterRentsByName(String nume)
    {
        List<Rent> list=new ArrayList<>();
        for (Rent rent : rentRepo.findAll()) {
            //System.out.println(rent.getClient());
            if(rent.getClient().getName().equals(nume))
            {
                list.add(rent);
            }
        }
        return list;
    }

    public List<Book> filterBookbyRents(int year)
    {

        List<Book> rez=new ArrayList<>();
        for (Book book : bookRepo.findAll()) {
            int nr=0;
            for (Rent rent : rentRepo.findAll()) {
                if(rent.getBook().getID().equals(book.getID()) && rent.getRentDate().getYear()==year)
                {
                    nr=nr+1;
                }
            }
            if(nr<=3)
                rez.add(book);
        }

        return rez;
    }




}
