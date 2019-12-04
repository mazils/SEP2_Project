package client.view.sparePartsVOS;

import client.model.spareParts.SparePart;
import client.view.ViewHandler;
import client.viewmodel.sparePartsList.ModelsListMViewModel;


import client.viewmodel.sparePartsList.SparePartViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.rmi.RemoteException;

public class SparePartsVOSController {
    @FXML
    private TableView<SparePart> sparePartsList;

    @FXML
    private TableColumn<SparePart, String> nameColumn;

    @FXML
    private TableColumn<SparePart, Integer> quantityColumn;

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
        currentModel = new SimpleStringProperty();
        initialLoad();
        modelsViewModel.updateAllModels();
        currentModel.bindBidirectional(sparePartsViewModel.currentmodelProperty());
    }

    public void initialLoad() {
        sparePartsList.setItems(sparePartsViewModel.getSparePartsProperty());
        sparePartsList.setPlaceholder(new Label("No content in list"));
        modelList.setItems(modelsViewModel.getModelsProperty());
        modelList.setPlaceholder(new Label("No models to show"));
        modelList.setValue("Choose");
    }

    public void onLogOff()
    {
        viewHandler.openView("logIn");
    }

    public void onSubtract(ActionEvent actionEvent) {
    }

    public void onAdd(ActionEvent actionEvent) {
    }

    public void onModelList() {
        currentModel.setValue((String) modelList.getValue());
        try {
            sparePartsViewModel.getList(currentModel.getValue());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
