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
    private IAccountsModel dataModel;
    private IMSModel modelModel;
    private IMSparePart sparePartModel;

    public IAccountsModel getDataModel() throws RemoteException, NotBoundException {
        if(dataModel==null)
        {
            dataModel= new AccountModel();
        }
        return dataModel;
    }

    public IMSModel getIMSModelModel() throws RemoteException, NotBoundException {
        if(modelModel==null)
        {
            modelModel= new MSModel();
        }
        return modelModel;
    }

    public IMSparePart getSparePartModel() throws RemoteException, NotBoundException {
       if(dataModel==null) {
            sparePartModel= new MSparePart();
       }
        return sparePartModel;
    }
}
