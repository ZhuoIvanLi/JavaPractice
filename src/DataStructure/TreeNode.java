package DataStructure;

public class TreeNode {
    public TreeNode left, right;
    public int value;

    public TreeNode(int value) {
        this.value = value;
    }

    public void createSubnodes(int l, int r, TreeNode node) {
        TreeNode lNode = new TreeNode(l);
        TreeNode rNode = new TreeNode(r);

        node.left = lNode;
        node.right = rNode;
    }

    public static TreeNode generateSampleBinaryTree() {
        TreeNode root = new TreeNode(5);
        root.createSubnodes(3, 8, root);

        root.left.createSubnodes(11, 12, root.left);
        root.right.createSubnodes(15, 8, root.right);

        return root;
    }
}
