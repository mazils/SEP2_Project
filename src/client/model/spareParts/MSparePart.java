package client.model.spareParts;

import client.RPCLImpl;
import client.model.ScooterModels.ISModel;
import Shared.remoteServer.SparePartsServer;

import java.beans.PropertyChangeListener;
import java.io.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class MSparePart implements IMSparePart {
    private ISparePart sparePart;
    private SparePartsServer server;

    private IFileWriter fileWrite;


    public MSparePart() throws RemoteException, NotBoundException {
        Registry reg = LocateRegistry.getRegistry("localhost",1099);
        server = (SparePartsServer) reg.lookup("server");
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
    public void incrementSparePartQuantity(ISparePart part, String scooterModel)
    {
        try
        {
            server.incrementSparePartQuantity(part,scooterModel);
        } catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void decrementSparePartQuantity(ISparePart part, String scooterModel)
    {
        try
        {
            server.decrementSparePartQuantity(part,scooterModel);
        } catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void placeOrder(ISModel model,String comments)
    {
        try
        {
            fileWrite = new FileWrite(getAllSpareparts(model));
            fileWrite.createOrder(model,comments);
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }

    }

    public void removeSparepart(String name,ISModel model) throws RemoteException {
        sparePart= new SparePart(name);
        server.removeSparePart(sparePart,model);

    }

    public ArrayList<SparePart> getAllSpareparts(ISModel model) throws RemoteException {
        return server.getAllSpareParts(model);
    }



    @Override
    public void addListener(String names, PropertyChangeListener listener){
        try {
            server.wrappListener(names, new RPCLImpl(listener));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeListener(String names, PropertyChangeListener listener) throws RemoteException {

    }
}
