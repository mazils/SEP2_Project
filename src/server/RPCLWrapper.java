package server;

import Shared.RemotePropertyChangeListener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.Remote;
import java.rmi.RemoteException;

public class RPCLWrapper implements PropertyChangeListener {
    private RemotePropertyChangeListener remotePropertyChangeListener;

    public RPCLWrapper(RemotePropertyChangeListener remotePropertyChangeListener){
        this.remotePropertyChangeListener=remotePropertyChangeListener;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        try {
            remotePropertyChangeListener.propertyChange(evt);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}
