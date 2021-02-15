package SystemDesign;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class SystemDesign {
    /* 9.2 Social Network: design a very large social network like Facebook. How to design an algorithm to show the
    shortest path between two people(A->B->C->D->E)  */
    // Use BFS

    public LinkedList<Person> findPathBiBFS(HashMap<Integer, Person> people, int source, int destination) {
        BFSData sourceData = new BFSData(people.get(source));
        BFSData destinationData = new BFSData(people.get(destination));

        while(!sourceData.isFinished() && !destinationData.isFinished()){
            // Search out from source
            Person collision = searchLevel(people, sourceData, destinationData);
            if(collision != null){
                return mergePaths(sourceData, destinationData, collision.getID());
            }

            // Search out from destination
            collision = searchLevel(people, destinationData, sourceData);
            if(collision != null){
                return mergePaths(sourceData, destinationData, collision.getID());
            }
        }

        return null;
    }

    // Search one level and return collision, if any
    public Person searchLevel(HashMap<Integer, Person> people, BFSData primary, BFSData secondary){
        // search one level at a time. Count how many nodes are in primary level and only do that many nodes.
        int count = primary.toVisit.size();
        for(int i = 0; i< count; i++){
            // pull out first node
            PathNode pathNode = primary.toVisit.poll();
            int personId = pathNode.getPerson().getID();

            //Check if it's already been visited
            if(secondary.visited.containsKey(personId)){
                return pathNode.getPerson();
            }

            // Add friends to Queue
            Person person = pathNode.getPerson();
            ArrayList<Integer> friends = person.getFriends();
            for(int friendId : friends){
                if(!primary.visited.containsKey(friendId)){
                    Person friend= people.get(friendId);
                    PathNode next =  new PathNode(friend, pathNode);
                    primary.visited.put(friendId, next);
                    primary.toVisit.add(next);
                }
            }
        }

        return null;
    }

    public LinkedList<Person> mergePaths(BFSData bfs1, BFSData bfs2, int connection) {
        PathNode end1 = bfs1.visited.get(connection); // end1 -> source
        PathNode end2 = bfs2.visited.get(connection); // end2 -> dest
        LinkedList<Person> pathOne = end1.collapse(false);
        LinkedList<Person> pathTwo = end2.collapse(true);
        pathTwo.removeFirst(); // remove connection
        pathOne.addAll(pathTwo); // add second path
        return pathOne;
    }

    public class PathNode {
        private Person person = null;
        private PathNode previousNode = null;

        public PathNode(Person p, PathNode prev) {
            person= p;
            previousNode = prev;
        }

        public Person getPerson() { return person; }

        public LinkedList<Person> collapse(boolean startsWithRoot){
            LinkedList<Person> path = new LinkedList<Person>();
            PathNode node = this;
            while(node != null){
                if(startsWithRoot){
                    path.addLast(node.person);
                } else {
                    path.addFirst(node.person);
                }
                node=node.previousNode;
            }
            return path;
        }
    }

    public class BFSData {
        public Queue<PathNode> toVisit = new LinkedList<PathNode>();
        public HashMap<Integer, PathNode> visited = new HashMap<Integer, PathNode>();

        public BFSData(Person root) {
            PathNode source = new PathNode(root, null);
            toVisit.add(source);
            visited.put(root.getID(), source);
        }

        public boolean isFinished(){
            return toVisit.isEmpty();
        }
    }

    public class Server {
        private HashMap<Integer, Machine> machines = new HashMap<Integer, Machine>();
        private HashMap<Integer, Integer> personToMachineMap = new HashMap<Integer, Integer>();

        public Machine getMachineWithId(int machineID){
            return machines.get(machineID);
        }

        public int getMachineIDForUser(int personID) {
            Integer machineID = personToMachineMap.get(personID);
            return machineID == null ? -1 : machineID;
        }

        public Person getPersonWithID(int personID) {
            Integer machineID = personToMachineMap.get(personID);
            if(machineID == null) { return null; }

            Machine machine = getMachineWithId(machineID);
            if(machine == null) return null;

            return machine.getPersonWithID(personID);
        }
    }

    public class Person {
        private ArrayList<Integer> friends = new ArrayList<Integer>();
        private int id;
        private String info;

        public Person(int id) {this.id = id;}
        public String getInfo(){ return info; }
        // Getters and setters

        public ArrayList<Integer> getFriends() {
            return friends;
        }

        public int getID() {
            return id;
        }

        public void addFriend(int person){ friends.add(person); }
    }

    /*
        9.5 Cache: a search engine has 100 machines to respond to search queries, which may call processSearch(string query)
        to get the result. The machine respond at random. processSearch is expensive
     */
    public class Cache {
        public static int MAX_SIZE = 10;
        public Node head, tail;
        public HashMap<String, Node> hm;
        public int size = 0;

        public Cache() {
            hm = new HashMap<String, Node> ();
        }

        // Move Node to the front of the linked list
        public void moveToFront(Node node){...}
        public void moveToFront(String query) {...}

        // Remove the node from linked list
        public void removeFromLinkedList(Node node) {...}

        // Get result from cache, and updates linked list
        public String[] getResults(String query) {
            if(!hm.containsKey(query)) return null;

            Node node = hm.get(query);
            moveToFront(node); // update freshness
            return node.results;
        }

        // Insert result into linked list and hash
        public void insertResult(String query, String[] results) {
            if(hm.containsKey(query)) { // update values
                Node node=hm.get(query);
                node.results = results;
                moveToFront(node); //update freshness
                return;
            }

            Node node = new Node(query, results);
            moveToFront(node);
            hm.put(query, node);

            if(size > MAX_SIZE) {
                hm.remove((tail.query));
                removeFromLinkedList(tail);
            }

        }
    }

}
