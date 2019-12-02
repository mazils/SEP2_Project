package Shared;

import java.beans.PropertyChangeListener;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PropertyChangeSubject extends Remote {
    public void addListener(String names,PropertyChangeListener listener) throws RemoteException;

}
