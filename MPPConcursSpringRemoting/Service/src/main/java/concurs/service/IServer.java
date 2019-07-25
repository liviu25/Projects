package concurs.service;

import concurs.model.Participant;
import concurs.model.Proba;
import concurs.model.User;

public interface IServer {
    void login(User user, IObserver client) throws ConcursException;
    void logout(User user, IObserver client) throws ConcursException;

    Iterable<Proba> getProbeAndNrParticipanti() throws ConcursException;
    Iterable<Participant> getParticipantiByProba(Integer idProba) throws ConcursException;
    void inscriereParticipant(Participant participant, String tipProba1,String tipProba2) throws ConcursException;


}
