package server;
import client.model.SMModel.ISModel;
import client.model.SMModel.ISparePart;
import client.model.modelaccount.Account;

import java.sql.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class JDBC implements  Runnable{

    private static JDBC databaseConnector;
    private static Lock lock= new ReentrantLock();
    private Connection con = null;
    private Statement st= null;

    private JDBC()
    {}

    public static JDBC getInstance()
    {
        if(databaseConnector==null) {
            synchronized (lock) {
                if(databaseConnector==null) {
                   databaseConnector= new JDBC();
                }
            }
        }
        return  databaseConnector;
    }

    public boolean checkAccExists(String userName, String password) throws SQLException {

        String statement = "SELECT * FROM " + "\"SEP2\""+ ".account WHERE userName = " + "'" + userName+ "'" + " AND password =" + "'" + password + "'";
        System.out.println(statement);
        ResultSet rs= st.executeQuery(statement);
            while (rs.next())
            {
                if(rs.getString(2).equals(userName) && rs.getString(3).equals(password))
                {
                    return true;
                }
            }


        return false;
    }

    public void addAccount(Account acc,boolean isManager) {
        String statement = "INSERT INTO " + "\"SEP2\"" + ".account(username,password,isManager) VALUES " +"( '" +acc.getUsername() + "', '" + acc.getPassword() + "', '" + isManager +"')";
        System.out.println(statement);
        try {
            ResultSet rs= st.executeQuery(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkUsername(String username) throws SQLException {
        String statement = "SELECT * FROM " + "\"SEP2\""+ ".account WHERE userName = " + "'" + username+ "'";
        System.out.println(statement);
        ResultSet rs= st.executeQuery(statement);
        while (rs.next())
        {
            if(rs.getString(2).equals(username) )
            {
                return true;
            }
        }
        return false;

    }





    @Override
    public void run() {

        try {
            Class.forName("org.postgresql.Driver");
             con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","qwerty");
             st= con.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        while (true)
        {

        }

    }


    public void addModel(ISModel model) {
        String statement = "INSERT INTO " + "\"SEP2\"" + ".model(name) VALUES " +"( '" +model.getModelName() + "')";
        try {
            st.executeQuery(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void addSparePart(ISparePart sparePart, ISModel model) {
      //  String statement = "INSERT INTO " + "\"SEP2\"" + ".sparePart(name,mName) VALUES " +"( '" +sparePart.getName() +   "', '" + acc.getPassword() + "')";

    }

    public void removeSparePart(ISparePart sparePart, ISModel model) {
      // String statement= "DELETE FROM" + "\"SEP2\"" + ".sparepart WHERE" + " name in " + sparePart.ge + " AND mName IN " + model.getModelName();
    }

    public void removeModel(ISModel model) {
        String statement= "DELETE FROM" + "\"SEP2\"" + ".model WHERE" + " name in  (" + model.getModelName() + ")";
        try {
            st.executeQuery(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
