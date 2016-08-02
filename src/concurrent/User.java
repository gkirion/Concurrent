/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrent;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * @author george
 */
public class User {
    private String username;
    private String password;
    private HashMap<String, MessageBox> messageBoxes;
    private ReentrantReadWriteLock readWriteLock;
    
    public User() {
        messageBoxes = new HashMap<>();
        readWriteLock = new ReentrantReadWriteLock();
    }
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        messageBoxes = new HashMap<>();
        readWriteLock = new ReentrantReadWriteLock();
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void addFriend(String id, MessageBox messageBox) {
        readWriteLock.writeLock().lock();
        try {
            messageBoxes.put(id, messageBox);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }
    
    public String[] getFriends() {
        String[] friends = new String[messageBoxes.size()];
        int i = 0;
        readWriteLock.readLock().lock();
        try {
            for (String f : messageBoxes.keySet()) {
                friends[i] = f;
                i++;
            }
        }
        finally {
            readWriteLock.readLock().unlock();
        }
        return friends;
    }
    
    public MessageBox getMessageBox(String id) {
        MessageBox messageBox;
        readWriteLock.readLock().lock();
        try {
            messageBox = messageBoxes.get(id);
        }
        finally {
            readWriteLock.readLock().unlock();
        }
        return messageBox;
    }
    
    public MessageBox[] getMessageBoxes() {
        MessageBox[] m = new MessageBox[messageBoxes.size()];
        int i = 0;
        readWriteLock.readLock().lock();
        try {
            for (String key : messageBoxes.keySet()) {
                m[i] = messageBoxes.get(key);
                i++;
            }
        } finally {
            readWriteLock.readLock().unlock();
        }
        return m;
    }
    
}
