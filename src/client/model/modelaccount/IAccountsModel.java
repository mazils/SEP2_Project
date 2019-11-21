package client.model.modelaccount;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface IAccountsModel extends Remote {
    boolean accountExists(String username,String password) throws RemoteException;
    boolean checkUsername(String username) throws RemoteException, SQLException;
    void createAccount(String username,String password,boolean isManager) throws RemoteException;

}
