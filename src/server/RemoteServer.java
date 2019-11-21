package server;

import client.model.modelaccount.Account;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteServer extends Remote {
    boolean checkIfExists(String userName,String password) throws RemoteException;
    void addAccount(Account acc,boolean isManager) throws RemoteException;

}
