package client.view.sparePartsVOS;

import client.model.spareParts.SparePart;
import client.view.ViewHandler;
import client.viewmodel.sparePartsList.ModelsListMViewModel;


import client.viewmodel.sparePartsList.SparePartViewModel;
import client.viewmodel.sparepartsVOS.SparePartsVOSViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.rmi.RemoteException;

public class SparePartsVOSController {
    @FXML
    private TableView<SparePart> sparePartsList;

    @FXML
    private TableColumn<SparePart, String> nameColumn;

    @FXML
    private TableColumn<SparePart, Integer> quantityColumn;

    @FXML
    ComboBox <String>modelList;

    private ViewHandler viewHandler;
    private ModelsListMViewModel modelsViewModel;
    private SparePartViewModel sparePartsViewModel;

    private StringProperty currentModel;

    public void init(ModelsListMViewModel modelsViewModel, SparePartViewModel sparePartsViewModel, ViewHandler viewHandler, SparePartsVOSViewModel sparePartsVOSViewModel){
        this.viewHandler=viewHandler;
        this.modelsViewModel=modelsViewModel;
        this.sparePartsViewModel = sparePartsVOSViewModel;

        currentModel = new SimpleStringProperty();
        initialLoad();
        modelsViewModel.updateAllModels();
        currentModel.bindBidirectional(sparePartsViewModel.currentmodelProperty());//todo
        currentModel.bindBidirectional(sparePartsVOSViewModel.currentmodelProperty());
    }

    public void initialLoad() {
        sparePartsList.setItems(sparePartsViewModel.getSpareParts());
        sparePartsList.setPlaceholder(new Label("No content in list"));
        modelList.setItems(modelsViewModel.getModelsProperty());
        modelList.setPlaceholder(new Label("No models to show"));
        modelList.setValue("Choose");
        initCols();
    }

    public void initCols(){
        nameColumn.setCellValueFactory(new PropertyValueFactory<SparePart, String>("name"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<SparePart, Integer>("quantity"));

    }

    public void onLogOff()
    {
        viewHandler.openView("logIn");
    }

    public void onSubtract()
    {
        sparePartsViewModel.decrementPart(sparePartsList.getSelectionModel().getSelectedItem(),modelList.getValue());
    }

    public void onAdd()
    {
        sparePartsViewModel.incrementPart(sparePartsList.getSelectionModel().getSelectedItem(),modelList.getValue());
        System.out.println(sparePartsList.getSelectionModel().getSelectedItem());
    }

    public void onModelList() {
        currentModel.setValue(modelList.getValue());
        try {
            sparePartsViewModel.getList(currentModel.getValue());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
