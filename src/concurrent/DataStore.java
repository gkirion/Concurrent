/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * @author george
 */
public class DataStore {
    private HashMap<String, User> users;
    private ReentrantReadWriteLock readWriteLock;
    
    public DataStore() {
        users = new HashMap<>();
        readWriteLock = new ReentrantReadWriteLock();
    }
    
    public boolean register(String id, User user) {
        readWriteLock.writeLock().lock();
        try {
            if (users.get(id) == null) {
                users.put(id, user);
                return true;
            }
        } finally {
            readWriteLock.writeLock().unlock();
        }
        return false;
    }
    
    public User login(String id, String password) {
        User user;
        readWriteLock.readLock().lock();
        try {
            user = users.get(id);
            if (user != null) {
                if (user.getPassword().equals(password)) {
                    return user;
                }
            }
        } finally {
            readWriteLock.readLock().unlock();
        }
        return null;
    }
    
    public User getUser(String id) {
        User u;
        readWriteLock.readLock().lock();
        try {
            u = users.get(id);
        }
        finally {
            readWriteLock.readLock().unlock();
        }
        return u;
    }
    
}
