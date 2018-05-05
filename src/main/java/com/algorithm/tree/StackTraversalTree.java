package com.algorithm.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树的一些遍历方法
 */
public class StackTraversalTree {

    public static Stack<TreeNode> stack = new Stack<>();

    public static Queue<TreeNode> queue = new LinkedList<>();

    // 栈方式实现后续遍历
    public static void postOrderStackTraversal(TreeNode t) {

        if (t == null) {
            return;
        }

        stack.push(t);

        while (!stack.empty()) {

            TreeNode treeNode = stack.pop();

            if (treeNode.left != null) {
                stack.push(treeNode);
                stack.push(treeNode.left);
                treeNode.left = null;
            } else if (treeNode.right != null) {
                stack.push(treeNode);
                stack.push(treeNode.right);
                treeNode.right = null;
            } else {
                System.out.println(treeNode.num);
            }

        }

    }

    // 栈方式实现前序遍历
    public static void preOrderStackTraversal(TreeNode t) {

        if (t == null) {
            return;
        }

        stack.push(t);

        while (!stack.empty()) {

            TreeNode treeNode = stack.pop();
            System.out.println(treeNode.num);

            if (treeNode.right != null) {
                stack.push(treeNode.right);
            }

            if (treeNode.left != null) {
                stack.push(treeNode.left);
            }

        }

    }

    // 队列方式实现层序遍历
    public static void levelQueueTraversal(TreeNode t) {

        if (t == null) {
            return;
        }

        queue.add(t);

        while (queue.size() != 0) {

            TreeNode treeNode = queue.poll();
            System.out.println(treeNode.num);

            if (treeNode.left != null) {
                queue.add(treeNode.left);
            }

            if (treeNode.right != null) {
                queue.add(treeNode.right);
            }
        }

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        levelQueueTraversal(root);
    }

    static class TreeNode {
        public TreeNode(int num) {
            this.num = num;
        }

        public Integer num;
        public TreeNode left;
        public TreeNode right;
    }
}
