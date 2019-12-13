package client.viewmodel;

import client.model.ModelFactory;
import client.viewmodel.amountReceived.AmountReceivedViewModel;
import client.viewmodel.createAccount.CreateAccountViewModel;
import client.viewmodel.log.LogViewModel;
import client.viewmodel.logIn.LoginViewModel;
import client.viewmodel.newSparePart.NewSparePartViewModel;
import client.viewmodel.scooterModel.NewModelVM;
import client.viewmodel.sparePartsList.ModelsListMViewModel;
import client.viewmodel.sparePartsList.SparePartViewModel;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ViewModelFactory {
    private LoginViewModel loginViewModel;
    private CreateAccountViewModel createAccountViewModel;
    private NewModelVM newModelVM;
    private SparePartViewModel sparePartViewModel;
    private ModelsListMViewModel modelsListMViewModel;
    private NewSparePartViewModel newSparePartViewModel;
    private AmountReceivedViewModel amountReceivedViewModel;
    private LogViewModel logViewModel;

    public ViewModelFactory(ModelFactory modelFactory) throws RemoteException, NotBoundException {
        loginViewModel= new LoginViewModel(modelFactory.getAccountsModel());
        createAccountViewModel = new CreateAccountViewModel(modelFactory.getAccountsModel());
        newModelVM = new NewModelVM(modelFactory.getIMSModelModel());
        sparePartViewModel = new SparePartViewModel(modelFactory.getSparePartModel());
        modelsListMViewModel = new ModelsListMViewModel(modelFactory.getIMSModelModel());
        newSparePartViewModel = new NewSparePartViewModel(modelFactory.getSparePartModel());
        amountReceivedViewModel= new AmountReceivedViewModel(modelFactory.getSparePartModel());
        logViewModel = new LogViewModel(modelFactory.getLogModel());

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

    public NewSparePartViewModel getNewSparePartViewModel() {
        return newSparePartViewModel;
    }

    public AmountReceivedViewModel getAmountReceivedViewModel() {
        return amountReceivedViewModel;
    }

    public LogViewModel getLogViewModel() {
        return logViewModel;
    }
}
