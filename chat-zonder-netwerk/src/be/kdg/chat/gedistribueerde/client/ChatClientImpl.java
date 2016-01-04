package be.kdg.chat.gedistribueerde.client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import be.kdg.chat.gedistribueerde.server.ChatServer;

/**
 * Extending UnicastRemoteObject automatically exports the object,
 * without the need for serializing and exporting before binding.
 */
public class ChatClientImpl extends UnicastRemoteObject implements ChatClient {
    private ChatServer chatServer;
    private TextReceiver textReceiver;
    private String name;

    public ChatClientImpl(ChatServer chatServer, String name) throws RemoteException {
        this.chatServer = chatServer;
        this.name = name;
        chatServer.register(this);
    }

    @Override
    public String getName() throws RemoteException {
        return this.name;
    }

    @Override
    public void setTextReceiver(TextReceiver textReceiver) throws RemoteException {
        this.textReceiver = textReceiver;
    }

    @Override
    public void receive(String message) throws RemoteException {
        if (this.textReceiver == null) { return; }
        this.textReceiver.receive(message);
    }

    @Override
    public void send(String message) throws RemoteException {
        this.chatServer.send(this.name, message);
    }

    @Override
    public void unregister() throws RemoteException {
        this.chatServer.unregister(this);
    }
}
