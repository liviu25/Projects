package Domain;

import java.time.LocalDateTime;
import java.util.Date;

public class Vanzare implements HasID<String>{
    private String id;
    private Client client;
    private  Laptop laptop;
    private LocalDateTime date;

    public Vanzare(Client client, Laptop laptop, LocalDateTime date) {
        id=client.getID()+"-"+laptop.getID();
        this.client = client;
        this.laptop = laptop;
        this.date = date;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Laptop getLaptop() {
        return laptop;
    }

    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
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
