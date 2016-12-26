package com.additional;

import java.util.*;

class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode getLeft() {
            return left;
        }

        public TreeNode getRight() {
            return right;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }
    }

    class BSTIterator {
        private Deque<TreeNode> stack = new LinkedList<TreeNode>();

        public BSTIterator(TreeNode root) {
            pushAll(root);
        }

        /**
         * @return whether we have a next smallest number
         */
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /**
         * @return the next smallest number
         */
        public int next() {
            TreeNode tmpNode = stack.pop();
            pushAll(tmpNode.right);
            return tmpNode.val;
        }

        private void pushAll(TreeNode node) {
            for (; node != null; stack.push(node), node = node.left) ;
        }

    }

    public class BinaryTree {

        //************************************************************************************************************
        // max depth of the tree
        //************************************************************************************************************

        public int maxDepth(TreeNode root) {

            if (root == null)
                return 0;
            else
                return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
        }

        //************************************************************************************************************
        // Same Tree Recursive
        //************************************************************************************************************

        public boolean isSameTreeRecursive(TreeNode p, TreeNode q) {

            if (p == null && q == null) return true;
            if (p == null || q == null) return false;

            if (p.val == q.val)
                return isSameTreeRecursive(p.left, q.left) && isSameTreeRecursive(p.right, q.right);
            return false;
        }

        //************************************************************************************************************
        // Same Tree Non-recursive - using DFS (Stack) - pop & push
        //************************************************************************************************************

        public boolean isSameTree(TreeNode p, TreeNode q) {

            Deque<TreeNode> stack_p = new LinkedList<>();
            Deque<TreeNode> stack_q = new LinkedList<>();

            if (p != null) stack_p.push(p);
            if (q != null) stack_q.push(q);

            while (!stack_p.isEmpty() && !stack_q.isEmpty()) {

                TreeNode pn = stack_p.pop();
                TreeNode qn = stack_q.pop();

                if (pn.val != qn.val) return false;

                if (pn.right != null) stack_p.push(pn.right);
                if (qn.right != null) stack_q.push(qn.right);
                if (stack_p.size() != stack_q.size()) return false;

                if (pn.left != null) stack_p.push(pn.left);
                if (qn.left != null) stack_q.push(qn.left);
                if (stack_p.size() != stack_q.size()) return false;
            }

            return stack_p.size() == stack_q.size();
        }

        //************************************************************************************************************
        // Recursive
        //************************************************************************************************************

        public TreeNode invertTreeRecursive(TreeNode root) {

            if (root == null) {
                return null;
            }

            final TreeNode left = root.left, right = root.right;
            root.left = invertTreeRecursive(right);
            root.right = invertTreeRecursive(left);
            return root;
        }

        //************************************************************************************************************
        // DFS - uses Stack - Pop & Push
        //************************************************************************************************************

        public TreeNode invertTreeDFS(TreeNode root) {

            if (root == null) {
                return null;
            }

            final Deque<TreeNode> stack = new LinkedList<>();
            stack.push(root);

            while (!stack.isEmpty()) {

                final TreeNode node = stack.pop();
                final TreeNode left = node.left;
                node.left = node.right;
                node.right = left;

                if (node.left != null) {
                    stack.push(node.left);
                }
                if (node.right != null) {
                    stack.push(node.right);
                }
            }
            return root;
        }

        //************************************************************************************************************
        // BFS - uses Queue - Poll & Offer
        //************************************************************************************************************

        public TreeNode invertTree(TreeNode root) {

            if (root == null) {
                return null;
            }

            final Deque<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            while (!queue.isEmpty()) {

                final TreeNode node = queue.poll();
                final TreeNode left = node.left;
                node.left = node.right;
                node.right = left;

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            return root;
        }

        //************************************************************************************************************
        // PreOrder - root, left, right
        //************************************************************************************************************

        public void preOrder(TreeNode node) {
            if (node != null) {
                System.out.println(node.val);
                preOrder(node.left);
                preOrder(node.right);
            }
        }

        //************************************************************************************************************
        // inOrder - left, root, right
        //************************************************************************************************************

        public void inOrder(TreeNode node) {
            if (node != null) {

                inOrder(node.left);
                System.out.println(node.val);
                inOrder(node.right);
            }
        }

        //************************************************************************************************************
        // PostOrder - left, right, root
        //************************************************************************************************************

        public void postOrder(TreeNode node) {

            if (node != null) {

                postOrder(node.left);
                postOrder(node.right);
                System.out.println(node.val);

            }
        }

        //************************************************************************************************************
        // Level Order - Use Queue
        //************************************************************************************************************

        public List<List<Integer>> levelOrder(TreeNode root) {
            Deque<TreeNode> queue = new LinkedList<TreeNode>();
            List<List<Integer>> wrapList = new LinkedList<List<Integer>>();

            if(root == null) return wrapList;

            queue.offer(root);

            while(!queue.isEmpty()){

                int levelNum = queue.size();
                List<Integer> subList = new LinkedList<Integer>();

                for(int i=0; i < levelNum; i++) {

                    if(queue.peek().left != null) queue.offer(queue.peek().left);
                    if(queue.peek().right != null) queue.offer(queue.peek().right);
                    subList.add(queue.poll().val);
                }

                wrapList.add(subList);
            }

            return wrapList;
        }

        //************************************************************************************************************
        // LCA
        //************************************************************************************************************

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

            if (root == null || root == p || root == q) return root;

            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);

            if (left == null)
                return right;
            else
                if (right == null)
                    return left;
                else
                    return root;
            //return left == null ? right : right == null ? left : root;
        }

        //************************************************************************************************************
        // is Balanced Binary Tree
        //************************************************************************************************************

        public Boolean isBalanced(TreeNode root) {

            if (root == null) return true;

            int left = maxDepth(root.left);
            int right = maxDepth(root.right);

            return Math.abs(left - right) <= 1 && isBalanced(root.left) && isBalanced(root.right);
        }

        //************************************************************************************************************
        // Max Value
        //************************************************************************************************************

        int maxValue;

        public int maxPathSum(TreeNode root) {
            maxValue = Integer.MIN_VALUE;
            maxPathDown(root);
            return maxValue;
        }

        private int maxPathDown(TreeNode node) {

            if (node == null) return 0;

            int left = Math.max(0, maxPathDown(node.left));
            int right = Math.max(0, maxPathDown(node.right));

            maxValue = Math.max(maxValue, left + right + node.val);
            return Math.max(left, right) + node.val;

        }

        //************************************************************************************************************
        // Symmetric  recursive
        //************************************************************************************************************

        public boolean isSymmetric(TreeNode root) {
            return root==null || isSymmetricHelp(root.left, root.right);
        }

        private boolean isSymmetricHelp(TreeNode left, TreeNode right){

            if(left==null || right==null)
                return left==right;

            if(left.val != right.val)
                return false;

            return isSymmetricHelp(left.left, right.right) && isSymmetricHelp(left.right, right.left);
        }

        //************************************************************************************************************
        // Symmetric non-recursive
        //************************************************************************************************************

        public boolean isSymmetricIterative(TreeNode root) {

            if(root==null)  return true;

            Deque<TreeNode> stack = new LinkedList<>();
            TreeNode left, right;

            if(root.left!=null){
                if(root.right==null) return false;
                stack.push(root.left);
                stack.push(root.right);
            }
            else if(root.right!=null){
                return false;
            }

            while(!stack.isEmpty()){
                if(stack.size()%2!=0)   return false;
                right = stack.pop();
                left = stack.pop();
                if(right.val!=left.val) return false;

                if(left.left!=null){
                    if(right.right==null)   return false;
                    stack.push(left.left);
                    stack.push(right.right);
                }
                else if(right.right!=null){
                    return false;
                }

                if(left.right!=null){
                    if(right.left==null)   return false;
                    stack.push(left.right);
                    stack.push(right.left);
                }
                else if(right.left!=null){
                    return false;
                }
            }

            return true;
        }

        //************************************************************************************************************
        // Serialize/ DeSerialize  recursive
        //************************************************************************************************************

        public String serialize(TreeNode root) {
            return serial(new StringBuilder(), root).toString();
        }

        private StringBuilder serial(StringBuilder str, TreeNode root) {
            if (root == null) return str.append("#");
            str.append(root.val).append(",");
            serial(str, root.left).append(","); // note execution order
            return serial(str, root.right);
        }

        public TreeNode deserialize(String data) {
            return deserial(new LinkedList<>(Arrays.asList(data.split(","))));
        }

        private TreeNode deserial(LinkedList<String> queue) {
            String val = queue.poll(); // return null if empty
            if ("#".equals(val)) return null;
            TreeNode root = new TreeNode(Integer.valueOf(val));
            root.left = deserial(queue);
            root.right = deserial(queue);
            return root;
        }

        //************************************************************************************************************
        // hasPathSum
        //************************************************************************************************************

        public boolean hasPathSum(TreeNode root, int sum) {

            if(root == null) return false;

            if(root.left == null && root.right == null && sum - root.val == 0) return true;

            return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
        }

        //************************************************************************************************************
        // all paths from root to leaf
        //************************************************************************************************************

        public List<String> binaryTreePaths(TreeNode root) {

            List<String> result = new ArrayList<String>();
            if (root != null) searchBT(root, "", result);
            return result;
        }

        private void searchBT(TreeNode root, String path, List<String> result) {

            if (root.left == null && root.right == null) result.add(path + root.val);
            if (root.left != null) searchBT(root.left, path + root.val + "->", result);
            if (root.right != null) searchBT(root.right, path + root.val + "->", result);
        }

        //************************************************************************************************************
        // kth Smallest
        //************************************************************************************************************

        public int kthSmallest(TreeNode root, int k) {

            int count = countNodes(root.left);
            if (k <= count) {
                return kthSmallest(root.left, k);
            } else if (k > count + 1) {
                return kthSmallest(root.right, k-1-count); // 1 is counted as current node
            }

            return root.val;
        }

        public int countNodes(TreeNode n) {

            if (n == null) return 0;

            return 1 + countNodes(n.left) + countNodes(n.right);
        }

        //************************************************************************************************************
        // Flatten Binary Tree to Linked List
        //************************************************************************************************************

        private TreeNode prev = null;

        public void flatten(TreeNode root) {

            if (root == null)
                return;
            flatten(root.right);
            flatten(root.left);
            root.right = prev;
            root.left = null;
            prev = root;
        }

        //************************************************************************************************************
        // Given a binary tree where all the right nodes are either leaf nodes with a sibling
        // (a left node that shares the same parent node) or empty, flip it upside down and turn it into a tree
        // where the original right nodes turned into left leaf nodes. Return the new root.
        //************************************************************************************************************


        public TreeNode upsideDownBinaryTree(TreeNode root) {
            if (root == null || root.left == null && root.right == null)
                return root;

            TreeNode newRoot = upsideDownBinaryTree(root.left);

            root.left.left = root.right;
            root.left.right = root;

            root.left = null;
            root.right = null;

            return newRoot;
        }


        //************************************************************************************************************
        // Main method
        //************************************************************************************************************


        public static void main(String args[]) {

            TreeNode root = new TreeNode(6);

            TreeNode left1 = new TreeNode(8);
            TreeNode right1 = new TreeNode(10);

            TreeNode left2 = new TreeNode(11);
            TreeNode right2 = new TreeNode(1);

            root.setLeft(left1);

            left1.right = right2;

            root.left = left1;
            root.right = right1;

            BinaryTree binaryTree = new BinaryTree();
            int res = binaryTree.maxDepth(root);
            System.out.println(res);

        }
    }
