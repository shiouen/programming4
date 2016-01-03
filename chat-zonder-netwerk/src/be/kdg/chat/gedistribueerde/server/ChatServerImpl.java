package be.kdg.chat.gedistribueerde.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import be.kdg.chat.gedistribueerde.client.ChatClient;

public class ChatServerImpl implements ChatServer {
    private List<ChatClient> clients;

    public ChatServerImpl() throws RemoteException {
        this.clients = new ArrayList<>();
    }

    @Override
    public void register(ChatClient client) throws RemoteException {
        this.clients.add(client);
        this.send("server", client.getName() + " has entered the room");
    }

    @Override
    public void send(String name, String message) throws RemoteException {
        for(ChatClient client : this.clients) {
            client.receive(name + ": " + message);
        }
    }

    @Override
    public void unregister(ChatClient client) throws RemoteException {
        this.clients.remove(client);
        this.send("server", client.getName() + " has left the room");
    }
}
