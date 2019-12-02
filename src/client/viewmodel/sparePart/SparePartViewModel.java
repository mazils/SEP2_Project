package client.viewmodel.sparePart;

import client.model.SMModel.IMSModel;
import client.model.SMModel.ISModel;
import client.model.SMModel.ISparePart;
import client.model.SMModel.SparePart;
import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;


public class SparePartViewModel {
    private IMSModel model;
    private ObservableList<SparePart> spareParts;
    private ObservableList<String> models;

    public SparePartViewModel (IMSModel model)
    {
        this.model= model;
        spareParts= FXCollections.observableArrayList();
        models= FXCollections.observableArrayList();
        try {
            model.addListener("addedModel",evt -> updateModels(evt));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public ObservableList<SparePart> getSparePartsProperty() {
        return spareParts;
    }

    public ObservableList<String> getModelsProperty() {
        return models;
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

    public void updateAllModels()
    {
            ArrayList<ISModel> modelcollection= new ArrayList<>();
            try {
                modelcollection= model.getAllModels();
            } catch (RemoteException e) {
                e.printStackTrace();
            }

            for(int i=0;i<modelcollection.size();i++)
            {
                ISModel model= modelcollection.get(i);
                if(!(models.contains(model.getModelName())))
                {
                    models.add(model.getModelName());
                }
            }
    }

    public void updateModels(PropertyChangeEvent evt)
    {
        Platform.runLater(() ->{
            models.add((String) evt.getNewValue());
        });
    }


}
