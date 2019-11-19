package client.model.modelaccount;

import java.util.ArrayList;

public class AccountModel implements IAccountsModel {
    private Account accounts;
    private ArrayList<Account> database;

    public AccountModel(Account accs)
    {
        accounts = accs;
        database= new ArrayList<>();
    }

    public void addAccount(Account acc)
    {
        database.add(acc);
    }

    public boolean accountExists( Account acc)
    {
        for(int i= 0; i<database.size();i++)
        {
            if(acc== database.get(i))
            {
                return true;
            }
        }
        return false;
    }

    public boolean accountExists( String username, String password)
    {
        for(int i= 0; i<database.size();i++)
        {
            if(username== database.get(i).getUsername() && password== database.get(i).getPassword())
            {
                return true;
            }
        }
        return false;
    }

    public boolean checkUsername(String username)
    {
        for(int i= 0; i<database.size();i++)
        {
            if(username== database.get(i).getUsername())
            {
                return true;
            }
        }
        return false;
    }

}
