package iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import logic.FloatingScore;
import logic.FloatingScoreList;

public class FloatingScoreListIteratorTest {
	
	FloatingScoreListIterator iterator;
	FloatingScoreList list;
	FloatingScore b1;
	FloatingScore b2;
	FloatingScore b3;

	@Before
	public void setUp() throws Exception {
		b1 = new FloatingScore("a", 1, 2);
		b2 = new FloatingScore("b", 1, 2);
		b3 = new FloatingScore("c", 1, 2);
		ArrayList<FloatingScore> arraylist = new ArrayList<FloatingScore>();
		arraylist.add(b1);
		arraylist.add(b2);
		arraylist.add(b3);
		list = new FloatingScoreList(arraylist);
		iterator = (FloatingScoreListIterator) list.createIterator();
		
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
		FloatingScore result = (FloatingScore) iterator.next();
		assertEquals(b2, result);
	}

	@Test
	public void testRemove() {
		FloatingScore result = (FloatingScore) iterator.next();
		System.out.println(result);
		iterator.remove();
		assertEquals(2, list.getFloatingScores().size());
	}

}
