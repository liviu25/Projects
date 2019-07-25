package concurs.model;

import javafx.util.Pair;

public class Inscriere implements HasID<Pair<Integer,Integer>>{
    private Pair<Integer,Integer> id;
    private Participant participant;
    private Proba proba;

    public Inscriere(Participant participant, Proba proba) {
        id=new Pair<>(participant.getID(),proba.getID());
        this.participant = participant;
        this.proba = proba;
    }

    @Override
    public String toString() {
        return "concurs.model.Inscriere{" +
                "id=" + id +
                ", participant=" + participant +
                ", proba=" + proba +
                '}';
    }

    @Override
    public Pair<Integer, Integer> getID() {
        return id;
    }

    @Override
    public void setID(Pair<Integer, Integer> integerIntegerPair) {

    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public Proba getProba() {
        return proba;
    }

    public void setProba(Proba proba) {
        this.proba = proba;
    }
}
