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
    private HashMap<String, Friend> friends;
    private ReentrantReadWriteLock readWriteLock;
    
    public User() {
        friends = new HashMap<>();
        readWriteLock = new ReentrantReadWriteLock();
    }
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        friends = new HashMap<>();
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
    
    public void addFriend(String id, Friend friend) {
        readWriteLock.writeLock().lock();
        try {
            friends.put(id, friend);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }
    
    public Friend getFriend(String id) {
        Friend f;
        readWriteLock.readLock().lock();
        try {
            f = friends.get(id);
        }
        finally {
            readWriteLock.readLock().unlock();
        }
        return f;
    }
    
    public Friend[] getFriends() {
        Friend[] friend = new Friend[friends.size()];
        int i = 0;
        readWriteLock.readLock().lock();
        try {
            for (String key : friends.keySet()) {
                friend[i] = friends.get(key);
                i++;
            }
        } finally {
            readWriteLock.readLock().unlock();
        }
        return friend;
    }
    
}
