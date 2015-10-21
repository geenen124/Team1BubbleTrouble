package iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import logic.CircleList;
import logic.Gate;
import logic.GateList;

public class GateListIteratorTest {
	
	GateListIterator iterator;
	GateList list;
	Gate b1;
	Gate b2;
	Gate b3;

	@Before
	public void setUp() throws Exception {
		b1 = new Gate(1, 2, 3, 4);
		b2 = new Gate(2, 2, 3, 4);
		b3 = new Gate(3, 2, 3, 4);
		ArrayList<Gate> arraylist = new ArrayList<Gate>();
		arraylist.add(b1);
		arraylist.add(b2);
		arraylist.add(b3);
		list = new GateList(arraylist);
		iterator = (GateListIterator) list.createIterator();
		
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
		Gate result = (Gate) iterator.next();
		assertEquals(b2, result);
	}

	@Test
	public void testRemove() {
		Gate result = (Gate) iterator.next();
		System.out.println(result);
		iterator.remove();
		assertEquals(2, list.getGates().size());
	}

	@Test
	public void testGetPosition() {
		assertEquals(0, iterator.getPosition());
	}

}
