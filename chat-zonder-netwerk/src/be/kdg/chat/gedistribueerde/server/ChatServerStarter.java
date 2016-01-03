package be.kdg.chat.gedistribueerde.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ChatServerStarter {
    public static void main(String[] args) {
        try {
            ChatServer server = new ChatServerImpl();
            ChatServer serverRemote = (ChatServer) UnicastRemoteObject.exportObject(server, 0);
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("chatserver", serverRemote);
            System.out.println("INFO: ChatServer started");
        } catch (RemoteException e) {
            System.out.println("ERROR: Something is not right about the RMI stuffs.");
            e.printStackTrace();
            System.exit(0);
        }
    }
}
