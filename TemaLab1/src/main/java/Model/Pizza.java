package Model;

public class Pizza {
    private Integer id;
    private String denumire;
    private Integer pret;

    public Pizza(Integer id, String denumire, Integer pret) {
        this.id = id;
        this.denumire = denumire;
        this.pret = pret;
    }

    public Integer getId() {
        return id;
    }

    public String getDenumire() {
        return denumire;
    }

    public Integer getPret() {
        return pret;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public void setPret(Integer pret) {
        this.pret = pret;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "id=" + id +
                ", denumire='" + denumire + '\'' +
                ", pret=" + pret +
                '}';
    }
}
