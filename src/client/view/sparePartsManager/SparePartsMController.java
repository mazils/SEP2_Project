package client.view.sparePartsManager;

import client.model.ScooterModels.*;
import client.model.spareParts.SparePart;
import client.view.ViewHandler;
import client.viewmodel.sparePartsList.ModelsListMViewModel;
import client.viewmodel.sparePartsList.SparePartViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
;import java.rmi.RemoteException;

public class SparePartsMController {
    @FXML
    private TableView<SparePart> sparePartsList;

    @FXML
    private TableColumn<SparePart, String> nameColumn;

    @FXML
    private TableColumn<SparePart, Integer> quantityColumn;

    @FXML
    private TextArea textArea;

    @FXML
    private ComboBox modelList;

    private ViewHandler viewHandler;
    private ModelsListMViewModel modelsViewModel;
    private SparePartViewModel sparePartsViewModel;
    private StringProperty currentModel;

    public void init(ModelsListMViewModel modelsViewModel, SparePartViewModel sparePartsViewModel, ViewHandler viewHandler){
        this.viewHandler=viewHandler;
        this.modelsViewModel=modelsViewModel;
        this.sparePartsViewModel=sparePartsViewModel;
        currentModel= new SimpleStringProperty();
        currentModel.bindBidirectional(sparePartsViewModel.currentmodelProperty());
        inittialLoad();
        modelsViewModel.updateAllModels();

    }

    public void inittialLoad() {
        sparePartsList.setItems(sparePartsViewModel.getSparePartsProperty());
        sparePartsList.setPlaceholder(new Label("No Content In List"));

        nameColumn.setCellValueFactory(new PropertyValueFactory<SparePart, String>("name"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<SparePart, Integer>("quantity"));

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
        Stage stage = (Stage)textArea.getScene().getWindow();
        viewHandler.closeView(stage);
        viewHandler.openView("logIn");
    }


    public void onModelList() {
       currentModel.setValue((String) modelList.getValue());
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

        if(!sparePartsList.getSelectionModel().isEmpty()) {
            currentModel.setValue((String) modelList.getValue());
            SModel model=  new SModel(currentModel.getValue());
            try {
                sparePartsViewModel.removeSparePart(sparePartsList.getSelectionModel().getSelectedItem().getName(),model);// todo should take object of spare part instead of String

                sparePartsViewModel.getList(model.getModelName());
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
