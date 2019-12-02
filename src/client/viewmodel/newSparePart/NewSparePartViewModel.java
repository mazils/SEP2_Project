package client.viewmodel.newSparePart;

import client.model.SMModel.IMSparePart;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class NewSparePartViewModel
{
    private StringProperty partName;
    private IMSparePart newSparePartModel;

    public NewSparePartViewModel(IMSparePart newSparePartModel)
    {
        this.newSparePartModel = newSparePartModel;
        partName = new SimpleStringProperty();
    }

    public StringProperty getPartNameProperty()
    {
        return partName;
    }

    public void addPartButton()
    {
//        newSparePartModel.addSparepart(partName,);
    }
}
