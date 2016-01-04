package be.kdg.chat.gedistribueerde.p2p;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;
import java.rmi.server.UnicastRemoteObject;

public final class P2PChatClientStarter {
    public static void main(String[] args) {
        ChatClient peer;
        ChatClient other;
        Registry registry;

        try {
            registry = pokeRegistry();

            String[] clients = registry.list();
            boolean noClients = clients.length == 0;

            if (noClients) {
                peer = new ChatClientImpl(null, String.format("chatclient%d", clients.length));
            } else {
                other = (ChatClient) Naming.lookup(String.format("rmi://localhost:1099/%s", clients[0]));
                peer = new ChatClientImpl(other, String.format("chatclient%d", clients.length));
            }

            registry.rebind(peer.getName(), peer);

            new ChatClientFrame(peer);

            System.out.printf("INFO: %s started", peer.getName());
        } catch (RemoteException e) {
            System.out.println("ERROR: Something is not right about the RMI stuffs.");
            e.printStackTrace();
        } catch (MalformedURLException e) {
            System.out.println("ERROR: Something is not right about the RMI url.");
            e.printStackTrace();
        } catch (NotBoundException e) {
            System.out.println("ERROR: Something is not right about the RMI lookup.");
            e.printStackTrace();
        }
    }

    public static Registry pokeRegistry() throws RemoteException {
        try {
            return LocateRegistry.createRegistry(1099);
        } catch (ExportException e) {
            return LocateRegistry.getRegistry(1099);
        } catch (RemoteException e) {
            System.out.println("ERROR: Something is not right about the RMI stuffs.");
            e.printStackTrace();
            System.exit(0);
        }

        return null;
    }
}
