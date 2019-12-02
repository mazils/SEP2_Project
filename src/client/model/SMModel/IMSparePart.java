package client.model.SMModel;

import Shared.PropertyChangeSubject;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IMSparePart extends Remote, PropertyChangeSubject {
    void removeSparepart(String name,ISModel model)throws RemoteException;
    ArrayList<SparePart> getAllSpareparts(ISModel model)throws RemoteException;
    void addSparepart(String name,ISModel model)throws RemoteException;
}
