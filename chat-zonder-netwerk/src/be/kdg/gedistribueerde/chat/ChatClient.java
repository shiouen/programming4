package be.kdg.gedistribueerde.chat;

public class ChatClient {
    private ChatServer chatServer;
    private TextReceiver textReceiver;
    private String name;

    public ChatClient(ChatServer chatServer, String name) {
        this.chatServer = chatServer;
        this.name = name;
        chatServer.register(this);
    }

    public String getName() {
        return name;
    }

    public void send(String message) {
        chatServer.send(name, message);
    }

    public void receive(String message) {
        if (textReceiver == null) return;
        textReceiver.receive(message);
    }

    public void setTextReceiver(TextReceiver textReceiver) {
        this.textReceiver = textReceiver;
    }

    public void unregister() {
        chatServer.unregister(this);
    }
}
