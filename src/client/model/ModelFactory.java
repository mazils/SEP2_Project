package client.model;

import client.model.modelaccount.AccountModel;
import client.model.modelaccount.IAccountsModel;

public class ModelFactory {
    private IAccountsModel dataModel;

    public IAccountsModel getDataModel()
    {
        if(dataModel==null)
        {
            dataModel= new AccountModel();
        }
        return dataModel;
    }
}
