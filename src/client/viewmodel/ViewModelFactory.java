package client.viewmodel;

import client.model.ModelFactory;
import client.view.ViewHandler;
import client.viewmodel.createAccount.CreateAccountViewModel;
import client.viewmodel.logIn.LoginViewModel;
import client.viewmodel.scooterModel.NewModelVM;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ViewModelFactory {
    private LoginViewModel loginViewModel;
    private CreateAccountViewModel createAccountViewModel;
    private NewModelVM newModelVM;

    public ViewModelFactory(ModelFactory modelFactory) throws RemoteException, NotBoundException {
        loginViewModel= new LoginViewModel(modelFactory.getDataModel());
        createAccountViewModel = new CreateAccountViewModel(modelFactory.getDataModel());
        newModelVM = new NewModelVM(modelFactory.getModelModel());
    }

    public LoginViewModel getLoginViewModel() {
        return loginViewModel;
    }

    public CreateAccountViewModel getCreateAccountVM() {
        return createAccountViewModel;
    }

    public NewModelVM getNewModelVM()
    {
        return newModelVM;
    }
}
