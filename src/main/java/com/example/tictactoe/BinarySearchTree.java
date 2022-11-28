package com.example.tictactoe;

import java.util.*;
class Node{
    int data;
    Node left;
    Node right;
    public Node(int data){
        this.data = data;
        left = null;
        right = null;
    }
}
public class BinarySearchTree {
    public static Node root;
    public BinarySearchTree(){
        this.root = null;
    }
    public void insert(int id){
        Node newNode = new Node(id);
        if(root==null){
            root = newNode;
            return;
        }
        Node current = root;
        Node parent = null;
        while(true){
            parent = current;
            if(id<current.data){
                current = current.left;
                if(current==null){
                    parent.left = newNode;
                    return;
                }
            }else{
                current = current.right;
                if(current==null){
                    parent.right = newNode;
                    return;
                }
            }
        }
    }
    //Inorder traversal method using stack
    public void inOrder(Node root) {

        if(root == null)
            return;

        Stack<Node> s = new Stack<Node>(); //stack to store node
        Node currentNode=root;

        while(!s.empty() || currentNode!=null){ //loop continues until stack is empty or current node in not null

            if(currentNode!=null)
            {
                s.push(currentNode);
                currentNode=currentNode.left;
            }
            else
            {
                Node n=s.pop();
                System.out.printf("%d ",n.data);
                currentNode=n.right;
            }
        }
    }
    public static void main(String []args){
        BinarySearchTree b = new BinarySearchTree(); // b is the object of class BST
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter amount of times 'X' won, then the amount of times 'O' won.");
        for(int i=0;i<2;i++)//loop to read 10 integers
        { int num=sc.nextInt();
            b.insert(num); // call insert method to insert num to bst
        }
        System.out.println(" BST inorder traversal : ");
        b.inOrder(b.root);
    }
}