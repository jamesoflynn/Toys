import java.io.PrintStream;

public class ThingyTree<T extends Comparable<T>> {

	private Thingy<T> head;
	private float alpha = 0.2f;
	public int length;

	public ThingyTree() {}

	public ThingyTree(float alpha) {
		if (0.182f <= alpha && alpha <= 0.292f) {
			this.alpha = alpha;
		}
	}

	public String toString() {
		return new StringBuilder().append(alpha)
				.append(":").append(head != null ? head.toString() : "")
				.toString();
	}

	public int length() {
		return length;
	}

	///////////////////////////////////////////////////////////////////////////
	
	public void add(Thingy<T> aThingy) {
		if (head == null) {
			head = aThingy;
		} else {
			head = descendInsert(head, aThingy);
		}
	}
	
	private Thingy<T> descendInsert(Thingy<T> cursor, Thingy<T> aThingy) {
		if (cursor.compareTo(aThingy) == 0) {
			return cursor;
		} else if (aThingy.compareTo(cursor) < 0) {
			if (cursor.left == null) {
				cursor.left = aThingy;
				length++;
			} else {
				cursor.left = descendInsert(cursor.left, aThingy);
			}
		} else if (aThingy.compareTo(cursor) > 0) {
			if (cursor.right == null) {
				cursor.right = aThingy;
				length++;
			} else {
				cursor.right = descendInsert(cursor.right, aThingy);
			}
		}
		cursor.resize();
		if (!isBalanced(cursor)) {
			cursor = rebalance(cursor);
		}
		return cursor;
	}

	///////////////////////////////////////////////////////////////////////////
	
	public void remove(Thingy<T> aThingy) {
		if (head == null) {
			return;
		}
		head = descendRemove(head, aThingy);
	}

	private Thingy<T> descendRemove(Thingy<T> cursor, Thingy<T> aThingy) {
		if (aThingy.compareTo(cursor) < 0) {
			if (cursor.left != null) {
				cursor.left = descendRemove(cursor.left, aThingy);
				cursor.resize();
			}
		} else if (aThingy.compareTo(cursor) > 0) {
			if (cursor.right != null) {
				cursor.right = descendRemove(cursor.right, aThingy);
				cursor.resize();
			}
		}
		else if (aThingy.compareTo(cursor) == 0) {
			if (cursor.left != null) {
				if (cursor.right != null) {
					cursor.right = descendInsert(cursor.right, cursor.left);
					cursor.left = null;
					cursor = cursor.right;
				} else {
					cursor = cursor.left;
				}
			} else if (cursor.right != null) {
				cursor = cursor.right;
			} else { // if (cursor.left == null && cursor.right == null) {
				cursor = null;
			}
			length--;
		}
		return cursor;
	}

	///////////////////////////////////////////////////////////////////////////

	private boolean isBalanced(Thingy<T> cursor) {
		if (cursor.left == null && cursor.right == null) {
			return true;
		}
		if ((cursor.left != null && cursor.size() < 2 && cursor.right == null)
			|| (cursor.right != null && cursor.size() < 2 && cursor.left == null)) {
			return true;
		}
		if (cursor.left != null && cursor.right != null) {
			int diff = cursor.right.weight() - cursor.left.weight();
			if (Math.abs(diff) < alpha * cursor.right.weight()) {
				return true;
			}
		}
		/*
		if (cursor.left != null && cursor.left.weight() >= weight
				&& cursor.right != null && cursor.right.weight() >= weight) {
			return true;
		}
		*/
		return false;
	}

	private Thingy<T> rebalance(Thingy<T> cursor) {
		if (cursor.right == null || cursor.left != null && cursor.left.size() > cursor.right.size()) {
			// Rotate Right
			Thingy<T> node = cursor.left;
			cursor.left = node.right;
			cursor.resize();
			node.right = cursor;
			cursor = node;
			cursor.resize();
		} else if (cursor.left == null || cursor.right != null && cursor.right.size() > cursor.left.size()) {
			// Rotate Left
			Thingy<T> node = cursor.right;
			cursor.right = node.left;
			cursor.resize();
			node.left = cursor;
			cursor = node;
			cursor.resize();
		}
		return cursor;
	}

	///////////////////////////////////////////////////////////////////////////

	public void dump(PrintStream output) {
		dump(head, output);
	}

	private void dump(Thingy<T> cursor, PrintStream output) {
		if (cursor.left != null) {
			dump(cursor.left, output);
		}
		if (cursor.right != null) {
			dump(cursor.right, output);
		}
		output.print(cursor.toString());
		output.print(", ");
	}
}
