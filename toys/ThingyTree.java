import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ThingyTree<T extends Comparable<T>> implements Externalizable {

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

	public Iterator<Thingy<T>> iterator() {
		return null;
	}

	///////////////////////////////////////////////////////////////////////////


	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		Visitor<Thingy<T>> wrapper = new Visitor<Thingy<T>>() {
			@Override
			public void visit(Thingy<T> thingy) {
				try {
					objectOutput.writeObject(thingy);
				}
				catch(IOException ioe){}
			}
		};
		traverse(head, wrapper);
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {

	}

	///////////////////////////////////////////////////////////////////////////

	public List<Thingy<T>> flatten() {
		List<Thingy<T>> list = new ArrayList<>();
		Visitor<Thingy<T>> wrapper = new Visitor<>() {
			public void visit(Thingy<T> thingy) {
				list.add(thingy);
			}
		};
		traverse(head, wrapper);
		return list;
	}

	private Visitor<Thingy<T>> traverse(Thingy<T> cursor, Visitor<Thingy<T>> visitor) {
		visitor.visit(cursor);
		if (cursor.left != null) {
			traverse(cursor.left, visitor);
		}
		if (cursor.right != null) {
			traverse(cursor.right, visitor);
		}
		return visitor;
	}

	public List<Thingy<T>> lhrFlatten() {
		List<Thingy<T>> list = new ArrayList<>();
		Visitor<Thingy<T>> wrapper = new Visitor<>() {
			public void visit(Thingy<T> thingy) {
				list.add(thingy);
			}
		};
		lhrTraverse(head, wrapper);
		return list;
	}

	private Visitor<Thingy<T>> lhrTraverse(Thingy<T> cursor, Visitor<Thingy<T>> visitor) {
		if (cursor.left != null) {
			lhrTraverse(cursor.left, visitor);
		}
		if (cursor.right != null) {
			lhrTraverse(cursor.right, visitor);
		}
		visitor.visit(cursor);
		return visitor;
	}

	public List<Thingy<T>> dfFlatten() {
		List<Thingy<T>> list = new ArrayList<>();
		Visitor<Thingy<T>> wrapper = new Visitor<>() {
			public void visit(Thingy<T> thingy) {
				list.add(thingy);
			}
		};
		dfTraverse(head, wrapper);
		return list;
	}

	private Visitor<Thingy<T>> dfTraverse(Thingy<T> cursor, Visitor<Thingy<T>> visitor) {
		visitor.visit(cursor);
		while (cursor.left != null) {
			cursor = cursor.left;
			visitor.visit(cursor);
		}
		if (cursor.right != null) {
			dfTraverse(cursor.right, visitor);
		}
		return visitor;
	}

	public void dump(PrintStream output) {
		Visitor<Thingy<T>> wrapper = new Visitor<>() {
			public void visit(Thingy<T> thingy) {
				output.print(thingy.toString());
				output.print(", ");
			}
		};
		traverse(head, wrapper);
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
