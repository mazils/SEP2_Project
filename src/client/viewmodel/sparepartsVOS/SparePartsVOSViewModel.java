//package client.viewmodel.sparepartsVOS;
//
//import client.model.ScooterModels.ISModel;
//import client.model.ScooterModels.SModel;
//import client.model.spareParts.IMSparePart;
//import client.model.spareParts.ISparePart;
//import client.model.spareParts.SparePart;
//import javafx.application.Platform;
//import javafx.beans.property.SimpleStringProperty;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//
//
//import java.beans.PropertyChangeEvent;
//import java.rmi.RemoteException;
//import java.util.ArrayList;
//
//
//public class SparePartsVOSViewModel
//{
//    private ObservableList<SparePart> spareParts;
//    private SimpleStringProperty selectedScooterModel;
//    private IMSparePart imSparePart;
//
//    public SparePartsVOSViewModel(IMSparePart imSparePart)
//    {
//        selectedScooterModel = new SimpleStringProperty();
//        this.imSparePart = imSparePart;
//        spareParts = FXCollections.observableArrayList();
//        try
//        {
//            imSparePart.addListener("change", evt ->
//            {
//                try
//                {
//                    getList(selectedScooterModel.getValue());
//                } catch (RemoteException e)
//                {
//                    e.printStackTrace();
//                }
//            });
//        } catch (RemoteException e)
//        {
//            e.printStackTrace();
//        }
//
//    }
//
//    public void addSparePart(PropertyChangeEvent evt) throws RemoteException
//    {
//        Platform.runLater(() ->
//        {
//            SparePart sparePart = (SparePart) evt.getOldValue();
//            ISModel model = (SModel) evt.getNewValue();
//            addToSpareParts(sparePart, model);
//        });
//    }
//
//    public void incrementPart(ISparePart iSparePart, String scooterModel)
//    {
//        imSparePart.incrementSparePartQuantity(iSparePart, scooterModel);//todo here
//
//    }
//    public void decrementPart(ISparePart iSparePart, String scooterModel)
//    {
//        imSparePart.decrementSparePartQuantity(iSparePart,scooterModel);
//    }
//
//
//    public void getList(String name) throws RemoteException
//    {
//        SModel scooterModel = new SModel(name);
//        ArrayList<SparePart> parts = imSparePart.getAllSpareparts(scooterModel);
//        spareParts.clear();
//        if (imSparePart.getAllSpareparts(scooterModel) != null)
//        {
//            for (int i = 0; i < parts.size(); i++)
//            {
//                spareParts.add(parts.get(i));
//                System.out.println(parts.get(i));
//            }
//
//        }
//    }
//
//    public ObservableList<SparePart> getSpareParts()
//    {
//        return spareParts;
//    }
//
//    public SimpleStringProperty modelProperty()
//    {
//        return selectedScooterModel;
//    }
//
//
//    public void addToSpareParts(SparePart part, ISModel model)
//    {
//        if (model.getModelName().equals(selectedScooterModel.getValue()))
//        {
//            spareParts.add(part);
//        }
//    }
//
//}