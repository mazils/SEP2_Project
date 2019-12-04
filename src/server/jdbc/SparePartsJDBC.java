package server.jdbc;

import client.model.ScooterModels.ISModel;
import client.model.spareParts.ISparePart;
import client.model.spareParts.SparePart;

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
         String statement = "INSERT INTO " + "\"SEP2\"" + ".sparepart(name,mName, quantity) VALUES " +"( '" +sparePart.getName() +   "', '" + model.getModelName() + "', '"+0+"')";
         database.executeUpdate(statement);
    }

    public void removeSparePart(ISparePart sparePart, ISModel model) {
         String statement= "DELETE FROM" + "\"SEP2\"" + ".sparepart WHERE" + " name = " +"'"+ sparePart.getName()+"'"+ " AND mName =" +  " '" + model.getModelName() + "' ";
         database.executeUpdate(statement);
    }

    public ArrayList<SparePart> getAllSpareParts(ISModel model) throws SQLException {
        String statement = "SELECT * FROM " + "\"SEP2\""+ ".sparepart WHERE mName = " + "'" + model.getModelName()+ "'";
        ResultSet rs=database.executeQuery(statement);
        ArrayList<SparePart> spareParts= new ArrayList<>();
        while (rs.next())
        {
            String name =rs.getString(2);
            int quantity = rs.getInt(4);
            SparePart part= new SparePart(name, quantity);
            spareParts.add(part);

        }
        return spareParts;
    }


}
