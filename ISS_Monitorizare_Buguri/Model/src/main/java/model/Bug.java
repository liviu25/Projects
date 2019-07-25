package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Bugs", schema = "main")
public class Bug implements Serializable {
    private Integer id;
    private String denumire;
    private String descriere;
    private String status;


    public Bug(String denumire, String descriere) {
        this.denumire = denumire;
        this.descriere = descriere;
        this.status="nerezolvat";
    }

    public Bug() {
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Denumire")
    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    @Basic
    @Column(name = "Descriere")
    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    @Basic
    @Column(name = "Status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bug bug = (Bug) o;
        return Objects.equals(id, bug.id) &&
                Objects.equals(denumire, bug.denumire) &&
                Objects.equals(descriere, bug.descriere);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, denumire, descriere);
    }

    @Override
    public String toString() {
        return "Bug{" +
                "id=" + id +
                ", denumire='" + denumire + '\'' +
                ", descriere='" + descriere + '\'' +
                '}';
    }



}
