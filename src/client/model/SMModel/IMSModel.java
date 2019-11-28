package client.model.SMModel;

import java.util.ArrayList;

public interface IMSModel {
    void addModel(String modelName);
    void removeModel(SModel scooterModel);
    void removeSparepart(String name,ISModel model);
    ArrayList<SparePart> getAllSpareparts(ISModel model);
    void addSparepart(String name,ISModel model);
}
