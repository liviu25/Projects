package Domain;
public interface HasID<ID> {
    /**
     * getter pentru id
     * @return id-ul
     */
    ID getID();

    /**
     * setter pentru id
     * @param id ID
     */
    void setID(ID id);
}
