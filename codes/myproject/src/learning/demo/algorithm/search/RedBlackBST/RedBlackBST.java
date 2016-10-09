package learning.demo.algorithm.search.RedBlackBST;

@SuppressWarnings("all")
public class RedBlackBST<Key extends Comparable<Key>, Value> {
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	private Node root;

	private class Node {
		Key key;
		Value val;
		Node left, right;
		int N;
		boolean color;

		public Node(Key key, Value val, int n, boolean color) {
			super();
			this.key = key;
			this.val = val;
			N = n;
			this.color = color;
		}
	}

	public int size() {
		return size(root);
	}

	private int size(Node x) {
		if (x == null)
			return 0;
		else
			return x.N;
	}

	private boolean isRed(Node x) {
		if (x == null)
			return false;
		return x.color == RED;
	}

	Node rotateLeft(Node h) {
		Node x = h.right;
		x.left = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = 1 + size(h.left) + size(h.right);
		return x;
	}

	Node rotateRight(Node h) {
		Node x = h.left;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = 1 + size(h.left) + size(h.right);
		return x;

	}

}
