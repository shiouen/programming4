package be.kdg.chat.gedistribueerde.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import be.kdg.chat.gedistribueerde.client.ChatClient;

public interface ChatServer extends Remote {
    void register(ChatClient client) throws RemoteException;
    void send(String name, String message) throws RemoteException;
    void unregister(ChatClient client) throws RemoteException;
}
