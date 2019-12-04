package client.viewmodel.newSparePart;

import client.model.spareParts.IMSparePart;
import client.model.ScooterModels.SModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.rmi.RemoteException;

public class NewSparePartViewModel
{
    private StringProperty partName;
    private IMSparePart newSparePartModel;

    private StringProperty selectedModel;

    public StringProperty getSelectedModel()
    {
        return selectedModel;
    }

    public NewSparePartViewModel(IMSparePart newSparePartModel)
    {
       System.out.println("constructor "+newSparePartModel);
        this.newSparePartModel = newSparePartModel;
        partName = new SimpleStringProperty();
        selectedModel = new SimpleStringProperty();
    }

    public StringProperty getPartNameProperty()
    {
        return partName;
    }

    public void addPart()
    {
        try
        {   System.out.println(newSparePartModel);
            newSparePartModel.addSparepart(partName.getValue(),new SModel(selectedModel.getValue()));

        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }
}
