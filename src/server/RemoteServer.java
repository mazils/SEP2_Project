package server;

import Shared.RemotePropertyChangeListener;
import client.model.ScooterModels.ISModel;
import client.model.spareParts.ISparePart;
import client.model.spareParts.SparePart;
import client.model.modelaccount.Account;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface RemoteServer extends Remote {
    boolean checkIfExists(String userName,String password)throws RemoteException;
    boolean checkUsername(String username) throws  SQLException,RemoteException;
    void addAccount(Account acc,boolean isManager)throws RemoteException;
    void addModel(ISModel model)throws RemoteException;
    void addSparePart(ISparePart sparePart,ISModel model)throws RemoteException;
    void removeSparePart(ISparePart sparePart,ISModel model)throws RemoteException;
    void removeModel(ISModel model)throws RemoteException;
    ArrayList<SparePart> getAllSpareParts(ISModel model)throws RemoteException;
    ArrayList<ISModel> getAllModels() throws RemoteException;
    void editSparePart(ISparePart part, ISModel model, int quantity, int amountNeeded) throws RemoteException;
    void addListener(String names, RemotePropertyChangeListener listener) throws RemoteException;
}
