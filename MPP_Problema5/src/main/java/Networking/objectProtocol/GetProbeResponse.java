package Networking.objectProtocol;

import Model.Proba;

public class GetProbeResponse implements Response {
    private Iterable<Proba> probe;

    public GetProbeResponse(Iterable<Proba> probe) {
        this.probe = probe;
    }

    public Iterable<Proba> getProbe() {
        return probe;
    }
}
