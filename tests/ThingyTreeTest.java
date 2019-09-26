import junit.framework.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

	@Test
	void testTreeFlattening() {
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
		List<Thingy<Integer>> list = tree.flatten();
		Assert.assertEquals(9, list.size());
		Assert.assertEquals(6, list.get(0).getThingy().intValue());
		Assert.assertEquals(4, list.get(1).getThingy().intValue());
		Assert.assertEquals(2, list.get(2).getThingy().intValue());
		Assert.assertEquals(1, list.get(3).getThingy().intValue());
		Assert.assertEquals(3, list.get(4).getThingy().intValue());
		Assert.assertEquals(5, list.get(5).getThingy().intValue());
		Assert.assertEquals(8, list.get(6).getThingy().intValue());
		Assert.assertEquals(7, list.get(7).getThingy().intValue());
		Assert.assertEquals(9, list.get(8).getThingy().intValue());
	}

	@Test
	void testTreeSerialization() {
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

	}


	@Test
	void testIterator() {
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
		// LHR Iterator
		Iterator<Integer> itt = tree.iterator();
		List<Integer> list = new ArrayList<>();
		while(itt.hasNext()) {
			list.add(itt.next());
		}
		Assert.assertEquals(9, list.size());
		Assert.assertEquals(1, list.get(0).intValue());
		Assert.assertEquals(3, list.get(1).intValue());
		Assert.assertEquals(2, list.get(2).intValue());
		Assert.assertEquals(5, list.get(3).intValue());
		Assert.assertEquals(7, list.get(4).intValue());
		Assert.assertEquals(9, list.get(5).intValue());
		Assert.assertEquals(8, list.get(6).intValue());
		Assert.assertEquals(6, list.get(7).intValue());
		Assert.assertEquals(4, list.get(8).intValue());
	}
}
