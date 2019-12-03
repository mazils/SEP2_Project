package client.viewmodel.sparePart;

import client.model.SMModel.*;
import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;


public class SparePartViewModel {
    private IMSparePart model;
    private ObservableList<SparePart> spareParts;
    private StringProperty currentmodel;


    public SparePartViewModel (IMSparePart model) {

        this.model= model;
        currentmodel= new SimpleStringProperty();
        spareParts= FXCollections.observableArrayList();
        try {
            model.addListener("addedsparepart", evt -> {
                try {
                    addSparePart(evt);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }


    }

    public StringProperty currentmodelProperty() {
        return currentmodel;
    }

    public ObservableList<SparePart> getSparePartsProperty() {
        return spareParts;
    }

    public void getList(String name) throws RemoteException {
        SModel scooterModel= new SModel(name);
        Iterator<SparePart> sparePartIterator= model.getAllSpareparts(scooterModel).iterator();
        while (sparePartIterator.hasNext())
        {
            spareParts.add(sparePartIterator.next());
        }
    }

    public void addToSpareParts(SparePart part,ISModel model)
    {
        if(model.getModelName().equals(currentmodelProperty().getValue()))
        {
            spareParts.add(part);
        }
    }

    public void removeSparePart(ISparePart sparePart,ISModel scootermodel) throws RemoteException {
        model.removeSparepart(sparePart.getName(),scootermodel);
    }

    public void addSparePart(PropertyChangeEvent evt) throws RemoteException {
        Platform.runLater(() ->{
            SparePart sparePart= (SparePart)evt.getOldValue();
            ISModel model= (SModel)evt.getOldValue();
            spareParts.add(sparePart);
        });
    }




}
