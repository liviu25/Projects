package Domain;

public class Client implements HasID<Long> {
    private Long clientId;
    private String name;

    public Client(Long clientId, String name) {
        this.clientId = clientId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Long getID() {
        return clientId;
    }

    @Override
    public void setID(Long aLong) {
        clientId=aLong;
    }
}
