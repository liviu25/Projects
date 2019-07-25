package Model;

import java.io.Serializable;

public class Proba implements HasID<Integer>, Serializable {
    private Integer id;
    private TipProba tipProba;
    private Integer varstaMin,varstaMax;
    private Integer nrParticipanti;

    public Proba(Integer id, TipProba tipProba, Integer varstaMin, Integer varstaMax) {
        this.id = id;
        this.tipProba = tipProba;
        this.varstaMin = varstaMin;
        this.varstaMax = varstaMax;
    }

    @Override
    public String toString() {
        return "Proba{" +
                "id=" + id +
                ", tipProba=" + tipProba +
                ", varstaMin=" + varstaMin +
                ", varstaMax=" + varstaMax +
                ", nrParticipanti=" + nrParticipanti +
                '}';
    }

    @Override
    public Integer getID() {
        return id;
    }

    @Override
    public void setID(Integer integer) {
        this.id=integer;
    }

    public TipProba getTipProba() {
        return tipProba;
    }

    public void setTipProba(TipProba tipProba) {
        this.tipProba = tipProba;
    }

    public Integer getVarstaMin() {
        return varstaMin;
    }

    public void setVarstaMin(Integer varstaMin) {
        this.varstaMin = varstaMin;
    }

    public Integer getVarstaMax() {
        return varstaMax;
    }

    public void setVarstaMax(Integer varstaMax) {
        this.varstaMax = varstaMax;
    }

    public Integer getNrParticipanti() {
        return nrParticipanti;
    }

    public void setNrParticipanti(Integer nrParticipanti) {
        this.nrParticipanti = nrParticipanti;
    }
}
