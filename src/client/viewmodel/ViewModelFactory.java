package client.viewmodel;

import client.model.ModelFactory;
import client.viewmodel.logIn.LoginViewModel;

public class ViewModelFactory {
    private LoginViewModel loginViewModel;

    public ViewModelFactory(ModelFactory modelFactory)
    {
        loginViewModel= new LoginViewModel(modelFactory.getDataModel());
    }

    public LoginViewModel getLoginViewModel() {
        return loginViewModel;
    }
}
