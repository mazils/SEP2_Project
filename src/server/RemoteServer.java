package server;

import client.model.SMModel.ISModel;
import client.model.SMModel.ISparePart;
import client.model.SMModel.SparePart;
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
    ArrayList<SparePart> getAllSpareparts(ISModel model)throws RemoteException;
    ArrayList<ISModel> getAllModels() throws RemoteException;
}
