package be.kdg.chat.gedistribueerde.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import be.kdg.chat.gedistribueerde.server.ChatServer;

public final class ChatClientStarter {
    public static void main(String[] args) {
        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String name = String.format("ChatClient - %s @ %s",
                    LocalDate.now().format(dateFormatter),
                    LocalTime.now().format(timeFormatter)
            );

            ChatServer server = (ChatServer) Naming.lookup("rmi://localhost:1099/chatserver");
            ChatClient client = new ChatClientImpl(server, name);
            new ChatFrame(client);

            System.out.printf("INFO: %s started", name);
        } catch (MalformedURLException e) {
            System.out.println("ERROR: Something is not right about the RMI url.");
        } catch (NotBoundException e) {
            System.out.println("ERROR: Something is not right about the RMI lookup.");
        } catch (RemoteException e) {
            System.out.println("ERROR: Something is not right about the RMI stuffs.");
            e.printStackTrace();
        }
    }
}
