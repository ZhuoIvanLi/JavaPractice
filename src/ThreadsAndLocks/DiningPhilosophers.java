package ThreadsAndLocks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 15.3 Dining Philosopher: philosophers are siting around circular table and every one has one chopstick. One philosopher
 * needs at least two chopsticks to eat and always pick up the left chopstick before the right one. A deadlock can occur
 * if they pick up at the same time. Implement to prevents deadlock
 */
public class DiningPhilosophers {
    public class Chopstick {
        private Lock lock;

        public Chopstick(){
            lock = new ReentrantLock();
        }

        // Will cause deadlock
//        public void pickUp() {
//            lock.lock();
//        }

        public boolean pickUp() {
            return lock.tryLock();
        }

        public void putDown() {
            lock.unlock();
        }
    }

    public class Philosopher extends Thread{
        private int bites = 10;
        private Chopstick left, right;

        public Philosopher(Chopstick l, Chopstick r) {
            this.left = l;
            this.right = r;
        }

        public void eat(){
            if(pickUp()) {
                chew();
                putDown();
            }
        }

        public boolean pickUp(){
            if(!left.pickUp()){
                return false;
            }
            if(!right.pickUp()){
                left.putDown();
                return false;
            }

            return true;
        }

        public void chew(){}

        public void putDown(){
            left.putDown();
            right.putDown();
        }

        public void run(){
            for(int i = 0; i < bites; i++) {
                eat();
            }
        }

    }
}
