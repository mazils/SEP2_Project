package client.viewmodel.logIn;

import client.model.modelaccount.IAccountsModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;

import javax.swing.*;

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

    public void checkIfExists(){
        if(accountsModel.accountExists(userName.getValue(),password.getValue())){
            return;
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
