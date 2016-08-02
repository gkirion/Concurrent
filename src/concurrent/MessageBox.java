/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrent;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author george
 */
public class Friend {
    private ArrayList<Message> messages;
    private Semaphore readersLock, writersLock;
    private ReentrantReadWriteLock readWriteLock;
    private int readers;
    
    public Friend() {
        messages = new ArrayList<>();
        readersLock = new Semaphore(1);
        writersLock = new Semaphore(1);
        readWriteLock = new ReentrantReadWriteLock();
        readers = 0;
    }
    
   
    
    // readers writers 3s
    // synchronized 3s
    // read write lock 2s
    
    // sync loop 1 4s
    // sync loop 10 4s
    // sync loop 100 4s
    // sync loop 1000 4s
    
    // readers writers loop 1 3s
    // readers writers loop 10 2s
    // readers writers loop 100 2s
    // readers writers loop 1000 3s
    
    // readwrite lock loop 1 3s
    // readwrite lock loop 10 2s
    // readwrite lock loop 100 2s
    // readwrite lock loop 1000 2s
    
    public void addMessage(Message message) {
        try {
            readWriteLock.writeLock().lock();
            messages.add(message);
            /*try {
              //  writersLock.acquire();
                messages.add(message);
            } 
           // catch(InterruptedException exception) {
            
            //} 
            finally {
                readWriteLock.writeLock().unlock();
                //writersLock.release();
            }*/
            //writersLock.acquire();
        }
        finally {
            //writersLock.release();
            readWriteLock.writeLock().unlock();
        }
    }
    
    
    public Message getMessage(int i) throws IndexOutOfBoundsException {
        Message m;
        readWriteLock.readLock().lock();
        try {
            m = messages.get(i);
        }
        finally {
            readWriteLock.readLock().unlock();
        }
        return m;
    }
    
    public Message[] getMessages() {
        /*try {
            readersLock.acquire();
            if (readers == 0) {
                writersLock.acquire();
            }
            readers++;
        } catch (InterruptedException ex) {
            Logger.getLogger(Friend.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            readersLock.release();
        }*/
        
        readWriteLock.readLock().lock();
        
        try {
            Thread.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(Friend.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        readWriteLock.readLock().unlock();
        return null;
        
        /*
        try {
            readersLock.acquire();
            if (readers == 1) {
                writersLock.release();
            }
            readers--;
        }
        catch (InterruptedException exception) {
        
        }
        finally {
            readersLock.release();
        }
        
        return null;
        
        //readWriteLock.readLock().lock();
        /*try {
            //readersLock.acquire();
            if (readers == 0) {
                readWriteLock.writeLock().lock();
               // writersLock.acquire();
            }
            readers++;
        }
        //catch (InterruptedException exception) {
        
        //}
        finally {
            readWriteLock.readLock().unlock();
            //readersLock.release();
        }*/
        /*int size = messages.size();
        Message[] m = new Message[size];
        for (int i = 0; i < size; i++) {
            m[i] = messages.get(i);
        }*//*
        readWriteLock.readLock().lock();
        try {
            //readersLock.acquire();
            if (readers == 1) {
                readWriteLock.writeLock().unlock();
                //writersLock.release();
            }
            readers--;
        }
        //catch (InterruptedException exception) {
        
        //}
        finally {
            readWriteLock.readLock().unlock();
           // readersLock.release();
        }
        */
        //readWriteLock.readLock().unlock();
        
        //return m;
    }
}
