package server;

import Shared.RemotePropertyChangeListener;
import Shared.remoteServer.AccountsRServer;
import Shared.remoteServer.LogServer;
import Shared.remoteServer.SModelsRServer;
import Shared.remoteServer.SparePartsServer;
import client.model.ScooterModels.ISModel;
import client.model.ScooterModels.SModel;
import client.model.spareParts.ISparePart;
import client.model.spareParts.SparePart;
import client.model.modelaccount.Account;
import server.jdbc.JDBC;
import server.serverManager.AccountsSManager;
import server.serverManager.LogsManager;
import server.serverManager.SModelsSMnagaer;
import server.serverManager.SparePartsSManager;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

public class Server implements AccountsRServer, SparePartsServer, SModelsRServer, LogServer {
    private AccountsSManager accountsSManager;
    private SModelsSMnagaer sModelsSMnagaer;
    private SparePartsSManager sparePartsSManager;
    private LogsManager logsManager;


    public Server() {
        accountsSManager = new AccountsSManager();
        sModelsSMnagaer = new SModelsSMnagaer();
        sparePartsSManager = new SparePartsSManager();
        logsManager= new LogsManager();

        try{
            UnicastRemoteObject.exportObject(this,0);
            System.out.println("Server started");
        }catch (RemoteException e){
            e.printStackTrace();
        }
    }

    @Override
    public synchronized boolean checkIfExists(String userName, String password) {
        return accountsSManager.checkIfExists(userName,password);
    }

    @Override
    public boolean checkUsername(String username)throws SQLException {
        return accountsSManager.checkUsername(username);
    }

    @Override
    public void addAccount(Account acc,boolean isManager){
        accountsSManager.addAccount(acc, isManager);

    }

    @Override
    public boolean accountIsManager(String username, String password) {
        System.out.println(accountsSManager.accountIsManager( username,password) + "Server");
        return accountsSManager.accountIsManager( username,password);
    }

    @Override
    public void addModel(ISModel model) {
        sModelsSMnagaer.addModel(model);
    }

    @Override
    public void addSparePart(ISparePart sparePart, ISModel model) {
        sparePartsSManager.addSparePart(sparePart,model);
    }

    @Override
    public void removeSparePart(ISparePart sparePart, ISModel model) {
        sparePartsSManager.removeSparePart(sparePart,model);
    }

    @Override
    public void removeModel(ISModel model) {
        sModelsSMnagaer.removeModel(model);
    }

    @Override
    public ArrayList<SparePart> getAllSpareParts(ISModel model) {
        return sparePartsSManager.getAllSpareParts(model);
    }

    @Override
    public ArrayList<ISModel> getAllModels() throws RemoteException {
        return sModelsSMnagaer.getAllModels();
    }

    @Override
    public void editSparePart(ISparePart part, ISModel model, int quantity, int amountNeeded) throws RemoteException {
        sparePartsSManager.editSparePart(part, model, quantity, amountNeeded);
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

    @Override
    public ArrayList<String> getLogList(ISparePart part, SModel model) throws RemoteException {
      return  logsManager.getLogList(part,model);
    }

    @Override
    public void logToDatabase(String log) throws RemoteException {
            logsManager.logToDatabase(log);
    }

    @Override
    public void wrappLogListener(String names, RemotePropertyChangeListener listener) throws RemoteException {
        logsManager.addListener(names,new RPCLWrapper(listener));
    }

    public void wrappListener(String names, RemotePropertyChangeListener listener) throws RemoteException {
        sparePartsSManager.addListener(names, new RPCLWrapper(listener));
    }

    @Override
    public void incrementSparePartQuantity(ISparePart part, String scooterModel) {
        sparePartsSManager.incrementSparePartQuantity(part,scooterModel);
    }

    @Override
    public void decrementSparePartQuantity(ISparePart part, String scooterModel) {
        sparePartsSManager.decrementSparePartQuantity(part,scooterModel);
    }

}
