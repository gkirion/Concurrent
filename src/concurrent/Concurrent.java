package concurrent;

import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Concurrent {

    public static void main(String[] args) {
        Message message = new Message("", "", "Hello", new Date(), Status.NOT_SENT);
        System.out.println(message.getText());
        System.out.println(message.getDate());
        System.out.println(message.getStatus());
        
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
        Friend friend = new Friend();
        dataStore.getUser("gkirion").addFriend("fotini@gmail.com", friend);
        friend = new Friend();
        dataStore.getUser("fotini@gmail.com").addFriend("gkirion", friend);
        dataStore.getUser("gkirion").getFriend("fotini@gmail.com").addMessage(message);
        dataStore.getUser("fotini@gmail.com").getFriend("gkirion").addMessage(message);
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        Friend[] friends = user.getFriends();
        for (Friend f : friends) {
            try {
                System.out.println(f.getMessage(0));
            }
            catch (IndexOutOfBoundsException e) {
            }
        }
        
        boolean ret = dataStore.register("gkirion", new User("George", "cskir88"));
        System.out.println(ret);
        ret = dataStore.register("bebis", new User("Baby", "bebis88"));
        System.out.println(ret);
        User u = dataStore.login("gkirion", "cskir88");
        System.out.println(u);
        u.addFriend("bebis", new Friend());
        User l = dataStore.login("bebis", "bebis88");
        System.out.println(l);
        for (Friend f : u.getFriends()) {
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
