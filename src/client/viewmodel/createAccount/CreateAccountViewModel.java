package client.viewmodel.createAccount;

import client.model.modelaccount.IAccountsModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.rmi.RemoteException;
import java.sql.SQLException;

public class CreateAccountViewModel
{
    private IAccountsModel iAccountsModel;
    private StringProperty username = new SimpleStringProperty();
    private StringProperty password = new SimpleStringProperty();
    private StringProperty confirmPassword = new SimpleStringProperty();
    private BooleanProperty managerAccount = new SimpleBooleanProperty();

    public CreateAccountViewModel(IAccountsModel iAccountsModel)
    {
        this.iAccountsModel = iAccountsModel;
    }

    public StringProperty getUsername()
    {
        return username;
    }

    public StringProperty getPassword()
    {
        return password;
    }

    public StringProperty getConfirmPassword()
    {
        return confirmPassword;
    }

    public BooleanProperty getIsManagerProperty()
    {
        return managerAccount;
    }

    public void createAccount(Stage stage) throws RemoteException, SQLException
    {
        if (iAccountsModel.checkUsername(username.getValue()))
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("This account already exists");
            alert.showAndWait();
            username.setValue("");
            password.setValue("");
            confirmPassword.setValue("");
            managerAccount.setValue(false);

       }
         if(!password.getValue().equals(confirmPassword.getValue()))
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("The password doesn't match");
            alert.showAndWait();
            password.setValue("");
            confirmPassword.setValue("");
        }
        else
        {
            System.out.println(password.getValue()+" "+confirmPassword.getValue());
            iAccountsModel.createAccount(username.getValue(),password.getValue(),managerAccount.getValue());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("New account has been created");
            alert.showAndWait();
            stage.close();
        }
    }
}
