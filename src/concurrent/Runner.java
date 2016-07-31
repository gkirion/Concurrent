/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrent;

import java.util.Date;
import java.util.Random;

/**
 *
 * @author george
 */
public class Runner implements Runnable {
    Friend friend;
    
    public Runner() {}
    
    public Runner(Friend friend) {
        this.friend = friend;
    }
    
    public void setFriend(Friend friend) {
        this.friend = friend;
    }

    @Override
    public void run() {
        int action;
        Random r = new Random();
        for (int i = 0; i < 1000; i++) {
            action = r.nextInt(7);
            System.out.println(action);
            if (action == 2 || action == 4) {
                friend.addMessage(new Message("", "", "Hello", new Date(), Status.NOT_SENT));
            }
            else {
                friend.getMessages();
            }     
        }
    }
    
}
