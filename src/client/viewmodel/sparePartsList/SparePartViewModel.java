package client.viewmodel.sparePartsList;

import client.model.ScooterModels.*;
import client.model.spareParts.IMSparePart;
import client.model.spareParts.ISparePart;
import client.model.spareParts.SparePart;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;


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
        try{
            model.addListener("editSparePart", evt -> {
                editSparePartEvt(evt);
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
        ArrayList<SparePart> parts= model.getAllSpareparts(scooterModel);
        spareParts.clear();
        if(model.getAllSpareparts(scooterModel)!=null) {
            for (int i = 0; i < parts.size() ; i++) {
                spareParts.add(parts.get(i));
            }
        }
    }

    public void addToSpareParts(SparePart part,ISModel model) {
        if(model.getModelName().equals(currentmodelProperty().getValue())) {
            spareParts.add(part);
        }
    }

    public void removeSparePart(String sparePart,ISModel scootermodel) throws RemoteException {
        model.removeSparepart(sparePart,scootermodel);
    }
    public void editSparePart(ISparePart part, ISModel model) {
        if(model.getModelName().equals(currentmodelProperty().getValue())){
            for (int i = 0; i <spareParts.size() ; i++) {
                if (spareParts.get(i).equals(part.getName())){
                    spareParts.get(i).setQuantity(part.getQuantity());
                    spareParts.get(i).setAmountNeeded(part.getAmountNeeded());
                }
            }
        }
    }

    public void editSparePartEvt(PropertyChangeEvent event) {
        Platform.runLater(() ->{
            SparePart sparePart= (SparePart)event.getOldValue();
            ISModel model= (SModel)event.getNewValue();
            editSparePart(sparePart, model);
        });
    }

    public void addSparePart(PropertyChangeEvent evt) throws RemoteException {
        Platform.runLater(() ->{
            SparePart sparePart= (SparePart)evt.getOldValue();
            ISModel model= (SModel)evt.getNewValue();
            addToSpareParts(sparePart,model);
        });
    }




}
