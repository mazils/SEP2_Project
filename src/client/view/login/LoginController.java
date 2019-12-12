package client.view.login;

import client.view.ViewHandler;
import client.viewmodel.logIn.LoginViewModel;
import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.rmi.RemoteException;


public class LoginController {

    private ViewHandler viewHandler;
    private LoginViewModel loginViewModel;

    @FXML
    private TextField userNameTextField;

    @FXML
    private TextField passwordTextField;

    /** initial method for controller, takes parameters and do binding
     * @parameter view model of log in, view handler
     */
    public void init(LoginViewModel viewModel, ViewHandler viewHandler){
        loginViewModel=viewModel;
        this.viewHandler=viewHandler;

        loginViewModel.getUserNameProperty().bindBidirectional(userNameTextField.textProperty());
        loginViewModel.getPasswordProperty().bindBidirectional(passwordTextField.textProperty());
    }

    /** execute the login function by view model
     */
    public void onLogInButton() throws RemoteException {
        Stage stage= (Stage) passwordTextField.getScene().getWindow();

        if(loginViewModel.checkIfExists()) {
            System.out.println("dkdbdj");
            viewHandler.closeView(stage);
            System.out.println(loginViewModel.isManager() + "ddfsgs");
            if(loginViewModel.isManager()) {
                viewHandler.openView("sparePartsManager");
            }else
                viewHandler.openView("sparepartsVOS");
        }
        else {
            passwordTextField.textProperty().setValue("");
            userNameTextField.textProperty().setValue("");
        }
    }


}
