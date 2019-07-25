package practic.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Games", schema = "examenmppdb")
public class Game implements Serializable {
    private int id;
    private String winner;
    private List<Player> activePlayers=new ArrayList<>();

    private int currentPlayer;

    public Game() {
        this.activePlayers.forEach(x->x.setGame(this));
    }

    @Id
    @Column(name = "Id", nullable = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Winner", nullable = true)
    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(id, game.id) &&
                Objects.equals(winner, game.winner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, winner);
    }

    @OneToMany(mappedBy = "game",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonBackReference
    public List<Player> getActivePlayers() {
        return activePlayers;
    }

    public void setActivePlayers(List<Player> activePlayers) {
        this.activePlayers = activePlayers;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", winner='" + winner + '\'' +
                '}';
    }



    @Column
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }



}
