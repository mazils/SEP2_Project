package client.viewmodel;

import client.model.ModelFactory;
import client.view.ViewHandler;
import client.viewmodel.createAccount.CreateAccountViewModel;
import client.viewmodel.logIn.LoginViewModel;
import client.viewmodel.newSparePart.NewSparePartViewModel;
import client.viewmodel.scooterModel.NewModelVM;
import client.viewmodel.sparePart.ModelsListMViewModel;
import client.viewmodel.sparePart.SparePartViewModel;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ViewModelFactory {
    private LoginViewModel loginViewModel;
    private CreateAccountViewModel createAccountViewModel;
    private NewModelVM newModelVM;
    private SparePartViewModel sparePartViewModel;
    private ModelsListMViewModel modelsListMViewModel;
    private NewSparePartViewModel newSparePartViewModel;

    public ViewModelFactory(ModelFactory modelFactory) throws RemoteException, NotBoundException {
        loginViewModel= new LoginViewModel(modelFactory.getDataModel());
        createAccountViewModel = new CreateAccountViewModel(modelFactory.getDataModel());
        newModelVM = new NewModelVM(modelFactory.getIMSModelModel());
        sparePartViewModel = new SparePartViewModel(modelFactory.getSparePartModel());
        modelsListMViewModel = new ModelsListMViewModel(modelFactory.getIMSModelModel());
        newSparePartViewModel = new NewSparePartViewModel(modelFactory.getSparePartModel());
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

    public SparePartViewModel getSparePartViewModel()
    {
        return sparePartViewModel;
    }

    public ModelsListMViewModel getModelsListVM() {
        return modelsListMViewModel;
    }

    public NewSparePartViewModel getNewSparePartViewModel() {return newSparePartViewModel;}
}
