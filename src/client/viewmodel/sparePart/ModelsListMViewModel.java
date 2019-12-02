package client.viewmodel.sparePart;

import client.model.SMModel.IMSModel;
import client.model.SMModel.ISModel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.PropertyChangeEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class ModelsListMViewModel {
    private IMSModel model;
    private ObservableList<String> models;
    public ObservableList<String> getModelsProperty() {
        return models;
    }

    public ModelsListMViewModel(IMSModel model){
        this.model=model;
        models= FXCollections.observableArrayList();
        try {
            model.addListener("addedModel",evt -> updateModels(evt));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
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
