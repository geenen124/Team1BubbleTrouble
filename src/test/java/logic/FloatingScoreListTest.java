package logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import iterator.FloatingScoreListIterator;
import iterator.Iterator;

public class FloatingScoreListTest {
	
	FloatingScoreList fl;
	FloatingScore f1;
	FloatingScore f2;
	FloatingScore f3;

	@Before
	public void setUp() throws Exception {
		f1 = new FloatingScore("a", 1, 2, 3, 4);
		f2 = new FloatingScore("a", 1, 2, 3, 4);
		f3 = new FloatingScore("b", 5, 6, 7, 8);
	}

	@Test
	public void testFloatingScoreList() {
		fl = new FloatingScoreList();
		assertNotNull(fl.getFloatingScores());
	}
	
	@Test
	public void testFloatingScoreListArrayList() {
		ArrayList<FloatingScore> list = new ArrayList<FloatingScore>();
		list.add(f1);
		fl = new FloatingScoreList(list);
		assertEquals(list, fl.getFloatingScores());
	}
	
	@Test
	public void testCreateIterator() {
		fl = new FloatingScoreList();
		Iterator iterator = fl.createIterator();
		if (!(iterator instanceof FloatingScoreListIterator)) {
			fail("Wrong iterator type");
		}
	}
	
	@Test
	public void testAdd() {
		fl = new FloatingScoreList();
		fl.add(f1);
		assertEquals(f1, fl.get(0));
	}
	
	@Test
	public void testSize() {
		fl = new FloatingScoreList();
		fl.add(f1);
		fl.add(f3);
		assertEquals(2, fl.size());
	}
	
	@Test
	public void testEmptyTrue() {
		fl = new FloatingScoreList();
		assertTrue(fl.empty());
	}
	
	@Test
	public void testEmptyFalse() {
		fl = new FloatingScoreList();
		fl.add(f1);
		assertFalse(fl.empty());
	}
	
	@Test
	public void testGet() {
		fl = new FloatingScoreList();
		fl.add(f3);
		fl.add(f1);
		assertEquals(f3, fl.get(0));
	}
	
	
	@Test
	public void testContainsTrue() {
		fl = new FloatingScoreList();
		fl.add(f3);
		fl.add(f1);
		assertTrue(fl.contains(f3));
	}
	
	@Test
	public void testContainsFalse() {
		fl = new FloatingScoreList();
		fl.add(f3);
		assertFalse(fl.contains(f1));
	}
	
	@Test
	public void testRemove() {
		fl = new FloatingScoreList();
		fl.add(f3);
		fl.add(f1);
		fl.remove(f3);
		assertEquals(f1, fl.get(0));
	}
	
	@Test
	public void testGetFloatingScores() {
		ArrayList<FloatingScore> expected = new ArrayList<FloatingScore>();
		fl = new FloatingScoreList();
		fl.add(f3);
		expected.add(f3);
		assertEquals(expected, fl.getFloatingScores());
	}
	
	@Test
	public void testSetFloatingScores() {
		ArrayList<FloatingScore> list = new ArrayList<FloatingScore>();
		fl = new FloatingScoreList();
		list.add(f3);
		fl.setFloatingScores(list);
		assertEquals(list, fl.getFloatingScores());
	}

}
