package client.viewmodel.logIn;

import client.model.modelaccount.IAccountsModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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
            return ;
        }else{
            JOptionPane.showMessageDialog(null, "Invalid user name or password", "Error", JOptionPane.WARNING_MESSAGE);
            userName.setValue("");
            password.setValue("");
        }
    }


}
