package client.viewmodel.newSparePart;

import client.model.SMModel.IMSparePart;
import client.model.SMModel.SModel;
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

    public StringProperty selectedModelProperty()
    {
        return selectedModel;
    }

    public NewSparePartViewModel(IMSparePart newSparePartModel)
    {
//        System.out.println("++++++++++"+newSparePartModel);
        this.newSparePartModel = newSparePartModel;
        partName = new SimpleStringProperty();
        selectedModel = new SimpleStringProperty();
    }

    public StringProperty getPartNameProperty()
    {
        return partName;
    }

    public void addPartButton()
    {
        try
        {   System.out.println("SELECTED MODEL:"+ selectedModel.getValue()+"\n" +partName.getValue() + "newsparepartsmodel: " + newSparePartModel);
            newSparePartModel.addSparepart(partName.getValue(),new SModel(selectedModel.getValue()));

        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }
}
