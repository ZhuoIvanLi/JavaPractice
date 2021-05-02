package Blind75.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 133. Clone Graph: Given a reference of a node in a connected undirected graph.
 * Return a deep copy (clone) of the graph.
 * Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.
 *
 * class Node {
 *     public int val;
 *     public List<Node> neighbors;
 * }
 *
 *
 * Test case format:
 * For simplicity sake, each node's value is the same as the node's index (1-indexed). For example, the first node with
 * val = 1, the second node with val = 2, and so on. The graph is represented in the test case using an adjacency list.
 * Adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.
 * The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.
 *
 * Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
 * Output: [[2,4],[1,3],[2,4],[1,3]]
 * Explanation: There are 4 nodes in the graph.
 * 1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 * 3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 *
 * Input: adjList = [[]]
 * Output: [[]]
 * Explanation: Note that the input contains one empty list. The graph consists of only one node with val = 1 and it does not have any neighbors.
 *
 * Input: adjList = [[2],[1]]
 * Output: [[2],[1]]
 *
 * Solution: Recursion and hash table. Use hash table contains all node, and use recursion to return the correct node if exists
 */
public class CloneGraph {
    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
        public Node cloneGraph(Node node) {
            HashMap<Integer, Node> hm = new HashMap<>();
            return clone(node, hm);

//         List<Node> al = new ArrayList<Node>();
//         boolean[] alMem = new boolean[100];
//         addNodes(node, al, alMem);

//         //System.out.println(al);

//         boolean[] mem = new boolean[al.size()];
//         addNeighbors(node, al, mem);

//         return al.get(0);
        }

//     public void addNodes(Node node, List<Node> al, boolean[] alMem) {
//         if (alMem[node.val - 1]) return;

//         alMem[node.val - 1] = true;
//         // find the number of nodes and add them in array list
//         while (al.size() < node.val) {
//             al.add(new Node(al.size() + 1));
//         }

//         for (Node n : node.neighbors) {
//             addNodes(n, al, alMem);
//         }

//     }

//     public void addNeighbors(Node node, List<Node> al, boolean[] mem) {
//         if (mem[node.val - 1]) return;

//         mem[node.val - 1] = true;

//         List<Node> nei = new ArrayList<>();
//         Node current = al.get(node.val - 1);

//         if (node.neighbors.size() != 0) {
//             for (Node n : node.neighbors) {
//                 nei.add(al.get(n.val - 1));
//                 addNeighbors(n, al, mem);
//             }
//         }

//         current.neighbors = nei;

//     }

        public Node clone(Node node, HashMap<Integer, Node> hm) {
            if (node == null) return null;

            if (hm.containsKey(node.val)) {
                return hm.get(node.val);
            }

            hm.put(node.val, new Node(node.val));
            for (Node n : node.neighbors) {
                hm.get(node.val).neighbors.add(clone(n, hm));
            }

            return hm.get(node.val);
        }
}
