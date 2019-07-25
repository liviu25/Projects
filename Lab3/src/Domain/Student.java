package Domain;

import Validator.HasID;

public class Student implements HasID<String> {
    private String idStudent,grupa,nume,email,profesor;

    public Student(String idStudent, String grupa, String nume, String email, String profesor) {
        this.idStudent = idStudent;
        this.grupa = grupa;
        this.nume = nume;
        this.email = email;
        this.profesor = profesor;
    }

    /**
     *
     * @return id student
     */
    @Override
    public String getID() {
        return idStudent;
    }

    /**
     * seteaza id-u studentului
     * @param s String
     */
    @Override
    public void setID(String s) {
        setIdStudent(s);
    }

    /**
     *
     * @return id-ul studentului
     */
    public String getIdStudent() {
        return idStudent;
    }

    /**
     *
     * @return grupa studentului
     */
    public String getGrupa() {
        return grupa;
    }

    /**
     *
     * @return numele studentului
     */
    public String getNume() {
        return nume;
    }

    /**
     *
     * @return email-ul studentului
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @return profesor
     */
    public String getProfesor() {
        return profesor;
    }

    /**
     * seteza id-ul studentului
     * @param idStudent -noul id
     */
    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }

    /**
     * seteaza grupa studentului
     * @param grupa -noua grupa
     */
    public void setGrupa(String grupa) {
        this.grupa = grupa;
    }

    /**
     * seteaza numele
     * @param nume noul nume
     */
    public void setNume(String nume) {
        this.nume = nume;
    }

    /**
     * seteaza email-ul
     * @param email noul email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * seteaza profesorul
     * @param profesor noul profesor
     */
    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    @Override
    public String toString() {
        return idStudent+" "+grupa+" "+nume+" "+email+" "+ profesor;
    }
}
