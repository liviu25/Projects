package concurs.server;

import concurs.model.*;
import concurs.repository.InscriereDbRepository;
import concurs.repository.LoginRepository;
import concurs.repository.ParticipantDbRepository;
import concurs.repository.ProbaDbRepository;
import concurs.service.ConcursException;
import concurs.service.IObserver;
import concurs.service.IServer;

import java.rmi.RemoteException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcursServer implements IServer {
    private Map<String, IObserver> loggedClients;
    private LoginRepository loginRepository;
    private ParticipantDbRepository participantDbRepository;
    private ProbaDbRepository probaDbRepository;
    private InscriereDbRepository inscriereDbRepository;

    public ConcursServer(LoginRepository loginRepository, ParticipantDbRepository participantDbRepository,
                         ProbaDbRepository probaDbRepository, InscriereDbRepository inscriereDbRepository) {
        this.loginRepository = loginRepository;
        this.participantDbRepository = participantDbRepository;
        this.probaDbRepository = probaDbRepository;
        this.inscriereDbRepository = inscriereDbRepository;
        loggedClients=new ConcurrentHashMap<>();
    }

    public synchronized void login(User user, IObserver client) throws ConcursException {
        User user1= loginRepository.verifUser(user);
        if(user1!=null)
        {
            if(loggedClients.get(user.getID())!=null)
                throw new ConcursException("concurs.model.User already logged in.");
            loggedClients.put(user.getID(), client);
        }
        else throw new ConcursException("Authentication failed.");
    }

    @Override
    public void logout(User user, IObserver client) throws ConcursException {
        IObserver localClient=loggedClients.remove(user.getID());
        if (localClient==null)
            throw new ConcursException("concurs.model.User "+user.getID()+" is not logged in.");
    }

    public synchronized Iterable<Proba> getProbeAndNrParticipanti(){
        return probaDbRepository.findProbeAndNrParticipanti();
    }

    public synchronized Iterable<Participant> getParticipantiByProba(Integer idProba)
    {

        return participantDbRepository.findParticipantiByProba(idProba);
    }

    public synchronized void inscriereParticipant(Participant participant, String tipProba1,String tipProba2) {
        Participant savedParticipant = participantDbRepository.save(participant);
        if (tipProba1 != null) {
            Proba proba1 =
                    probaDbRepository.getProbaByTipAndVarsta(TipProba.valueOf(tipProba1), participant.getVarsta());
            Inscriere inscriere1 = new Inscriere(savedParticipant, proba1);

            inscriereDbRepository.save(inscriere1);
        }
        if (tipProba2 != null) {
            Proba proba2 =
                    probaDbRepository.getProbaByTipAndVarsta(TipProba.valueOf(tipProba2), participant.getVarsta());
            Inscriere inscriere2 = new Inscriere(savedParticipant, proba2);
            inscriereDbRepository.save(inscriere2);
        }
        notifyProbeUpdated();
    }

    private final int defaultThreadsNo=4;
    private void notifyProbeUpdated()
    {

        ExecutorService executor= Executors.newFixedThreadPool(defaultThreadsNo);
        for (IObserver client : loggedClients.values()) {
            if(client != null)
            {
                executor.execute(() ->{
                    try {
                        client.probeUpdated();
                    } catch (ConcursException e) {
                        e.printStackTrace();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
        executor.shutdown();
    }

}
