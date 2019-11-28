package server.jdbc;

import client.model.SMModel.ISModel;

public class SModelJDBC {
    JDBC database;

    public SModelJDBC()
    {
        database= JDBC.getInstance();
    }

    public void addModel(ISModel model) {
        String statement = "INSERT INTO " + "\"SEP2\"" + ".model(name) VALUES " +"( '" +model.getModelName() + "')";
        database.executeUpdate(statement);

    }

    public void removeModel(ISModel model) {
        String statement= "DELETE FROM" + "\"SEP2\"" + ".model WHERE" + " name in  (" + model.getModelName() + ")";
        database.executeUpdate(statement);
    }
}
