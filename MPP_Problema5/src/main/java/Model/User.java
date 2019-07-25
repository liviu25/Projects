package Model;

import java.io.Serializable;
import java.util.Objects;

public class User implements HasID<String>, Serializable {
    private String ID,password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String id, String password) {
        ID = id;
        this.password = password;
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public void setID(String s) {
        ID=s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return ID.equals(user.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }

    @Override
    public String toString() {
        return "User{" +
                "ID='" + ID + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
