package client.view.sparePartsManager;

import client.model.SMModel.SparePart;
import client.view.ViewHandler;
import client.viewmodel.sparePartsList.ModelsListMViewModel;
import client.viewmodel.sparePartsList.SparePartViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
;

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

    public void init(ModelsListMViewModel modelsViewModel, SparePartViewModel sparePartsViewModel, ViewHandler viewHandler){
        this.viewHandler=viewHandler;
        this.modelsViewModel=modelsViewModel;
        this.sparePartsViewModel=sparePartsViewModel;
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

    public void onLogOff() {
        viewHandler.openView("logIn");
    }


    public void onModelList(ActionEvent actionEvent) {

    }

    public void onNewSparePart()
    {
        viewHandler.openView("newsparepart");
    }

    public void onDeleteSparePart(ActionEvent actionEvent) {
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
