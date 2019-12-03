package client.viewmodel.sparePartsList;

import client.model.SMModel.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.rmi.RemoteException;
import java.util.Iterator;


public class SparePartViewModel {
    private IMSparePart model;
    private ObservableList<SparePart> spareParts;


    public SparePartViewModel (IMSparePart model) {

        this.model= model;
        spareParts= FXCollections.observableArrayList();

    }
    public ObservableList<SparePart> getSparePartsProperty() {
        return spareParts;
    }

    public void getList(ISModel scooterModel) throws RemoteException {
        Iterator<SparePart> sparePartIterator= model.getAllSpareparts(scooterModel).iterator();
        while (sparePartIterator.hasNext())
        {
            spareParts.add(sparePartIterator.next());
        }
    }

    public void removeSparePart(ISparePart sparePart,ISModel scootermodel) throws RemoteException {
        model.removeSparepart(sparePart.getName(),scootermodel);
    }

    public void addSparePart(ISparePart sparePart,ISModel scootermodel) throws RemoteException {
        model.addSparepart(sparePart.getName(),scootermodel);
    }




}
