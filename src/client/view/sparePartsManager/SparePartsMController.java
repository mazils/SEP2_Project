package client.view.sparePartsManager;

import client.model.SMModel.ISModel;
import client.model.SMModel.ISparePart;
import client.model.SMModel.SparePart;
import client.view.ViewHandler;
import client.viewmodel.sparePart.SparePartViewModel;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
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
    private SparePartViewModel viewModel;


    public void init(SparePartViewModel viewModel, ViewHandler viewHandler){
        this.viewHandler=viewHandler;
        this.viewModel=viewModel;
       sparePartsList.setItems(viewModel.getSparePartsProperty());
       sparePartsList.setPlaceholder(new Label("No Content In List"));
        modelList.setItems(viewModel.getModelsProperty());
        if(modelList.getItems().size()==0)
        {
            modelList.setPlaceholder(new Label("no models to show"));

        }
        else {
            modelList.setValue(modelList.getItems().get(0));
        }

    }

    public void onNewAccount(){
        viewHandler.openView("createAccount");
    }

    public void onNewModel(){
        viewModel.setAllModels();
        viewHandler.openView("newModel");

    }

    public void onLogOff(){
        viewHandler.openView("LogIn");
    }


    public void onModelList(ActionEvent actionEvent) {

    }

    public void onNewSparePart(ActionEvent actionEvent) {
//        viewModel.addSparePart(sparePartsList.getItems().get(0));
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
