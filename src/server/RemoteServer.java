package server;

import java.rmi.Remote;

public interface RemoteServer extends Remote {
    boolean checkIfExists(String userName,String password);

}
