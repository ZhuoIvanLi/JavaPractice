package ThreadsAndLocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 15.4 Deadlock-free class: Design a class which provides a lock only if there are no possible deadlocks.
 */
public class DeadlockFree {
    public enum VisitState { FRESH, VISITING, VISITED };


    private static DeadlockFree instance;

    private int numberOfLocks = 5;
    private LockNode[] locks;

    // Maps from a process or owner to the order that owner claimed it may call lock in.
    private HashMap<Integer, LinkedList<LockNode>> lockOrder;

    private DeadlockFree(int count) {}
    public static DeadlockFree getInstance(){
        if(instance == null){
            instance = new DeadlockFree(10);
        }
        return instance;
    }

    public static synchronized DeadlockFree initialize(int count) {
        if(instance == null) instance = new DeadlockFree(count);
        return instance;
    }

    public boolean hasCycle(HashMap<Integer, Boolean> touchedNodes, int[] resourcesInOrder) {
        //Check for cycle
        for(int resource : resourcesInOrder){
            if(touchedNodes.get(resource) == false){
                LockNode n = locks[resource];
                if(n.hasCycle(touchedNodes)){
                    return true;
                }
            }
        }

        return false;
    }

    /*
     * To prevent deadlocks, force the processes to declare upfront what order they will need the locks in. Verify that
     * this order does not create a deadlock
     */
    public boolean declare(int ownerId, int[] resourcesInOrder){
        HashMap<Integer, Boolean> touchedNodes = new HashMap<>();

        // add nodes to graphs 1. create nodes and connect them together
        int index = 1;
        touchedNodes.put(resourcesInOrder[0], false);
        for(index = 1; index < resourcesInOrder.length; index++){
            LockNode prev = locks[resourcesInOrder[index - 1]];
            LockNode curr = locks[resourcesInOrder[index]];
            prev.joinTo(curr);
            touchedNodes.put(resourcesInOrder[index], false);
        }

        // 2. check the nodes. If a cycle has created, destroy it and return false
        if(hasCycle(touchedNodes, resourcesInOrder)){
            for(int j = 1; j < resourcesInOrder.length; j++) {
                LockNode prev = locks[resourcesInOrder[j - 1]];
                LockNode curr = locks[resourcesInOrder[j]];
                prev.remove(curr);
            }
            return false;
        }

        // 3. If no cycles detected, Save the order that was declared, so that we can verify that the process is really
        // calling the locks in the order it said it would
        LinkedList<LockNode> list = new LinkedList<LockNode>();
        for(int i = 0; i < resourcesInOrder.length; i++){
            LockNode resource = locks[resourcesInOrder[i]];
            list.add(resource);
        }
        lockOrder.put(ownerId, list);

        return true;
    }

    public class LockNode {
        private ArrayList<LockNode> children;
        private int lockId;
        private int maxLocks;
        private Lock lock;

        public LockNode(int lockId, int maxLocks) {
            this.lockId = lockId;
            this.maxLocks = maxLocks;
        }

        // Join "this" to "node", checking that it doesn't create a cycle
        public void joinTo(LockNode node) { children.add(node); }
        public void remove(LockNode node) { children.remove(node); }

        // check for cycle by doing a depth-first-search
        public boolean hasCycle(HashMap<Integer, Boolean> touchedNodes) {
            VisitState[] visited = new VisitState[maxLocks];
            for(int i = 0; i < maxLocks; i++) {
                visited[i] = VisitState.FRESH;
            }

            return hasCycle(visited, touchedNodes);
        }

        private boolean hasCycle(VisitState[] visited, HashMap<Integer, Boolean> touchedNodes) {
            if(touchedNodes.containsKey(lockId)){
                touchedNodes.put(lockId, true);
            }

            if(visited[lockId] == VisitState.VISITING) {
                // We looped back to this node while still visiting it, so we know there is a cycle.
                return true;
            }else if (visited[lockId] == VisitState.FRESH){
                // Mark as visiting and check if we can get back to this lock id or not
                visited[lockId] = VisitState.VISITING;
                for(LockNode n : children){
                    if(n.hasCycle(visited, touchedNodes)){
                        return true;
                    }
                }
                visited[lockId] = VisitState.VISITED;
            }

            return false;
        }

        public Lock getLock(){
            if(lock == null) lock = new ReentrantLock();
            return lock;
        }

        public int getId() { return lockId; }
    }
}


