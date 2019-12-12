package client.viewmodel.createAccount;

import client.model.modelaccount.IAccountsModel;
import client.view.ViewHandler;
import javafx.beans.property.*;
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
    private ViewHandler viewHandler;
    private BooleanProperty isRightAccount;
    private IntegerProperty alertType;
    public CreateAccountViewModel(IAccountsModel iAccountsModel)
    {
        this.viewHandler = viewHandler;
        this.iAccountsModel = iAccountsModel;
        username= new SimpleStringProperty();
        password= new SimpleStringProperty();
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
        //todo the alerts
        //todo Troels : it’s the responsibility of your view/controller class, to show these alerts; not the ViewModel.
        //check if empty
        if (password.getValue().equals("") || confirmPassword.getValue().equals("") || username.getValue().equals("")) {
            System.out.println("this is empty");
            alertType.set(Alert.AlertType.ERROR.ordinal());

            // Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setContentText("Please fill in all fields");
//            alert.showAndWait();
            managerAccount.setValue(false);
            System.out.println("Fields are empty");
        }
        //checks if already in dbs
        else if (iAccountsModel.checkUsername(username.getValue())) {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setContentText("This account already exists");
//            alert.showAndWait();

            alertType.set(Alert.AlertType.WARNING.ordinal());
            username.setValue("");
            password.setValue("");
            confirmPassword.setValue("");
            managerAccount.setValue(false);
            System.out.println("Account allready exists in DBS");
        }

        //checks if passwords match
        else if (!password.getValue().equals(confirmPassword.getValue())) {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setContentText("The password doesn't match");
//            alert.showAndWait();
            alertType.set(Alert.AlertType.WARNING.ordinal());
            password.setValue("");
            confirmPassword.setValue("");
            System.out.println("passwords doesnt match");
        } else {
            System.out.println(password.getValue() + " " + confirmPassword.getValue());
            System.out.println("Account has been created");
            iAccountsModel.createAccount(username.getValue(), password.getValue(), managerAccount.getValue());
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setContentText("New account has been created");
//            alert.showAndWait();
            isRightAccount.setValue(true);
            return true;
        }


        return false;
    }

    public IntegerProperty alertTypeProperty()
    {
        return alertType;
    }
}
