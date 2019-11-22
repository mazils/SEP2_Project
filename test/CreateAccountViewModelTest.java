import client.model.modelaccount.AccountModel;
import client.model.modelaccount.IAccountsModel;
import client.viewmodel.createAccount.CreateAccountViewModel;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.*;

class CreateAccountViewModelTest
{

    @org.junit.jupiter.api.BeforeEach
    void setUp() throws RemoteException, NotBoundException
    {
        IAccountsModel iAccountsModel = new AccountModel();
        CreateAccountViewModel  createAccountViewModel = new CreateAccountViewModel(iAccountsModel);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown()
    {
    }

    @org.junit.jupiter.api.Test
    void getUsername()
    {
    }

    @org.junit.jupiter.api.Test
    void getPassword()
    {
    }

    @org.junit.jupiter.api.Test
    void getConfirmPassword()
    {
    }

    @org.junit.jupiter.api.Test
    void getIsManagerProperty()
    {
    }

    @org.junit.jupiter.api.Test
    void createAccount()
    {
    }
}