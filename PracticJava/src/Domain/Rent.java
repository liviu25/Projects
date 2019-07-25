package Domain;

import java.time.LocalDate;

public class Rent implements HasID<String> {

    private String id;
    private Book book;
    private Client client;
    private LocalDate rentDate;

    public Rent(Book book,Client client,  LocalDate rentDate) {
        this.id=client.getID()+"-"+book.getID();
        this.book = book;
        this.client = client;
        this.rentDate = rentDate;
    }

    @Override
    public String toString() {
        return "Rent{" +
                "id='" + id + '\'' +
                ", book=" + book +
                ", client=" + client +
                ", rentDate=" + rentDate +
                '}';
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDate getRentDate() {
        return rentDate;
    }

    public void setRentDate(LocalDate rentDate) {
        this.rentDate = rentDate;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public void setID(String s) {
        id=s;
    }
}
