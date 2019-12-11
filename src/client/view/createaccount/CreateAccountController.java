package client.view.createaccount;

import client.view.ViewHandler;
import client.viewmodel.createAccount.CreateAccountViewModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.CheckBox;

import java.rmi.RemoteException;
import java.sql.SQLException;


public class CreateAccountController
{
    @FXML
    TextField userNameTextField;

    @FXML
    TextField passwordTextField;

    @FXML
    TextField passwordConfTextField;

    @FXML
    CheckBox isManager;

    private ViewHandler handler;
    private CreateAccountViewModel viewModel;

    public void init(ViewHandler viewHandler, CreateAccountViewModel createAccountViewModel)
    {
        handler = viewHandler;
        viewModel = createAccountViewModel;

        viewModel.getUsername().bindBidirectional(userNameTextField.textProperty());
        viewModel.getPassword().bindBidirectional(passwordTextField.textProperty());
        viewModel.getConfirmPassword().bindBidirectional(passwordConfTextField.textProperty());
        viewModel.getIsManagerProperty().bindBidirectional(isManager.selectedProperty());
        viewModel.alertTypeProperty().addListener((observableValue, number, type) -> showAlert(type));
    }

    private void showAlert(Number type)
    {
        int value = type.intValue();
        Alert.AlertType t = (Alert.AlertType.values()[value]) ;
        Alert alert = new Alert(t);
        alert.setContentText("Please fill in all fields");
        alert.showAndWait();
    }

    public void onCreateButton() throws RemoteException, SQLException
    {
        Stage stage = (Stage) userNameTextField.getScene().getWindow();
        viewModel.createAccount();
        if (viewModel.isRightAccountProperty().getValue())
        {
            handler.closeView(stage);
        }
    }
}
