package be.kdg.chat.gedistribueerde.p2p;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashSet;
import java.util.Set;

public class ChatClientImpl extends UnicastRemoteObject implements ChatClient {
    private Set<ChatClient> clients;
    private String name;
    private TextReceiver textReceiver;


    public ChatClientImpl(ChatClient other, String name) throws RemoteException {
        this.name = name;
        this.clients = (other != null) ? other.getChatClients() : new HashSet<>();

        for (ChatClient client : this.clients) {
            client.register(this);
        }
        this.clients.add(this);
        this.send("lobby", this.name + " has entered the room");
    }

    @Override
    public Set<ChatClient> getChatClients() throws RemoteException { return this.clients; }
    @Override
    public String getName() throws RemoteException { return this.name; }

    @Override
    public void setTextReceiver(TextReceiver textReceiver) throws RemoteException { this.textReceiver = textReceiver; }

    @Override
    public void receive(String message) throws RemoteException {
        if (this.textReceiver == null) { return; }
        this.textReceiver.receive(message);
    }

    @Override
    public void register(ChatClient client) throws RemoteException {
        this.clients.add(client);
    }

    @Override
    public void send(String message) throws RemoteException {
        this.send(this.name, message);
    }

    @Override
    public void send(String name, String message) throws RemoteException {
        for (ChatClient client : this.clients) {
            client.receive(name + ": " + message);
        }
    }

    @Override
    public void unregister() throws RemoteException {
        for (ChatClient client : this.clients) {
            client.unregister(this);
        }
        this.send("lobby", this.name + " has left the room");
    }

    @Override
    public void unregister(ChatClient client) throws RemoteException {
        this.clients.remove(client);
    }
}
