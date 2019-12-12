package server.serverManager;

import client.model.modelaccount.Account;
import server.jdbc.AccountsJDBC;

import java.sql.SQLException;

public class AccountsSManager {
    private AccountsJDBC accountsJDBC;

    public AccountsSManager() {
        accountsJDBC = new AccountsJDBC();
    }

    public synchronized boolean checkIfExists(String userName, String password) {
        try {
            return accountsJDBC.checkAccExists(userName,password);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkUsername(String username)throws SQLException {
        return accountsJDBC.checkUsername(username);
    }

    public void addAccount(Account acc, boolean isManager){
        accountsJDBC.addAccount(acc,isManager);

    }


}
