package client.model.SMModel;

import Shared.PropertyChangeSubject;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IMSModel extends Remote, PropertyChangeSubject {
    void addModel(String modelName)throws RemoteException;
    void removeModel(SModel scooterModel)throws RemoteException;
    ArrayList<ISModel> getAllModels()throws RemoteException;
}
