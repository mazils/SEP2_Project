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

        // binding username
        // binding password
    }

    public void onLogInButton(){

    }

}
