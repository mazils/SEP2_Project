package client.model.modelaccount;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface IAccountsModel extends Remote {
    boolean accountExists(String username,String password) throws RemoteException;
    boolean checkUsername(String username) throws RemoteException, SQLException;//todo we should not catch sql here
    void createAccount(String username,String password,boolean isManager) throws RemoteException;
    boolean accountIsManager(String value, String value1) throws RemoteException;
}
