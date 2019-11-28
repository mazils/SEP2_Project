package server.jdbc;

import client.model.SMModel.ISModel;
import client.model.SMModel.ISparePart;

public class SparePartsJDBC {
    private JDBC database;

    public SparePartsJDBC()
    {
        database= JDBC.getInstance();
    }

    public void addSparePart(ISparePart sparePart, ISModel model) {
         String statement = "INSERT INTO " + "\"SEP2\"" + ".sparePart(name,mName) VALUES " +"( '" +sparePart.getName() +   "', '" + acc.getPassword() + "')";
         database.executeUpdate(statement);
    }

    public void removeSparePart(ISparePart sparePart, ISModel model) {
         String statement= "DELETE FROM" + "\"SEP2\"" + ".sparepart WHERE" + " name in " + sparePart.ge + " AND mName IN " + model.getModelName();
         database.executeUpdate(statement);
    }
}
