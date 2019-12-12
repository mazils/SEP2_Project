package server.serverManager;

import client.model.ScooterModels.SModel;
import client.model.spareParts.ISparePart;
import client.model.spareParts.SparePart;
import server.RPCLWrapper;
import server.jdbc.LogJDBC;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class LogsManager  {
    private LogJDBC logJDBC;

    public LogsManager()
    {
        logJDBC= new LogJDBC();
    }
    public  ArrayList<String> getLogList(ISparePart part, SModel model) {
      return   logJDBC.getDatabaseLogs((SparePart) part,model);
    }


    public void logToDatabase(String log) {
        logJDBC.logToDatabase(log);
    }

    public void addListener(String names, PropertyChangeListener listener) {
        logJDBC.addListener(names, listener);
    }
}
