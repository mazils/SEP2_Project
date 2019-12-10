package client.viewmodel.logIn;

import client.model.modelaccount.IAccountsModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import javax.swing.*;
import java.rmi.RemoteException;

public class LoginViewModel {

    private StringProperty userName;
    private StringProperty password;

    private IAccountsModel accountsModel;

    public LoginViewModel(IAccountsModel accountsModel){
        this.accountsModel=accountsModel;

        userName = new SimpleStringProperty();
        password = new SimpleStringProperty();
    }

    public StringProperty getUserNameProperty() {
        return userName;
    }

    public StringProperty getPasswordProperty() {
        return password;
    }

    /**
     * Validate input of username and password
     * if both are correct - calling another view trough view handler
     * else warning message
     */
    public void checkIfExists() throws RemoteException {
        if(accountsModel.accountExists(userName.getValue(),password.getValue())){
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Log in error");
            alert.setContentText("Invalid user name or password");
            alert.showAndWait();

            userName.setValue("");
            password.setValue("");
        }
    }


}
