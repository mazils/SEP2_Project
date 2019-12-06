import client.model.modelaccount.IAccountsModel;
import client.model.modelaccount.AccountModelDummyClass;
import client.viewmodel.createAccount.CreateAccountViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;


//todo this
class CreateAccountViewModelTest
{
    IAccountsModel iAccountsModel ;
    CreateAccountViewModel createAccountViewModel;

    @BeforeEach
    void setUp() throws RemoteException, NotBoundException, SQLException
    {
        //arrange
        iAccountsModel = new AccountModelDummyClass();
        createAccountViewModel = new CreateAccountViewModel(iAccountsModel);

    }

    @AfterEach
    void tearDown()
    {

    }

    @Test
    void getUsername()
    {
    }

    @Test
    void getPassword()
    {
    }

    @Test
    void getConfirmPassword()
    {
    }

    @Test
    void getIsManagerProperty()
    {
    }

    @Test
    void createAccount() throws RemoteException, SQLException
    {
        StringProperty username = new SimpleStringProperty();
        StringProperty password = new SimpleStringProperty();
        StringProperty confPassword = new SimpleStringProperty();

        username.bindBidirectional(createAccountViewModel.getUsername());
        password.bindBidirectional(createAccountViewModel.getPassword());
        confPassword.bindBidirectional(createAccountViewModel.getConfirmPassword());

        //act
        username.setValue("arturas");
        password.setValue("password");
        confPassword.setValue("password");

        try
        {
            createAccountViewModel.createAccount(new Stage());
        }
        catch (ExceptionInInitializerError e)
        {

        }


        System.out.println(createAccountViewModel.getUsername());
        //assert
        assertEquals("arturas",createAccountViewModel.getUsername().getValue());


    }

}