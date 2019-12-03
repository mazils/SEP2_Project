package client.view.newSparePart;

import client.view.ViewHandler;
import client.viewmodel.newSparePart.NewSparePartViewModel;
import client.viewmodel.sparePartsList.ModelsListMViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewSparePartController
{
    @FXML
    private TextField partNameTextField;

    @FXML
    private ComboBox<String> modelList;

    private ViewHandler viewHandler;

    private NewSparePartViewModel newSparePartViewModel;
    private ModelsListMViewModel modelsListMViewModel;


    public void init(ViewHandler viewHandler, NewSparePartViewModel newSparePartViewModel, ModelsListMViewModel modelsListMViewModel)
    {

        this.modelsListMViewModel = modelsListMViewModel;
        this.viewHandler = viewHandler;
        this.newSparePartViewModel = newSparePartViewModel;
        System.out.println("*******"+ this.newSparePartViewModel);
        this.newSparePartViewModel.getPartNameProperty().bindBidirectional(partNameTextField.textProperty());
        modelList.setItems(modelsListMViewModel.getModelsProperty());

    }

    public void onAddPartButton()
    {
        String value = modelList.getValue();
        StringProperty modelList = new SimpleStringProperty(value);
        System.out.println("onAddPArtButton controller: " + this.modelList.getValue());


        this.newSparePartViewModel.getSelectedModel().bindBidirectional(modelList);
        Stage stage = (Stage) partNameTextField.getScene().getWindow(); // getting the stage
        newSparePartViewModel.addPartButton();
        viewHandler.closeView(stage);
    }

}
