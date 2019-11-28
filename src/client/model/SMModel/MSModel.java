package client.model.SMModel;

import server.RemoteServer;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class MSModel implements IMSModel, Remote {
    private ISModel scooterModel;
    private ISparePart sparePart;
    private RemoteServer server;


    public MSModel() throws RemoteException, NotBoundException {
        UnicastRemoteObject.exportObject(this,0);
        Registry reg = LocateRegistry.getRegistry("Localhost",1099);
        server = (RemoteServer) reg.lookup("server");
        System.out.println("Connected to Server");
    };

    @Override
    public void addModel(String modelName) throws RemoteException {
        scooterModel = new SModel(modelName);

        server.addModel(scooterModel);
    }

    @Override
    public void removeModel(SModel scooterModel) throws RemoteException {
        server.removeModel(scooterModel);
    }

    public void addSparepart(String name,ISModel model)
    {
        sparePart= new SparePart(name);
        server.addSparePart(sparePart,model);
    }

    public void removeSparepart(String name,ISModel model)
    {
        sparePart= new SparePart(name);
        server.removeSparePart(sparePart,model);
    }

    public ArrayList<SparePart> getAllSpareparts(ISModel model)
    {
        return server.getAllSpareparts(model);
    }


}
