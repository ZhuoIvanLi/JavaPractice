package Blind75.Tree;

import DataStructure.TreeNode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 297. Serialize and Deserialize Binary Tree: Serialization is the process of converting a data structure or object into
 * a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link
 * to be reconstructed later in the same or another computer environment.Design an algorithm to serialize and deserialize a binary tree.
 * There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that
 * a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 *
 * Clarification: The input/output format is the same as how LeetCode serializes a binary tree. You do not necessarily
 * need to follow this format, so please be creative and come up with different approaches yourself.
 *
 * EX 1:
 * Input: root = [1,2,3,null,null,4,5]
 * Output: [1,2,3,null,null,4,5]
 *
 * Example 2:
 * Input: root = []
 * Output: []
 *
 * Example 3:
 * Input: root = [1]
 * Output: [1]
 *
 * Example 4:
 * Input: root = [1,2]
 * Output: [1,2]
 *
 * Solution: use BFS to serialize the binary tree to string. For deserialize also use Queue to setup nodes left and right.
 */
public class SerializeDeserializeBT {
    public static void main(String[] args) throws FileNotFoundException {
        TreeNode root = new TreeNode(1);

        System.out.println(serialize(root));
    }

    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        if (root == null) return "";

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode current = q.poll();

            if (current != null) {
                sb.append(current.value).append(",");
                q.offer(current.left);
                q.offer(current.right);
            } else {
                sb.append("n,");
            }
        }

        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        int pre = 0;
        TreeNode root = new TreeNode(Integer.parseInt(data.substring(0, data.indexOf(',', pre))));
        Queue<TreeNode> q = new LinkedList<>();

        pre = data.indexOf(',', pre) + 1;
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode curr = q.poll();

            // Catch current left number
            String left = data.substring(pre, data.indexOf(',', pre));
            if (!left.equals("n")) {
                TreeNode leftNode = new TreeNode(Integer.parseInt(left));
                curr.left = leftNode;
                q.offer(leftNode);
            }
            pre = data.indexOf(',', pre) + 1; // move to next char

            // Catch current right number
            String right = data.substring(pre, data.indexOf(',', pre));
            if (!right.equals("n")) {
                TreeNode rightNode = new TreeNode(Integer.parseInt(right));
                curr.right = rightNode;
                q.offer(rightNode);
            }
            pre = data.indexOf(',', pre) + 1; // move to next char

        }

        return root;
    }
}
