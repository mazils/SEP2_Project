package client.view.login;

import client.view.ViewHandler;
import client.viewmodel.logIn.LoginViewModel;
import javafx.fxml.FXML;

import javafx.scene.control.TextField;


public class LoginController {

    private ViewHandler viewHandler;
    private LoginViewModel loginViewModel;

    @FXML
    private TextField userNameTextField;

    @FXML
    private TextField passwordTextField;

    public void init(LoginViewModel viewModel, ViewHandler viewHandler){
        loginViewModel=viewModel;
        this.viewHandler=viewHandler;

        loginViewModel.getUserNameProperty().bind(userNameTextField.textProperty());
        loginViewModel.getPasswordProperty().bind(passwordTextField.textProperty());
    }

    public void onLogInButton(){

    }

}
