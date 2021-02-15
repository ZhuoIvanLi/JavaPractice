package Amazon;

/**
 * LeetCode: 297. Serialize and Deserialize Binary Tree
 */
public class SerializeDeserializeBinaryTree {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "";

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            TreeNode node = q.poll();

            if (node == null) {
                sb.append("n,");
            } else {
                q.add(node.left);
                q.add(node.right);
                sb.append(String.valueOf(node.val) + ",");
            }
        }

        return sb.toString();

    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == "") return null;

        int pre = 0;
        TreeNode ans = new TreeNode(Integer.parseInt(data.substring(pre, data.indexOf(',', pre))));
        Queue<TreeNode> q = new LinkedList<>();

        pre = data.indexOf(',', pre) + 1;
        q.add(ans);

        while (!q.isEmpty()) {
            TreeNode node = q.poll();

            String s1 = data.substring(pre, data.indexOf(',', pre));
            pre = data.indexOf(',', pre) + 1;
            String s2 = data.substring(pre, data.indexOf(',', pre));
            pre = data.indexOf(',', pre) + 1;

            if (s1.compareTo("n") != 0) {
                TreeNode left = new TreeNode(Integer.parseInt(s1));
                node.left = left;
                q.add(left);
            }

            if (s2.compareTo("n") != 0) {
                TreeNode right = new TreeNode(Integer.parseInt(s2));
                node.right = right;
                q.add(right);
            }

        }

        return ans;
    }
}
