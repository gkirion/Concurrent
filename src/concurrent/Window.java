/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrent;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author george
 */
public class Window extends JFrame implements ActionListener, MessageListener {
    
    private JTextArea textArea;
    private JPanel panel;
    private JTextField textField;
    private JButton button;
    private DataStore dataStore;
    
    public Window(String label) {
        setTitle(label);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 300);
        setLayout(new BorderLayout());
        textArea = new JTextArea();
        textArea.setEditable(false);
        add(textArea, BorderLayout.CENTER);
        panel = new JPanel(new BorderLayout());
        textField = new JTextField();
        panel.add(textField, BorderLayout.CENTER);
        button = new JButton("Send");
        button.addActionListener(this);
        panel.add(button, BorderLayout.EAST);
        add(panel, BorderLayout.SOUTH);
        show();
    }
    
    public void setDatastore(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String friend = dataStore.getUser(getTitle()).getFriends()[0];
        dataStore.getUser(getTitle()).getMessageBox(friend).addMessage(new Message(getTitle(), friend, textField.getText(), new Date(), Status.SENT));

        textField.setText(null);
    }

    @Override
    public void messageAdded() {
        String friend = dataStore.getUser(getTitle()).getFriends()[0];
        Message[] messages = dataStore.getUser(getTitle()).getMessageBox(friend).getMessages();
        StringBuilder sb = new StringBuilder();
        for (Message m : messages) {
            sb.append(m.getText() + "\n");
        }
        textArea.setText(sb.toString());
    }
    
}
