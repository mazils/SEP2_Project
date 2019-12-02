package client.view.newSparePart;

import client.view.ViewHandler;
import client.viewmodel.newSparePart.NewSparePartViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewSparePartController
{
    @FXML
    private TextField partNameTextField;
    private Button addPartButton;
    private ViewHandler viewHandler;
    private NewSparePartViewModel newSparePartViewModel;
    private Stage stage = (Stage) addPartButton.getScene().getWindow(); // getting the stage

    public void init(ViewHandler viewHandler, NewSparePartViewModel newSparePartViewModel)
    {
        this.viewHandler = viewHandler;
        this.newSparePartViewModel = newSparePartViewModel;
        this.newSparePartViewModel.getPartNameProperty().bindBidirectional(partNameTextField.textProperty());
    }

    public void onAddPartButton()
    {
        newSparePartViewModel.addPartButton();
        stage.close();
    }

}
