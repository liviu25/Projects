package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IProgrammerClient extends Remote {
    void bugsListUpdated() throws RemoteException;
}
