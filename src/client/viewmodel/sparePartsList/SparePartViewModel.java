package client.viewmodel.sparePartsList;

import client.RPCLImpl;
import client.model.ScooterModels.*;
import client.model.spareParts.IMSparePart;
import client.model.spareParts.ISparePart;
import client.model.spareParts.SparePart;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
            model.addListener("change", evt -> {
                try {
                    System.out.println("lambda in MSparePart: ");
                    getList(currentmodel.getValue());
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
        try {
           ArrayList<SparePart> scooterSpareParts= this.model.getAllSpareparts(model);

            for (int i = 0; i <scooterSpareParts.size() ; i++) {
                if (scooterSpareParts.get(i).getName().equals(part.getName())){
                    spareParts.get(i).setQuantity(part.getQuantity());
                    spareParts.get(i).setAmountNeeded(part.getAmountNeeded());
                    this.model.editSparePart(spareParts.get(i),model,spareParts.get(i).getQuantity(),spareParts.get(i).getAmountNeeded());
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }


    }




}
