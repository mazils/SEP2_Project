package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteServer extends Remote {
    boolean checkIfExists(String userName,String password) throws RemoteException;

}
