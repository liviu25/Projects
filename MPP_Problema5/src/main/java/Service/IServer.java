package Service;

import Model.Participant;
import Model.Proba;
import Model.User;
import javafx.util.Pair;

public interface IServer {
    void login(User user,IObserver client) throws ConcursException;
    void logout(User user,IObserver client) throws ConcursException;

    Iterable<Proba> getProbeAndNrParticipanti() throws ConcursException;
    Iterable<Participant> getParticipantiByProba(Integer idProba) throws ConcursException;
    void inscriereParticipant(Participant participant, String tipProba1,String tipProba2) throws ConcursException;


}
