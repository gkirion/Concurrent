package concurrent;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Concurrent {

    public static void main(String[] args) {
        /*Message message = new Message("", "", "Hello", new Date(), Status.NOT_SENT);
        System.out.println(message.getText());
        System.out.println(message.getDate());
        System.out.println(message.getStatus());
        */
        //Runner runner = new Runner(friend);
        //Thread[] thread = new Thread[4];
        /*for (int i = 0; i < 4; i++) {
            thread[i] = new Thread(runner);
            thread[i].start();
        }
        try {
            for (int i = 0; i < 4; i++) {
                thread[i].join();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Concurrent.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        DataStore dataStore = new DataStore();
        User user = new User("George", "cskir88");
        dataStore.register("gkirion", user);
        user = new User("Fotini", "george031006");
        dataStore.register("fotini@gmail.com", user);
        MessageBox messageBox = new MessageBox();
        dataStore.getUser("gkirion").addFriend("fotini@gmail.com", messageBox);
        dataStore.getUser("fotini@gmail.com").addFriend("gkirion", messageBox);
        Message message = new Message("gkirion", "fotini@gmail.com", "geia sou mpempi!", new Date(), Status.SENT);
        dataStore.getUser("gkirion").getMessageBox("fotini@gmail.com").addMessage(message);
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        MessageBox[] messageBoxes = user.getMessageBoxes();
        for (MessageBox f : messageBoxes) {
            try {
                System.out.println(f.getMessage(0).getText());
            }
            catch (IndexOutOfBoundsException e) {
            }
        }
        Window gkirionWindow = new Window("gkirion");
        dataStore.getUser("gkirion").getMessageBox("fotini@gmail.com").addMessageListener(gkirionWindow);
        gkirionWindow.setDatastore(dataStore);
        Window fotiniWindow = new Window("fotini@gmail.com");
        dataStore.getUser("fotini@gmail.com").getMessageBox("gkirion").addMessageListener(fotiniWindow);
        fotiniWindow.setDatastore(dataStore);
        /*
        boolean ret = dataStore.register("gkirion", new User("George", "cskir88"));
        System.out.println(ret);
        ret = dataStore.register("bebis", new User("Baby", "bebis88"));
        System.out.println(ret);
        User u = dataStore.login("gkirion", "cskir88");
        System.out.println(u);
        u.addFriend("bebis", new MessageBox());
        User l = dataStore.login("bebis", "bebis88");
        System.out.println(l);
        for (MessageBox f : u.getFriends()) {
            f.addMessage(new Message("gkirion", "bebis", "geia sou mpempi", new Date(), Status.SENT));
            System.out.println(f.getMessage(0).getText());
            System.out.println(f.getMessage(0).getDate());
            System.out.println(f.getMessage(0).getStatus());
        }
       /* Message[] messages = friend.getMessages();
        for (Message m : messages) {
            System.out.println(m.getDate());
        }*/
    }
}
