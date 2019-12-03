package client.viewmodel.sparePartsList;

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
    private ObservableList<String> spareParts;
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

    public ObservableList<String> getSparePartsProperty() {
        return spareParts;
    }

    public void getList(String name) throws RemoteException {
        SModel scooterModel= new SModel(name);
        ArrayList<SparePart> parts= model.getAllSpareparts(scooterModel);
        spareParts.clear();
        if(model.getAllSpareparts(scooterModel)!=null) {
            for (int i = 0; i < parts.size() ; i++) {
                spareParts.add(parts.get(i).getName());
            }
        }
    }

    public void addToSpareParts(SparePart part,ISModel model)
    {
        if(model.getModelName().equals(currentmodelProperty().getValue()))
        {
            spareParts.add(part.getName());
        }
    }

    public void removeSparePart(String sparePart,ISModel scootermodel) throws RemoteException {
        model.removeSparepart(sparePart,scootermodel);
    }

    public void addSparePart(PropertyChangeEvent evt) throws RemoteException {
        Platform.runLater(() ->{
            SparePart sparePart= (SparePart)evt.getOldValue();
            ISModel model= (SModel)evt.getNewValue();
            addToSpareParts(sparePart,model);
        });
    }




}
