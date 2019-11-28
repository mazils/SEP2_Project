package client.viewmodel.scooterModel;

import client.model.SMModel.IMSModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class NewModelVM {
    private StringProperty modelName;
    private IMSModel model;

    public NewModelVM(IMSModel model)
    {
        this.model= model;
        modelName= new SimpleStringProperty();
    }

    public StringProperty getModelNameProperty()
    {
        return modelName;
    }

    public void addScooterModel()
    {
        model.addModel(modelName.getValue());
    }
}
