package server.jdbc;

import Shared.PropertyChangeSubject;
import client.model.ScooterModels.IMSModel;
import client.model.ScooterModels.ISModel;
import client.model.spareParts.ISparePart;
import client.model.spareParts.SparePart;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SparePartsJDBC implements PropertyChangeSubject {
    private JDBC database;

    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    public SparePartsJDBC()
    {
        database= JDBC.getInstance();
    }

    public void addSparePart(ISparePart sparePart, ISModel model) {
         String statement = "INSERT INTO " + "\"SEP2\"" + ".sparepart(name,mName, quantity, amountNeeded) VALUES " +"( '" +sparePart.getName() +   "', '" + model.getModelName() + "', '"+0+"', '"+0+"')";
         database.executeUpdate(statement);

         support.firePropertyChange("change", null, model);
    }

    public void removeSparePart(ISparePart sparePart, ISModel model) {
         String statement= "DELETE FROM" + "\"SEP2\"" + ".sparepart WHERE" + " name = " +"'"+ sparePart.getName()+"'"+ " AND mName =" +  " '" + model.getModelName() + "' ";
         database.executeUpdate(statement);

         support.firePropertyChange("change", null, model);
    }

    public ArrayList<SparePart> getAllSpareParts(ISModel model) throws SQLException {
        String statement = "SELECT * FROM " + "\"SEP2\""+ ".sparepart WHERE mName = " + "'" + model.getModelName()+ "'";
        ResultSet rs=database.executeQuery(statement);
        ArrayList<SparePart> spareParts= new ArrayList<>();
        while (rs.next() )
        {
            String name =rs.getString(2);
            int quantity = rs.getInt(4);
            int amountNeeded = rs.getInt(5);
            SparePart part= new SparePart(name, quantity, amountNeeded);
            spareParts.add(part);

        }
        return spareParts;

    }

    public void editSparePart(ISparePart part, ISModel model, int quantity, int amountNeeded){
        String statement = "UPDATE \"SEP2\".sparepart SET quantity = "+quantity+", "+" amountNeeded = "+amountNeeded+" WHERE name = '"+part.getName()+"' AND mName = '"+model.getModelName()+"' ";

        database.executeUpdate(statement);

        support.firePropertyChange("change", null, model);
    }

    public void incrementSparePartQuantity(ISparePart part, String scooterModel)
    {
        int quantity = part.getQuantity()+1 ;
        System.out.println("quantity from spare parts jdbc" + quantity);
        String statement = "UPDATE \"SEP2\".sparepart SET quantity = " + quantity + "WHERE name = '"+part.getName()+"' AND mName = '"+scooterModel+"' ";
                //todo fire event

        database.executeUpdate(statement);
        support.firePropertyChange("change",null,scooterModel);
    }

    @Override
    public void addListener(String names, PropertyChangeListener listener) throws RemoteException {
        support.addPropertyChangeListener(names, listener);
        System.out.println("SparePartJDBC adding listener");
    }

    @Override
    public void removeListener(String names, PropertyChangeListener listener) {

    }


}
