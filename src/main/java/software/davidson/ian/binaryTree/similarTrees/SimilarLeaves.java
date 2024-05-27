package software.davidson.ian.binaryTree.similarTrees;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SimilarLeaves {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>();
        getLeafs(root1, list1);
        System.out.println("list1: " + list1);

        List<Integer> list2 = new ArrayList<>();
        getLeafs(root2, list2);
        System.out.println("list2: " + list2);

        if (list1.size() != list2.size()) {
            return false;
        }

        for (int i = 0; i < list1.size(); i++) {
            System.out.println("1: " + list1.get(i) + " 2: " + list2.get(i));
            if (!Objects.equals(list1.get(i), list2.get(i))) {
                System.out.println("here");
                return false;
            }
        }

        return true;
    }


    private void getLeafs(TreeNode root, List<Integer> current) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            current.add(root.val);
        }

        getLeafs(root.left, current);
        getLeafs(root.right, current);
    }
}
