package client.view.newSparePart;

import client.view.ViewHandler;
import client.viewmodel.newSparePart.NewSparePartViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewSparePartController
{
    @FXML
    private TextField partNameTextField;

    private ViewHandler viewHandler;
    private NewSparePartViewModel newSparePartViewModel;

    public void init(ViewHandler viewHandler, NewSparePartViewModel newSparePartViewModel)
    {
        System.out.println(partNameTextField);
        this.viewHandler = viewHandler;
        this.newSparePartViewModel = newSparePartViewModel;
        this.newSparePartViewModel.getPartNameProperty().bindBidirectional(partNameTextField.textProperty());
    }

    public void onAddPartButton()
    {
        Stage stage = (Stage) partNameTextField.getScene().getWindow(); // getting the stage
        newSparePartViewModel.addPartButton();
        stage.close();
    }

}
