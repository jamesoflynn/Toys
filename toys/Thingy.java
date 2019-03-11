
public class Thingy<T extends Comparable<T>> {

	private T theThing;
	protected Thingy<T> left;
	protected Thingy<T> right;
	private int size;

	public Thingy() {
		this.size = 0;
	}

	public Thingy(T t) {
		this();
		this.theThing = t;
	}

	public void setThingy(T t) {
		this.theThing = t;
	}
	
	public T getThingy() {
		return theThing;
	}

	public int compareTo(Thingy<T> other) {
		return this.theThing.compareTo(other.theThing);
	}

	public String toString() {
		StringBuilder stringbuilder = new StringBuilder();
		stringbuilder.append("(")
			.append((left != null ? "L<-" : "N<-"))
			.append(theThing.toString()).append("[").append(size).append("]")
			.append((right != null ? "->R" : "->N"))
			.append(")");
		return stringbuilder.toString();
	}

	protected int resize() {
		int leftSize = (this.left != null ? this.left.size : 0);
		int rightSize = (this.right != null ? this.right.size : 0);
		this.size = leftSize + rightSize + 1;
		return this.size;
	}

	protected int size() {
		return this.size;
	}

	protected int weight() {
		resize();
		return this.size + 1;
	}
}
