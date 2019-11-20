package server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

public class Server implements RemoteServer
{
    // private ISM sparePartsModel
    // private IMM modelsModel
    private JDBC jdbc;



    public Server() {
        jdbc = JDBC.getInstance();
        try{
            UnicastRemoteObject.exportObject(this,0);
            System.out.println("Server started");
        }catch (RemoteException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public synchronized boolean checkIfExists(String userName, String password) {
        try {
            return jdbc.checkAccExists(userName,password);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        JDBC database= JDBC.getInstance();
        Thread nana = new Thread(database);
        nana.start();
        Registry registry;
        try{
            registry = LocateRegistry.createRegistry(1099);
            Server server = new Server();
            registry.bind("server", server);
        }catch(RemoteException | AlreadyBoundException e){
            throw new RuntimeException(e);
        }
    }


}
