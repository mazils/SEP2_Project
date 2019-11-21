package client.view.createaccount;

import client.view.ViewHandler;
import client.viewmodel.createAccount.CreateAccountViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.CheckBox;


public class CreateAccountController {
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

    public void init(ViewHandler viewHandler, CreateAccountViewModel createAccountViewModel){
        handler=viewHandler;
        viewModel=createAccountViewModel;

        viewModel.getUserNameProperty().bindBidirectional(userNameTextField.textProperty());
        viewModel.getPasswordProperty().bindBidirectional(passwordConfTextField.textProperty());
        viewModel.getConfPasswordProperty().bindBidirectional(passwordConfTextField.textProperty());
        viewModel.getIsManager().bindBidirectional(isManager.borderProperty());

    }

    public void onCreateButton(){
        Stage stage = (Stage) userNameTextField.getScene().getWindow();
        viewModel.createAccount(stage);
    }
}
