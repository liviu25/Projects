package Domain;

import Validator.HasID;

import java.time.LocalDateTime;

public class Nota implements HasID<String> {
    private String idNota,idStudent,idTema;
    private int valoare;
    LocalDateTime data;
    /**
     *
     * @return numarul temei
     */
    @Override
    public String getID() {
        return idNota;
    }

    public Nota() {
    }

    /**
     * seteza numarul temei
     * @param s String
     */



    @Override
    public void setID(String s) {
        idNota=s;
    }

    public Nota(String idStudent, String idTema, int valoare) {
        idNota=idStudent+"-"+idTema;
        this.idStudent = idStudent;
        this.idTema = idTema;
        this.valoare = valoare;
        data=LocalDateTime.now();
    }

    public Nota(String idNota, String idStudent, String idTema, int valoare, LocalDateTime data) {
        this.idNota = idNota;
        this.idStudent = idStudent;
        this.idTema = idTema;
        this.valoare = valoare;
        this.data = data;
    }

    public String getIdNota() {
        return idNota;
    }

    public void setIdNota(String idNota) {
        this.idNota = idNota;
    }

    public String getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }

    public String getIdTema() {
        return idTema;
    }

    public void setIdTema(String idTema) {
        this.idTema = idTema;
    }

    public int getValoare() {
        return valoare;
    }

    public void setValoare(int valoare) {
        this.valoare = valoare;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return idStudent+" "+idTema+" "+valoare;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Nota)
        {
            Nota nota=(Nota) obj;
            return this.toString().equals(nota.toString());
        }
        return false;
    }

}
