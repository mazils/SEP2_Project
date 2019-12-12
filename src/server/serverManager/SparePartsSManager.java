package server.serverManager;

import Shared.RemotePropertyChangeListener;
import Shared.remoteServer.SparePartsServer;
import client.model.ScooterModels.ISModel;
import client.model.spareParts.ISparePart;
import client.model.spareParts.SparePart;
import server.RPCLWrapper;
import server.jdbc.SparePartsJDBC;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class SparePartsSManager  {
    private SparePartsJDBC sparePartsJDBC;

    public SparePartsSManager() {
        sparePartsJDBC = new SparePartsJDBC();
    }

    public void addSparePart(ISparePart sparePart, ISModel model) {
        sparePartsJDBC.addSparePart(sparePart,model);
    }

    public void removeSparePart(ISparePart sparePart, ISModel model) {
        sparePartsJDBC.removeSparePart(sparePart,model);
    }

    public ArrayList<SparePart> getAllSpareParts(ISModel model) {

        ArrayList<SparePart> partArrayList= new ArrayList<>();
        try {
            partArrayList= sparePartsJDBC.getAllSpareParts(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return partArrayList;
    }

    public void editSparePart(ISparePart part, ISModel model, int quantity, int amountNeeded) throws RemoteException {
        sparePartsJDBC.editSparePart(part, model, quantity, amountNeeded);
    }

    public void incrementSparePartQuantity(ISparePart part, String scooterModel) {
        sparePartsJDBC.incrementSparePartQuantity(part,scooterModel);
    }

    public void decrementSparePartQuantity(ISparePart part, String scooterModel) {
        sparePartsJDBC.decrementSparePartQuantity(part,scooterModel);
    }

    public void addListener(String names, PropertyChangeListener listener) throws RemoteException {
        sparePartsJDBC.addListener(names, listener);
    }


}
