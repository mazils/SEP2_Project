package client.viewmodel.createAccount;

import client.model.modelaccount.IAccountsModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class CreateAccountViewModel
{
    private IAccountsModel iAccountsModel;
    private StringProperty username = new SimpleStringProperty();
    private StringProperty password = new SimpleStringProperty();
    private StringProperty confirmPassword = new SimpleStringProperty();
    private BooleanProperty managerAccount = new SimpleBooleanProperty();
    private Alert alert = new Alert(Alert.AlertType.WARNING);

    public CreateAccountViewModel(IAccountsModel iAccountsModel)
    {
        this.iAccountsModel = iAccountsModel;
    }

    public String getUsername()
    {
        return username.get();
    }

    public StringProperty usernameProperty()
    {
        return username;
    }

    public String getPassword()
    {
        return password.get();
    }

    public StringProperty passwordProperty()
    {
        return password;
    }

    public String getConfirmPassword()
    {
        return confirmPassword.get();
    }

    public StringProperty confirmPasswordProperty()
    {
        return confirmPassword;
    }

    public boolean isManagerAccount()
    {
        return managerAccount.get();
    }

    public BooleanProperty managerAccountProperty()
    {
        return managerAccount;
    }

    public void createAccount(SimpleStringProperty username, SimpleStringProperty password, SimpleStringProperty confirmPassword, BooleanProperty managerAccount) throws RemoteException, SQLException
    {
        if (iAccountsModel.checkUsername(username.getValue()))
        {
            alert.setContentText("This account already exists");
            alert.showAndWait();
        }
        else if(!password.getValue().equals(confirmPassword.getValue()))
        {
            alert.setContentText("The username and password doesn't match");
            alert.showAndWait();
        }
        else
        {
            iAccountsModel.createAccount(username.getValue(),password.getValue(),managerAccount.getValue());
        }
    }
}
