package server;

import client.model.SMModel.ISModel;
import client.model.SMModel.ISparePart;
import client.model.SMModel.SparePart;
import client.model.modelaccount.Account;

import java.rmi.Remote;
import java.sql.SQLException;
import java.util.ArrayList;

public interface RemoteServer extends Remote {
    boolean checkIfExists(String userName,String password) ;
    boolean checkUsername(String username) throws  SQLException;
    void addAccount(Account acc,boolean isManager);
    void addModel(ISModel model);
    void addSparePart(ISparePart sparePart,ISModel model);
    void removeSparePart(ISparePart sparePart,ISModel model);
    void removeModel(ISModel model);
    ArrayList<SparePart> getAllSpareparts(ISModel model);
}
