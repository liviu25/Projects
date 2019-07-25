package Networking.objectProtocol;

import Model.Participant;

public class GetParticipantiResponse implements Response {
    private Iterable<Participant> participants;

    public GetParticipantiResponse(Iterable<Participant> participants) {
        this.participants = participants;
    }

    public Iterable<Participant> getParticipants() {
        return participants;
    }
}
