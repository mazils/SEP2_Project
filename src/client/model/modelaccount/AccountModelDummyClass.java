package client.model.modelaccount;

import client.model.modelaccount.IAccountsModel;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

//************A dummy class for testing*********


public class AccountModelDummyClass implements IAccountsModel
{

    private ArrayList<String> arrayList;

    public AccountModelDummyClass()
    {
        //our "DBS"
        this.arrayList = new ArrayList<>();
        arrayList.add("simon");
        arrayList.add("rafal");
        arrayList.add("arturas");
        arrayList.add("sasha");
    }

    @Override
    public boolean accountExists(String username, String password) throws RemoteException
    {
        return false;
    }

    @Override
    public boolean checkUsername(String username) throws RemoteException, SQLException
    {
        return arrayList.contains(username);
    }

    @Override
    public void createAccount(String username, String password, boolean isManager) throws RemoteException
    {
        System.out.println("account created in dummy class!");
    }


    public boolean accountIsManager(String username, String password) {
        return false;
    }
}
