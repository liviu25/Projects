package Domain;

import Validator.HasID;

public class Tema implements HasID<String> {
    private String id,descriere;
    private int deadline,nrSaptamanaPrimire;

    /**
     * contructor
     * @param id String -numarul temei
     * @param descriere String
     * @param deadline int -termenul  de predare
     * @param nrSaptamanaPrimire int saptamana in care a fost primita tema
     */
    public Tema(String id, String descriere, int deadline, int nrSaptamanaPrimire) {
        this.id = id;
        this.descriere = descriere;
        this.deadline = deadline;
        this.nrSaptamanaPrimire = nrSaptamanaPrimire;
    }

    /**
     *
     * @return id-ul temei
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @return descrierea
     */
    public String getDescriere() {
        return descriere;
    }

    /**
     *
     * @return deadline
     */
    public int getDeadline() {
        return deadline;
    }

    /**
     *
     * @return saptamana primirii temei
     */
    public int getNrSaptamanaPrimire() {
        return nrSaptamanaPrimire;
    }

    /**
     * seteaza id-ul
     * @param id noul id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * seteazaa descrierea
     * @param descriere noua descriere
     */
    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    /**
     * seteeaza deadline-ul
     * @param deadline noul deadline
     */
    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    /**
     * seteaza saptamana primirii temei
     * @param nrSaptamanaPrimire saptamana de primire
     */
    public void setNrSaptamanaPrimire(int nrSaptamanaPrimire) {
        this.nrSaptamanaPrimire = nrSaptamanaPrimire;
    }

    /**
     *
     * @return id-ul temei
     */
    @Override
    public String getID() {
        return id;
    }

    /**
     * seteaza id-ul temei
     * @param s String
     */
    @Override
    public void setID(String s) {
        id=s;
    }

    @Override
    public String toString() {
        return id+" "+descriere+" "+deadline+" "+nrSaptamanaPrimire;
    }
}
