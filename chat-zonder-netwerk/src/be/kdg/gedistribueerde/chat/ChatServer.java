package be.kdg.gedistribueerde.chat;

import java.util.List;
import java.util.ArrayList;

public class ChatServer {
    private List<ChatClient> clients;

    public ChatServer() {
        this.clients = new ArrayList<ChatClient>();
    }

    public void register(ChatClient client) {
        clients.add(client);
        send("server", client.getName() + " has entered the room");
    }

    public void unregister(ChatClient client) {
        clients.remove(client);
        send("server", client.getName() + " has left the room");
    }

    public void send(String name, String message) {
        for(ChatClient client : clients) {
            client.receive(name + ": " + message);
        }
    }
}
