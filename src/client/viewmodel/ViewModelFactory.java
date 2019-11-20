package client.viewmodel;

import client.model.ModelFactory;
import client.viewmodel.logIn.LoginViewModel;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ViewModelFactory {
    private LoginViewModel loginViewModel;

    public ViewModelFactory(ModelFactory modelFactory) throws RemoteException, NotBoundException {
        loginViewModel= new LoginViewModel(modelFactory.getDataModel());
    }

    public LoginViewModel getLoginViewModel() {
        return loginViewModel;
    }
}
