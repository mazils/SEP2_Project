package client.model.modelaccount;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAccountsModel extends Remote {
    boolean accountExists(String username,String password) throws RemoteException;
    boolean checkUsername(String username) throws RemoteException;

}
