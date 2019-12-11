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
import java.io.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class MSparePart implements IMSparePart {
    private ISparePart sparePart;
    private RemoteServer server;
    private BufferedWriter order;


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
            order = new BufferedWriter(new FileWriter("order.txt",true));
            System.out.println("write file");
            order.write("      SCOOTER MODEL : "+model.getModelName() + "\n");
            for(ISparePart i : getAllSpareparts(model))
            {
                if(i.getAmountNeeded() != 0)
                {
                    order.write( " spare part : " +i.getName() + " ammount needed : "+i.getAmountNeeded() + "\n");
                }

            }
            order.write("COMMENTS : " + "\n"+comments);
            System.out.println("done writing order");
            order.close();

        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
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
