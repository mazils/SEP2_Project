package client.model;

import client.model.modelaccount.AccountModel;
import client.model.modelaccount.IAccountsModel;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ModelFactory {
    private IAccountsModel dataModel;

    public IAccountsModel getDataModel() throws RemoteException, NotBoundException {
        if(dataModel==null)
        {
            dataModel= new AccountModel();
        }
        return dataModel;
    }
}
