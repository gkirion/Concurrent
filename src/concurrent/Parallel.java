/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrent;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author george
 */
public class Parallel implements Runnable {
    
    private ArrayList<Integer> arrayList;
    
    public Parallel() {
        arrayList = new ArrayList<>();
    }

    @Override
    public synchronized void run() {
        arrayList.add(arrayList.size());
    }
    
    public int getSize() {
        return arrayList.size();
    }
   
}
