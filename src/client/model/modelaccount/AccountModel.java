package client.model.modelaccount;

import server.RemoteServer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccountModel implements IAccountsModel {
    private RemoteServer rmi;
    private Account acc;
    public AccountModel() throws RemoteException, NotBoundException {
        UnicastRemoteObject.exportObject(this,0);
        Registry reg = LocateRegistry.getRegistry("Localhost",1099);
        rmi = (RemoteServer) reg.lookup("server");
        System.out.println("Connected to Server");

    }


    /**
     * Check if account already exists  by given parameters
     * @param username
     * @param password
     * @return true or false
     */
    public boolean accountExists( String username, String password) {


           return rmi.checkIfExists(username, password);

    }

    /**
     * Check if user name is already in use
     * @param username
     * @return true or false
     */
    public boolean checkUsername(String username) {


        try {
            return rmi.checkUsername(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void createAccount(String userName,String password,boolean isManager ){

       if(isManager)
       {
            acc = new Manager(userName,password);
       }
       else
       {
            acc = new VOS(userName,password);
       }
           rmi.addAccount(acc, isManager);

    }

}
