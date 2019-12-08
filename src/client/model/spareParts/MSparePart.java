package client.model.spareParts;

import client.model.ScooterModels.ISModel;
import server.RemoteServer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class MSparePart implements IMSparePart {
    private ISparePart sparePart;
    private RemoteServer server;
    private PropertyChangeSupport support= new PropertyChangeSupport(this);

    public MSparePart() throws RemoteException, NotBoundException {
        UnicastRemoteObject.exportObject(this,0);
        Registry reg = LocateRegistry.getRegistry("localhost",1099);
        server = (RemoteServer) reg.lookup("server");
        System.out.println("Connected to Server");
    }

    public void addSparepart(String name, ISModel model) throws RemoteException {
        sparePart= new SparePart(name);
        server.addSparePart(sparePart,model);
        support.firePropertyChange("addedsparepart",sparePart,model);



    }

    @Override
    public void editSparePart(ISparePart part, ISModel model, int quantity, int amountNeeded) throws RemoteException {
        server.editSparePart(part, model, quantity, amountNeeded);
        // observer? im not sure
    }

    public void removeSparepart(String name,ISModel model) throws RemoteException {
        sparePart= new SparePart(name);
        server.removeSparePart(sparePart,model);

    }

    public ArrayList<SparePart> getAllSpareparts(ISModel model) throws RemoteException {
        return server.getAllSpareParts(model);
    }
    @Override
    public void addListener(String names, PropertyChangeListener listener) {
        support.addPropertyChangeListener(names,listener);
    }
}
