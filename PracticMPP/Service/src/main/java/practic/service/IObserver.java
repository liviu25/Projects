package practic.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IObserver extends Remote {
    void gameStarted() throws RemoteException;
    void notifyMove() throws RemoteException;
    void gameFinished() throws RemoteException;
}
