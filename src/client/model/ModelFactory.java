package client.model;

import client.model.SMModel.IMSModel;
import client.model.SMModel.IMSparePart;
import client.model.SMModel.MSModel;
import client.model.SMModel.MSparePart;
import client.model.modelaccount.AccountModel;
import client.model.modelaccount.IAccountsModel;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ModelFactory {
    private IAccountsModel accountsModel;
    private IMSModel modelOfScooter;
    private IMSparePart sparePartModel;

    public IAccountsModel getAccountsModel() throws RemoteException, NotBoundException {
        if(accountsModel ==null)
        {
            accountsModel = new AccountModel();
        }
        return accountsModel;
    }

    public IMSModel getIMSModelModel() throws RemoteException, NotBoundException {
        if(modelOfScooter ==null)
        {
            modelOfScooter = new MSModel();
        }
        return modelOfScooter;
    }

    public IMSparePart getSparePartModel() throws RemoteException, NotBoundException {
       if(sparePartModel==null) {
            sparePartModel= new MSparePart();
       }
        return sparePartModel;
    }
}
