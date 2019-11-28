package server.jdbc;

import client.model.modelaccount.Account;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountsJDBC {
    JDBC database;

    public AccountsJDBC()
    {
        database= JDBC.getInstance();
    }

    public boolean checkAccExists(String userName, String password) throws SQLException {

        String statement = "SELECT * FROM " + "\"SEP2\""+ ".account WHERE userName = " + "'" + userName+ "'" + " AND password =" + "'" + password + "'";
        System.out.println(statement);
       ResultSet set= database.executeQuery(statement);
        while (set.next())
        {
            if(set.getString(2).equals(userName) && set.getString(3).equals(password))
            {
                return true;
            }
        }


        return false;
    }

    public void addAccount(Account acc, boolean isManager) {
        String statement = "INSERT INTO " + "\"SEP2\"" + ".account(username,password,isManager) VALUES " +"( '" +acc.getUsername() + "', '" + acc.getPassword() + "', '" + isManager +"')";
        System.out.println(statement);
       database.executeUpdate(statement);
    }

    public boolean checkUsername(String username) throws SQLException {
        String statement = "SELECT * FROM " + "\"SEP2\""+ ".account WHERE userName = " + "'" + username+ "'";
        System.out.println(statement);
        ResultSet rs= database.executeQuery(statement);
        while (rs.next())
        {
            if(rs.getString(2).equals(username) )
            {
                return true;
            }
        }
        return false;

    }

}
