package be.kdg.chat.gedistribueerde.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;

public class ChatFrame extends JFrame implements TextReceiver {
    private JLabel nameLabel;
    private JTextArea history;
    private JTextField messageField;
    private JButton sendButton;
    private JButton exitButton;
    private ChatClient chatClient;

    public ChatFrame(ChatClient chatClient) throws RemoteException {
        this.chatClient = chatClient;
        this.chatClient.setTextReceiver(this);
        String name = this.chatClient.getName();
        this.setTitle("Chat: " + name);
        this.createComponents(name);
        this.layoutComponents();
        this.addListeners();
        this.setSize(300, 300);
        this.setVisible(true);
    }

    private void createComponents(String name) {
        this.nameLabel = new JLabel(name);
        this.history = new JTextArea();
        this.history.setEditable(false);
        this.messageField = new JTextField();
        this.sendButton = new JButton("send");
        this.exitButton = new JButton("exit");
    }
    private void layoutComponents() {
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        namePanel.add(this.nameLabel);
        JScrollPane historyPane = new JScrollPane(this.history);
        JPanel bottomPanel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(this.messageField, BorderLayout.CENTER);
        inputPanel.add(this.sendButton, BorderLayout.EAST);
        bottomPanel.add(inputPanel, BorderLayout.NORTH);
        JPanel exitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        exitPanel.add(this.exitButton);
        bottomPanel.add(exitPanel, BorderLayout.SOUTH);
        Container contentPane = getContentPane();
        contentPane.add(namePanel, BorderLayout.NORTH);
        contentPane.add(historyPane, BorderLayout.CENTER);
        contentPane.add(bottomPanel, BorderLayout.SOUTH);
    }
    private void addListeners() {
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.addWindowListener(new WindowAdapter()  {
            public void windowClosing(WindowEvent windowEvent) {
                try {
                    ChatFrame.this.stop();
                } catch (RemoteException e) {
                    // do nothing
                }
            }
        });
        this.sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    ChatFrame.this.send();
                } catch (RemoteException e) {
                    // do nothing
                }
            }
        });
        this.exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    ChatFrame.this.exit();
                } catch (RemoteException e) {
                    // do nothing
                }
            }
        });
        this.messageField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    ChatFrame.this.send();
                } catch (RemoteException e) {
                    // do nothing
                }
            }
        });
    }

    private void exit() throws RemoteException {
        this.stop();
        this.setVisible(false);
    }

    private void send() throws RemoteException {
        String message = this.messageField.getText();
        this.chatClient.send(message);
        this.messageField.setText("");
    }

    private void stop() throws RemoteException {
        this.chatClient.unregister();
        System.exit(0);
    }

    public void receive(String text) {
        String historyText = this.history.getText();
        this.history.setText(historyText + '\n' + text);
    }
}
