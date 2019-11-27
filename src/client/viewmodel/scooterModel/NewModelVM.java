package client.viewmodel.scooterModel;

import client.model.SMModel.IMSModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class NewModelVM {
    private SimpleStringProperty modelName;
    private IMSModel model;

    public NewModelVM(IMSModel model)
    {
        this.model= model;
        this.modelName = new SimpleStringProperty();
    }

    public StringProperty getModelName()
    {
        return modelName;
    }

    public void addNewModel()
    {
        model.addModel(modelName.getValue());
    }


}
