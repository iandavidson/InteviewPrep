package software.davidson.ian.binaryTree.goodLeafNodesPairs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class GoodLeafNodePairs {
/*
    1530. Number of Good Leaf Nodes Pairs

    You are given the root of a binary tree and an integer distance.
    A pair of two different leaf nodes of a binary tree is said to be good if the length of the shortest path between
     them is less than or equal to distance.
    Return the number of good leaf node pairs in the tree.



            Example 1:

    Input: root = [1,2,3,null,4], distance = 3
    Output: 1
    Explanation: The leaf nodes of the tree are 3 and 4 and the length of the shortest path between them is 3. This
    is the only good pair.

    Example 2:

    Input: root = [1,2,3,4,5,6,7], distance = 3
    Output: 2
    Explanation: The good pairs are [4,5] and [6,7] with shortest path = 2. The pair [4,6] is not good because the
    length of ther shortest path between them is 4.

    Example 3:

    Input: root = [7,1,4,6,null,5,3,null,null,null,null,null,2], distance = 3
    Output: 1
    Explanation: The only good pair is [2,5].



    Constraints:

    The number of nodes in the tree is in the range [1, 210].
            1 <= Node.val <= 100
            1 <= distance <= 10
*/

    public int countPairs(TreeNode root, int distance) {
        //construct adjency list to traverse tree anyway we want
        Map<TreeNode, List<TreeNode>> map = new HashMap<>();
        buildMap(map, null, root);

        //use built map to determine leafs, these will be our starting places
        Set<TreeNode> leafs = new HashSet<>();
        for (TreeNode node : map.keySet()) {
            if (map.get(node).size() == 1 && node != root) {
                leafs.add(node);
            }
        }

        int found = 0;
        for (TreeNode leaf : leafs) {
            Queue<TreeNode> bfsQueue = new LinkedList<>();
            Set<TreeNode> seen = new HashSet<>();
            bfsQueue.add(leaf);
            seen.add(leaf);

            for (int i = 0; i <= distance; i++) {

                int size = bfsQueue.size();
                for (int j = 0; j < size; j++) {

                    TreeNode currNode = bfsQueue.remove();
                    if (leafs.contains(currNode) && currNode != leaf) {
                        found++;
                    }
                    if (map.containsKey(currNode)) {
                        for (TreeNode neighbor : map.get(currNode)) {
                            if (!seen.contains(neighbor)) {
                                bfsQueue.add(neighbor);
                                seen.add(neighbor);
                            }
                        }
                    }
                }
            }
        }

        return found / 2;
    }

    private void buildMap(Map<TreeNode, List<TreeNode>> map, TreeNode parent, TreeNode current) {
        if (current == null) {
            return;
        }

        map.put(current, new ArrayList<>());

        if (parent != null) {
            map.get(current).add(parent);
        }

        if (current.left != null) {
            map.get(current).add(current.left);
        }

        if (current.right != null) {
            map.get(current).add(current.right);
        }

        buildMap(map, current, current.left);
        buildMap(map, current, current.right);
    }

    public static class TreeNode {
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
}


