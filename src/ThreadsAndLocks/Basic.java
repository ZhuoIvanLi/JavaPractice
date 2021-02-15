package ThreadsAndLocks;

import java.util.concurrent.locks.Lock;

public class Basic implements Runnable{
    private Lock lock; // Use of lock

    public void run(){
        System.out.println("Run in runnable thread.");
    }

    public void main(String[] args) {
        Basic basic =  new Basic(); // Create and implement runnable in Java
        Thread t = new Thread(basic); // put runnable object in thread constructor
        t.start(); // use start();

        lock.lock();
        lock.unlock();

    }
}
