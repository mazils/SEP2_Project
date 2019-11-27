package client.model.SMModel;

import server.RemoteServer;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

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
    public void addModel(String modelName) {
        scooterModel = new SModel(modelName);

        server.addModel(scooterModel);
    }

    @Override
    public void removeModel(SModel scooterModel) {
        server.removeModel(scooterModel);
    }
}
