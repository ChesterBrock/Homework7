package edu.miracosta.cs113;

import java.io.Serializable;
import java.util.Scanner;

public class BinaryTree<T> implements Serializable {
	
	protected  Node<T> root ;
	
	// default constructor 
	 public BinaryTree() {
		 this.root = null; // setting the root to null 
	 }
	 
	// Copy constructor
	 protected BinaryTree(Node<T> root) {
	        this.root = root ;
	    }
	 
	 public BinaryTree(T data, BinaryTree<T> leftTree, BinaryTree<T> rightTree) {
	        root = new Node<T>(data) ;

	        if (leftTree != null) {
	            root.left = leftTree.root ;
	        }
	        else {
	            root.left = null ;
	        }
	        if (rightTree != null) {
	            root.right = rightTree.root ;
	        }
	        else {
	            root.right = null ;
	        }
	    }

	
	protected static class Node<E> implements Serializable {

        protected E data ;
        protected Node<E> left ;
        protected Node<E> right ;
        
        // node constructor
        public Node(E data) {
            this.data = data ;
            this.left = null ;
            this.right = null ;
        }
        
        //toString method 
        public  String toString() {
            return data.toString() ;
        }
        
        public Node<E> getLeft() {
            return  left ;
        }
        
        public Node<E> getRight() {
            return right ;
        }
        
        public E getData() {
            return data ;
        }
        
        public void setRight(Node<E> node) {
            this.right = node ;
        }
        
        public void setData(E data) {
            this.data = data ;
        }
        
        public void setLeft(Node<E> node) {
            this.left = node ;
        }

	}// end of inner class
	
	
}
