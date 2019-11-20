package client.model.modelaccount;

import server.RemoteServer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class AccountModel implements IAccountsModel {
    private ArrayList<Account> database;
    private RemoteServer rmi;

    public AccountModel() throws RemoteException, NotBoundException {
        UnicastRemoteObject.exportObject(this,0);
        Registry reg = LocateRegistry.getRegistry();
        rmi = (RemoteServer) reg.lookup("Accounts");
        System.out.println("Connected to Server");

        database= new ArrayList<>();
    }

    public void addAccount(Account acc)
    {
        database.add(acc);
    }


    /**
     * Check if account already exists  by given parameters
     * @param username
     * @param password
     * @return true or false
     */
    public boolean accountExists( String username, String password)
    {
       return rmi.checkIfExists(username,password);
    }

    public void createAccount(Account acc)
    {
        database.add(acc);
    }

    /**
     * Check if user name is already in use
     * @param username
     * @return true or false
     */
    public boolean checkUsername(String username)
    {
        for(int i= 0; i<database.size();i++)
        {
            if(username== database.get(i).getUsername())
            {
                return true;
            }
        }
        return false;
    }

}
