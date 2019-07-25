package Networking.dto;

import Model.Participant;

import java.io.Serializable;

public class InscriereDTO implements Serializable {
    private Participant participant;
    private String tipProba1,tipProba2;

    public InscriereDTO(Participant participant, String tipProba1, String tipProba2) {
        this.participant = participant;
        this.tipProba1 = tipProba1;
        this.tipProba2 = tipProba2;
    }

    public Participant getParticipant() {
        return participant;
    }

    public String getTipProba1() {
        return tipProba1;
    }

    public String getTipProba2() {
        return tipProba2;
    }

    @Override
    public String toString() {
        return "InscriereDTO{" +
                "participant=" + participant +
                ", tipProba1='" + tipProba1 + '\'' +
                ", tipProba2='" + tipProba2 + '\'' +
                '}';
    }
}
