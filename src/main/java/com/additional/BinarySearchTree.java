package com.additional;

public class BinarySearchTree {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return root;
        }
    }

    //************************************************************************************************************
    // Sorted Array to BST
    //************************************************************************************************************

    public TreeNode sortedArrayToBST(int[] num) {

        if (num.length == 0) {
            return null;
        }
        TreeNode head = helperSortedArrayToBST(num, 0, num.length - 1);
        return head;
    }

    public TreeNode helperSortedArrayToBST(int[] num, int low, int high) {

        if (low > high) { // Done
            return null;
        }
        int mid = (low + high) / 2;
        TreeNode node = new TreeNode(num[mid]);
        node.left = helperSortedArrayToBST(num, low, mid - 1);
        node.right = helperSortedArrayToBST(num, mid + 1, high);
        return node;
    }

    //************************************************************************************************************
    // isValidBST
    //************************************************************************************************************

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode root, long minVal, long maxVal) {
        if (root == null) return true;
        if (root.val >= maxVal || root.val <= minVal) return false;
        return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
    }

}
