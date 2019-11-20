package server;
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
        String statement = "SELECT * FROM account WHERE userName = " + userName + "AND password =" + password;
        st.executeQuery("Set search_path = " + "SEP2");
        ResultSet rs= st.executeQuery(statement);
            if(rs.getString(2).equals(userName) && rs.getString(3).equals(password))
            {
                return true;
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
}
