package be.kdg.chat.gedistribueerde.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatClient extends Remote {
    String getName() throws RemoteException;
    void setTextReceiver(TextReceiver textReceiver) throws RemoteException;

    void receive(String message) throws RemoteException;
    void send(String message) throws RemoteException;
    void unregister() throws RemoteException;
}
