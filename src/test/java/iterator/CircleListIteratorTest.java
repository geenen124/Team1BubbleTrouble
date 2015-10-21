package iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import logic.BouncingCircle;
import logic.CircleList;

public class CircleListIteratorTest {
	
	CircleListIterator iterator;
	CircleList list;
	BouncingCircle b1;
	BouncingCircle b2;
	BouncingCircle b3;

	@Before
	public void setUp() throws Exception {
		b1 = new BouncingCircle(1, 2, 3, 4, 5, 6, 7);
		b2 = new BouncingCircle(2, 2, 3, 4, 5, 6, 7);
		b3 = new BouncingCircle(3, 2, 3, 4, 5, 6, 7);
		ArrayList<BouncingCircle> arraylist = new ArrayList<BouncingCircle>();
		arraylist.add(b1);
		arraylist.add(b2);
		arraylist.add(b3);
		list = new CircleList(arraylist);
		iterator = (CircleListIterator) list.createIterator();
		
	}


	@Test
	public void testHasNextTrue() {
		assertTrue(iterator.hasNext());
	}
	
	@Test
	public void testHasNextFalse() {
		iterator.next();
		iterator.next();
		iterator.next();
		assertFalse(iterator.hasNext());
	}

	@Test
	public void testNext() {
		iterator.next();
		BouncingCircle result = (BouncingCircle) iterator.next();
		assertEquals(b2, result);
	}

	@Test
	public void testRemove() {
		BouncingCircle result = (BouncingCircle) iterator.next();
		System.out.println(result);
		iterator.remove();
		assertEquals(2, list.getCircles().size());
	}

}
