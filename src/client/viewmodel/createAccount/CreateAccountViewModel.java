package client.viewmodel.createAccount;

import client.model.modelaccount.IAccountsModel;
import client.view.ViewHandler;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
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
    private StringProperty alertText;
    private BooleanProperty managerAccount;
    private BooleanProperty isRightAccount;
    private IntegerProperty alertType;
    public CreateAccountViewModel(IAccountsModel iAccountsModel)
    {
        this.iAccountsModel = iAccountsModel;
        username= new SimpleStringProperty();
        password= new SimpleStringProperty();
        alertText= new SimpleStringProperty();
        confirmPassword= new SimpleStringProperty();
        managerAccount= new SimpleBooleanProperty();
        isRightAccount = new SimpleBooleanProperty();
        isRightAccount.setValue(false);
        alertType = new SimpleIntegerProperty();
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

    public BooleanProperty isRightAccountProperty() {
        return isRightAccount;
    }

    public BooleanProperty getIsManagerProperty()
    {
        return managerAccount;
    }

    public boolean createAccount() throws RemoteException, SQLException//todo there shouldnt be exceptions thrown here
    {
        //todo Troels : it’s the responsibility of your view/controller class, to show these alerts; not the ViewModel.
        //check if empty
        if (password.getValue().equals("") || confirmPassword.getValue().equals("") || username.getValue().equals(""))
        {
            System.out.println("this is empty");
            System.out.println(alertText.getValue() + "1");
            alertType.set(Alert.AlertType.ERROR.ordinal());
            alertText.set("Please fill in all fields");
            managerAccount.setValue(false);
            System.out.println("Fields are empty");


        }
        //checks if already in dbs
        else if (iAccountsModel.checkUsername(username.getValue()))
        {
            alertText.set("This account already exists");
            alertType.set(Alert.AlertType.WARNING.ordinal());
            username.setValue("");
            password.setValue("");
            confirmPassword.setValue("");
            managerAccount.setValue(false);
            System.out.println("Account already exists in DBS");
        }

        //checks if passwords match
        else if (!password.getValue().equals(confirmPassword.getValue()))
        {

             alertType.set(Alert.AlertType.WARNING.ordinal());
            alertText.set("The password doesn't match");
            password.setValue("");
            confirmPassword.setValue("");
            System.out.println("passwords doesnt match");
        }
        else
            {
            System.out.println(password.getValue() + " " + confirmPassword.getValue());
            System.out.println("Account has been created");
            iAccountsModel.createAccount(username.getValue(), password.getValue(), managerAccount.getValue());
             alertType.set(Alert.AlertType.INFORMATION.ordinal());
             alertText.set("New account has been created");

            isRightAccount.setValue(true);
            return true;
        }


        return false;
    }

    public IntegerProperty alertTypeProperty()
    {
        return alertType;
    }


    public StringProperty alertTextProperty()
    {
        return alertText;
    }
}
