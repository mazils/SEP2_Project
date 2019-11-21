package client.viewmodel;

import client.model.ModelFactory;
import client.view.ViewHandler;
import client.viewmodel.createAccount.CreateAccountViewModel;
import client.viewmodel.logIn.LoginViewModel;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ViewModelFactory {
    private LoginViewModel loginViewModel;
    private CreateAccountViewModel createAccountViewModel;

    public ViewModelFactory(ModelFactory modelFactory) throws RemoteException, NotBoundException {
        loginViewModel= new LoginViewModel(modelFactory.getDataModel());
        createAccountViewModel = new CreateAccountViewModel(modelFactory.getDataModel());
    }

    public LoginViewModel getLoginViewModel() {
        return loginViewModel;
    }

    public CreateAccountViewModel getCreateAccountVM() {
        return createAccountViewModel;
    }
}
