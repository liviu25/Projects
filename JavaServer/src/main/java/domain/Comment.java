package domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "comments", schema = "jsp_problem1")
public class Comment {
    private int id;
    private String message;
    private String email;
    private Integer verified;

    public Comment(String message, String email) {
        this.message = message;
        this.email = email;
    }

    public Comment() {
    }

    @Id
    @Column(name = "Id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Message", nullable = true, length = 255)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Basic
    @Column(name = "Email", nullable = true, length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id == comment.id &&
                Objects.equals(message, comment.message) &&
                Objects.equals(email, comment.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, email);
    }

    @Basic
    @Column(name = "Verified")
    public Integer getVerified() {
        return verified;
    }

    public void setVerified(Integer verified) {
        this.verified = verified;
    }
}
