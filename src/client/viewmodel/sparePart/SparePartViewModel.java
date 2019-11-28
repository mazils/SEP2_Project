package client.viewmodel.sparePart;

import client.model.SMModel.IMSModel;
import client.model.SMModel.ISModel;
import client.model.SMModel.ISparePart;
import client.model.SMModel.SparePart;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Iterator;


public class SparePartViewModel {
    private IMSModel model;
    private ListProperty<ISparePart> spareParts;
    private ListProperty<ISModel> models;

    public SparePartViewModel (IMSModel model)
    {
        this.model= model;
        spareParts= new SimpleListProperty<>();
        models= new SimpleListProperty<>();
    }
    public ObservableList<ISparePart> getSparePartsProperty() {
        return spareParts;
    }

    public ObservableList<ISModel> getModelsProperty() {
        return models;
    }

    public void getList(ISModel scooterModel)
    {
        Iterator<SparePart> sparePartIterator= model.getAllSpareparts(scooterModel).iterator();
        while (sparePartIterator.hasNext())
        {
            spareParts.add(sparePartIterator.next());
        }
    }

    public void removeSparePart(ISparePart sparePart,ISModel scootermodel)
    {
        model.removeSparepart(sparePart.getName(),scootermodel);
    }


}
