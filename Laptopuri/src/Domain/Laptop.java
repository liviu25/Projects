package Domain;


public class Laptop implements HasID<String> {
    private String laptopId,producator,model;
    private Type tip;
    private Float pret;

    public Laptop(String laptopId, String producator, String model, Type tip, Float pret) {
        this.laptopId = laptopId;
        this.producator = producator;
        this.model = model;
        this.tip = tip;
        this.pret = pret;
    }


    @Override
    public String getID() {
        return laptopId;
    }

    @Override
    public void setID(String s) {
        laptopId=s;
    }

    public String getProducator() {
        return producator;
    }

    public void setProducator(String producator) {
        this.producator = producator;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Type getTip() {
        return tip;
    }

    public void setTip(Type tip) {
        this.tip = tip;
    }

    public Float getPret() {
        return pret;
    }

    public void setPret(Float pret) {
        this.pret = pret;
    }
}
