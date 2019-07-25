package Networking.objectProtocol;

import Model.Participant;
import Model.Proba;

public class GetParticipantiRequest implements Request {
    private Integer idProba;

    public GetParticipantiRequest(Integer idProba) {
        this.idProba = idProba;
    }

    public Integer getIdProba() {
        return idProba;
    }
}
