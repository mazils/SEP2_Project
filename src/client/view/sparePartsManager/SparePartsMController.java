package client.view.sparePartsManager;

import client.model.SMModel.*;
import client.view.ViewHandler;
import client.viewmodel.sparePart.ModelsListMViewModel;
import client.viewmodel.sparePart.SparePartViewModel;
import client.viewmodel.sparePartsList.ModelsListMViewModel;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
;import java.rmi.RemoteException;

public class SparePartsMController {
    @FXML
    ListView<SparePart> sparePartsList;

    @FXML
    TextArea textArea;

    @FXML
    ComboBox modelList;

    private ViewHandler viewHandler;
    private ModelsListMViewModel modelsViewModel;
    private SparePartViewModel sparePartsViewModel;
    private StringProperty currentModel;

    public void init(ModelsListMViewModel modelsViewModel, SparePartViewModel sparePartsViewModel, ViewHandler viewHandler){
        this.viewHandler=viewHandler;
        this.modelsViewModel=modelsViewModel;
        this.sparePartsViewModel=sparePartsViewModel;
        currentModel.bindBidirectional(sparePartsViewModel.currentmodelProperty());
        inittialLoad();
        modelsViewModel.updateAllModels();

    }

    public void inittialLoad() {
        sparePartsList.setItems(sparePartsViewModel.getSparePartsProperty());
        sparePartsList.setPlaceholder(new Label("No Content In List"));
        modelList.setItems(modelsViewModel.getModelsProperty());
        modelList.setPlaceholder(new Label("No models to show"));
        modelList.setValue("Choose");

    }

    public void onNewAccount(){
        viewHandler.openView("createAccount");
    }

    public void onNewModel(){
        viewHandler.openView("newModel");
    }

    public void onLogOff(){
        viewHandler.openView("LogIn");
    }


    public void onModelList(ActionEvent actionEvent) {
       currentModel= (StringProperty) modelList.getValue();
        try {
            sparePartsViewModel.getList(currentModel.getValue());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onNewSparePart() {
        viewHandler.openView("newsparepart");
    }

    public void onDeleteSparePart() {
        if(!(modelList.getSelectionModel().isEmpty()) && !(sparePartsList.getSelectionModel().isEmpty()))
        {
            ISModel model= new SModel((String) modelList.getSelectionModel().getSelectedItem());
            try {
                sparePartsViewModel.removeSparePart(sparePartsList.getSelectionModel().getSelectedItem(),model);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void onSubtract(ActionEvent actionEvent) {
    }

    public void onAdd(ActionEvent actionEvent) {
    }

    public void onPlace(ActionEvent actionEvent) {
    }

    public void onRecive(ActionEvent actionEvent) {
    }
}
