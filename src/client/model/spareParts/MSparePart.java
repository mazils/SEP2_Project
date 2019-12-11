package client.model.spareParts;

import Shared.PropertyChangeSubject;
import client.RPCLImpl;
import client.model.ScooterModels.IMSModel;
import client.model.ScooterModels.ISModel;
import client.model.ScooterModels.SModel;
import javafx.application.Platform;
import server.RemoteServer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class MSparePart implements IMSparePart {
    private ISparePart sparePart;
    private RemoteServer server;


    public MSparePart() throws RemoteException, NotBoundException {
        Registry reg = LocateRegistry.getRegistry("localhost",1099);
        server = (RemoteServer) reg.lookup("server");
        System.out.println("Connected to Server");

    }

    public void addSparepart(String name, ISModel model) throws RemoteException {
        sparePart= new SparePart(name);
        server.addSparePart(sparePart,model);

    }

    @Override
    public void editSparePart(ISparePart part, ISModel model, int quantity, int amountNeeded) throws RemoteException {
        server.editSparePart(part, model, quantity, amountNeeded);
    }

    @Override
    public void incrementSparePartQuantity(ISparePart part, String scooterModel) throws RemoteException
    {
        server.incrementSparePartQuantity(part,scooterModel);
    }

    public void removeSparepart(String name,ISModel model) throws RemoteException {
        sparePart= new SparePart(name);
        server.removeSparePart(sparePart,model);

    }

    public ArrayList<SparePart> getAllSpareparts(ISModel model) throws RemoteException {
        return server.getAllSpareParts(model);
    }

    public void updateView(PropertyChangeEvent evt) throws RemoteException {
        Platform.runLater(() ->{
            try {
                System.out.println("updateView with evt");
                getAllSpareparts((ISModel)evt.getNewValue());

            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void addListener(String names, PropertyChangeListener listener) throws RemoteException {
        server.addListener(names, new RPCLImpl(listener));
    }

    @Override
    public void removeListener(String names, PropertyChangeListener listener) throws RemoteException {

    }
}
