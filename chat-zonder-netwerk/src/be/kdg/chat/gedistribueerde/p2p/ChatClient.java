package be.kdg.chat.gedistribueerde.p2p;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Set;

public interface ChatClient extends Remote {
    Set<ChatClient> getChatClients() throws RemoteException;
    String getName() throws RemoteException;
    void setTextReceiver(TextReceiver textReceiver) throws RemoteException;

    void receive(String message) throws RemoteException;
    void register(ChatClient client) throws RemoteException;
    void send(String message) throws RemoteException;
    void send(String name, String message) throws RemoteException;
    void unregister() throws RemoteException;
    void unregister(ChatClient client) throws RemoteException;
}
