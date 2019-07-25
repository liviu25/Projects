package concurs.service;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IObserver extends Remote {
    void probeUpdated() throws ConcursException, RemoteException;
}
