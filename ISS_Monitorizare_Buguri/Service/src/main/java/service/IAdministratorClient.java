package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAdministratorClient extends Remote {
    void usersListUpdated() throws RemoteException;
}
