package client.model.SMModel;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IMSModel extends Remote,PropertyChangeSubject {
    void addModel(String modelName)throws RemoteException;
    void removeModel(SModel scooterModel)throws RemoteException;
    void removeSparepart(String name,ISModel model)throws RemoteException;
    ArrayList<SparePart> getAllSpareparts(ISModel model)throws RemoteException;
    ArrayList<ISModel> getAllModels()throws RemoteException;
    void addSparepart(String name,ISModel model)throws RemoteException;
}
