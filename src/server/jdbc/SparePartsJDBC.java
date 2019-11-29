package server.jdbc;

import client.model.SMModel.ISModel;
import client.model.SMModel.ISparePart;
import client.model.SMModel.SparePart;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SparePartsJDBC {
    private JDBC database;

    public SparePartsJDBC()
    {
        database= JDBC.getInstance();
    }

    public void addSparePart(ISparePart sparePart, ISModel model) {
         String statement = "INSERT INTO " + "\"SEP2\"" + ".sparePart(name,mName) VALUES " +"( '" +sparePart.getName() +   "', '" + model.getModelName() + "')";
         database.executeUpdate(statement);
    }

    public void removeSparePart(ISparePart sparePart, ISModel model) {
         String statement= "DELETE FROM" + "\"SEP2\"" + ".sparepart WHERE" + " mName in " + sparePart.getName() + " AND mName IN " +  "( '" + model.getModelName() + "')";
         database.executeUpdate(statement);
    }

    public ArrayList<SparePart> getAllSpareParts(ISModel model) throws SQLException {
        String statement = "SELECT * FROM " + "\"SEP2\""+ ".sparepart WHERE mName = " + "'" + model.getModelName()+ "'";
        ResultSet rs=database.executeQuery(statement);
        ArrayList<SparePart> spareParts= new ArrayList<>();
        while (rs.next())
        {
            String name =rs.getString(1);
            SparePart part= new SparePart(name);
            spareParts.add(part);

        }
        return spareParts;
    }
}
