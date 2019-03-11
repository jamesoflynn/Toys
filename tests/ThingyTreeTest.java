import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ThingyTreeTest {


	@Test
	void test() {
		ThingyTree<Integer> tree = new ThingyTree<>();
		tree.add(new Thingy<Integer>(5));
		tree.add(new Thingy<Integer>(3));
		tree.add(new Thingy<Integer>(7));
		tree.add(new Thingy<Integer>(2));
		tree.add(new Thingy<Integer>(4));
		tree.add(new Thingy<Integer>(1));
		tree.dump(System.out);
		tree.remove(new Thingy<Integer>(3));
		System.out.println();
		tree.dump(System.out);
		System.out.println();
	}

	@Test
	void testListLikeInsertion() {
		ThingyTree<Integer> tree = new ThingyTree<>();
		tree.add(new Thingy<Integer>(1));
		tree.add(new Thingy<Integer>(2));
		tree.add(new Thingy<Integer>(3));
		tree.add(new Thingy<Integer>(4));
		tree.add(new Thingy<Integer>(5));
		tree.add(new Thingy<Integer>(6));
		tree.add(new Thingy<Integer>(7));
		tree.add(new Thingy<Integer>(8));
		tree.add(new Thingy<Integer>(9));
		tree.dump(System.out);
		System.out.println();
		
	}

	@Test
	void testAnotherListLikeInsertion() {
		ThingyTree<Integer> tree = new ThingyTree<>();
		tree.add(new Thingy<Integer>(9));
		tree.add(new Thingy<Integer>(8));
		tree.add(new Thingy<Integer>(7));
		tree.add(new Thingy<Integer>(6));
		tree.add(new Thingy<Integer>(5));
		tree.add(new Thingy<Integer>(4));
		tree.add(new Thingy<Integer>(3));
		tree.add(new Thingy<Integer>(2));
		tree.add(new Thingy<Integer>(1));
		tree.dump(System.out);
		System.out.println();
		
	}
}
