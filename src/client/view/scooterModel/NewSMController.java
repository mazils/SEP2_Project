package client.view.scooterModel;

import client.view.ViewHandler;
import client.viewmodel.scooterModel.NewModelVM;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.rmi.RemoteException;


public class NewSMController {
@FXML
private TextField newModelName;

private NewModelVM viewModel;
private ViewHandler viewHandler;

public void init(ViewHandler viewHandler, NewModelVM viewModel){
    this.viewHandler=viewHandler;
    this.viewModel=viewModel;

    viewModel.getModelNameProperty().bindBidirectional(newModelName.textProperty());
}

public void onSaveButton() throws RemoteException {
    viewModel.addScooterModel();
    Stage stage = (Stage)newModelName.getScene().getWindow();
    viewHandler.closeView(stage);
}

}
