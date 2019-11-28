package server;

import client.model.SMModel.ISModel;
import client.model.SMModel.ISparePart;
import client.model.SMModel.SparePart;
import client.model.modelaccount.Account;
import server.jdbc.AccountsJDBC;
import server.jdbc.JDBC;
import server.jdbc.SModelJDBC;
import server.jdbc.SparePartsJDBC;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

public class Server implements RemoteServer
{
    // private ISM sparePartsModel
    // private IMM modelsModel
   private AccountsJDBC accountsJDBC;
    private SModelJDBC sModelJDBC;
   private SparePartsJDBC sparePartsJDBC;



    public Server() {
       accountsJDBC= new AccountsJDBC();
       sModelJDBC= new SModelJDBC();
       sparePartsJDBC= new SparePartsJDBC();
        try{
            UnicastRemoteObject.exportObject(this,0);
            System.out.println("Server started");
        }catch (RemoteException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public synchronized boolean checkIfExists(String userName, String password) {
        try {
            return accountsJDBC.checkAccExists(userName,password);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkUsername(String username)throws SQLException {
        return accountsJDBC.checkUsername(username);
    }

    @Override
    public void addAccount(Account acc,boolean isManager){
        accountsJDBC.addAccount(acc,isManager);

    }

    @Override
    public void addModel(ISModel model) {
        sModelJDBC.addModel(model);

    }

    @Override
    public void addSparePart(ISparePart sparePart, ISModel model) {
        sparePartsJDBC.addSparePart(sparePart,model);
    }

    @Override
    public void removeSparePart(ISparePart sparePart, ISModel model) {
        sparePartsJDBC.removeSparePart(sparePart,model);
    }

    @Override
    public void removeModel(ISModel model) {
        sModelJDBC.removeModel(model);
    }

    @Override
    public ArrayList<SparePart> getAllSpareparts(ISModel model) {

        ArrayList<SparePart> partArrayList= new ArrayList<>();
        try {
           partArrayList= sparePartsJDBC.getAllSpareParts(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return partArrayList;
    }

    public static void main(String[] args) {
        JDBC database= JDBC.getInstance();
        Thread thread = new Thread(database);
        thread.start();
        Registry registry;
        try{
            registry = LocateRegistry.createRegistry(1099);
            Server server = new Server();
            registry.bind("server", server);
        }catch(RemoteException | AlreadyBoundException e){
            throw new RuntimeException(e);
        }
    }


}
