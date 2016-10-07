package learning.demo.algorithm.search.RedBlackBST;

@SuppressWarnings("all")
public class RedBlackBST<Key extends Comparable<Key>, Value> {
	private static final boolean RED = true;
	private static final boolean BLACK = false;

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

	private boolean isRed(Node x) {
		if (x == null)
			return false;
		return x.color == RED;
	}
}
