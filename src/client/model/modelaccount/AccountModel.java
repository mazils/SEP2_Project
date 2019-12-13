package client.model.modelaccount;

import Shared.remoteServer.AccountsRServer;
import client.model.logModel.Logger;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

public class AccountModel implements IAccountsModel {
    private AccountsRServer rmi;
    private Account acc;
    private Logger log= Logger.getInstance();
    public AccountModel() throws RemoteException, NotBoundException {
        UnicastRemoteObject.exportObject(this,0);
        Registry reg = LocateRegistry.getRegistry("Localhost",1099);
        rmi = (AccountsRServer) reg.lookup("server");
        System.out.println("Connected to Server");

    }


    /**
     * Check if account already exists  by given parameters
     * @param username
     * @param password
     * @return true or false
     */
    public boolean accountExists( String username, String password) throws RemoteException {

        if(rmi.checkIfExists(username, password)) {
            log.setUsername(username);
            System.out.println( "username is    "+username);
        }

        return rmi.checkIfExists(username, password);

    }

    public boolean accountIsManager(String username, String password)
    {
        try {
           return rmi.accountIsManager(username,password);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
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
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void createAccount(String userName,String password,boolean isManager ) throws RemoteException {

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
