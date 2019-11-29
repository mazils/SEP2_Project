package server.jdbc;

import client.model.SMModel.ISModel;
import client.model.SMModel.SModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SModelJDBC {
    JDBC database;

    public SModelJDBC()
    {
        database= JDBC.getInstance();
    }

    public void addModel(ISModel model) {
        String statement = "INSERT INTO " + "\"SEP2\"" + ".model(mName) VALUES " +"( '" +model.getModelName() + "')";
        database.executeUpdate(statement);

    }

    public void removeModel(ISModel model) {
        String statement= "DELETE FROM" + "\"SEP2\"" + ".model WHERE" + " mName in  (" + model.getModelName() + ")";
        database.executeUpdate(statement);
    }

    public ArrayList<ISModel> getAllModels() throws SQLException {
        ArrayList<ISModel> models= new ArrayList<>();
        String statement= "Select * FROM" + "\"SEP2\"" + ".model ";
        ResultSet rs=database.executeQuery(statement);
        while (rs.next())
        {
            ISModel model= new SModel(rs.getString(1));
           models.add(model);
        }
        return models;
    }
}
