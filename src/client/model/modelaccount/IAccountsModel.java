package client.model.modelaccount;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface IAccountsModel extends Remote {
    boolean accountExists(String username,String password);
    boolean checkUsername(String username);
    void createAccount(String username,String password,boolean isManager);

}
