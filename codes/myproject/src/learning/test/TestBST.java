package learning.test;

import learning.demo.algorithm.BST.BST;


public class TestBST {
	public static void main(String[] args) {
		BST<Integer, String> bst = new BST<Integer,String>();
		bst.put(1, "a");
		bst.put(2, "b");
		bst.put(5, "e");
		bst.put(3, "c");
		System.out.println(bst.toString());
		
		System.out.println(bst.floor(5));
		System.out.println(bst.rank(5));
	}

}
