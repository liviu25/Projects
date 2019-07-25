package Domain;

public class Client implements HasID<String> {
    private String clientId;
    private String name;

    public Client(String clientId, String name) {
        this.clientId = clientId;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId='" + clientId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getID() {
        return clientId;
    }

    @Override
    public void setID(String s) {
        clientId=s;
    }
}
