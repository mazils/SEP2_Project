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
    private StringProperty username ;
    private StringProperty password;
    private StringProperty confirmPassword;
    private BooleanProperty managerAccount;

    public CreateAccountViewModel(IAccountsModel iAccountsModel)
    {
        this.iAccountsModel = iAccountsModel;
        username= new SimpleStringProperty();
        password= new SimpleStringProperty();
        confirmPassword= new SimpleStringProperty();
        managerAccount= new SimpleBooleanProperty();
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

    public void createAccount(Stage stage)
    {
        //check if empty
        if (password.getValue().equals("") || confirmPassword.getValue().equals("")||username.getValue().equals(""))
        {
            System.out.println("this is empty");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill in all fields");
            alert.showAndWait();
            managerAccount.setValue(false);

        }
        //checks if already in dbs
        else if (iAccountsModel.checkUsername(username.getValue()))
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("This account already exists");
            alert.showAndWait();
            username.setValue("");
            password.setValue("");
            confirmPassword.setValue("");
            managerAccount.setValue(false);

       }

        //checks if passwords match
          else if(!password.getValue().equals(confirmPassword.getValue()))
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
