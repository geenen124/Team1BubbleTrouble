package logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import iterator.CoinListIterator;
import iterator.Iterator;

public class CoinListTest {
	
	CoinList cl;
	Coin c1;
	Coin c2;
	Coin c3;

	@Before
	public void setUp() throws Exception {
		c1 = new Coin(1, 2, true);
		c2 = new Coin(1, 2, true);
		c3 = new Coin(3, 4, false);
	}

	@Test
	public void testCoinList() {
		cl = new CoinList();
		assertNotNull(cl.getCoins());
	}

	@Test
	public void testCoinListArrayListOfCoin() {
		ArrayList<Coin> list = new ArrayList<Coin>();
		list.add(c1);
		list.add(c3);
		cl = new CoinList(list);
		assertEquals(list, cl.getCoins());
	}

	@Test
	public void testAdd() {
		cl = new CoinList();
		cl.add(c1);
		assertEquals(cl.get(0), c1);
	}

	@Test(expected=IndexOutOfBoundsException.class)
	public void testGetBelowZero() {
		cl = new CoinList();
		cl.get(-1);
		
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetTooHigh() {
		cl = new CoinList();
		cl.add(c1);
		cl.get(1);
	}
	
	@Test
	public void testGet() {
		cl = new CoinList();
		cl.add(c1);
		cl.add(c2);
		cl.add(c3);
		assertEquals(c3, cl.get(2));
	}

	@Test
	public void testSize() {
		cl = new CoinList();
		cl.add(c1);
		cl.add(c2);
		assertEquals(2, cl.size());
	}

	@Test
	public void testIsEmptyTrue() {
		cl = new CoinList();
		assertTrue(cl.isEmpty());
	}
	
	@Test
	public void testIsEmptyFalse() {
		cl = new CoinList();
		cl.add(c1);
		assertFalse(cl.isEmpty());
	}

	@Test
	public void testCreateIterator() {
		cl = new CoinList();
		Iterator iterator = cl.createIterator();
		if (!(iterator instanceof CoinListIterator)) {
			fail("Wrong iterator type");
		}
	}

	@Test
	public void testContainsTrue() {
		cl = new CoinList();
		cl.add(c1);
		cl.add(c3);
		assertTrue(cl.contains(c1));
	}
	
	@Test
	public void testContainsFalse() {
		cl = new CoinList();
		cl.add(c3);
		assertFalse(cl.contains(c2));
	}

	@Test
	public void testGetCoins() {
		cl = new CoinList();
		ArrayList<Coin> result = new ArrayList<Coin>();
		cl.add(c1);
		result.add(c1);
		assertEquals(result, cl.getCoins());
	}

	@Test
	public void testSetCoins() {
		cl = new CoinList();
		ArrayList<Coin> list = new ArrayList<Coin>();
		list.add(c1);
		cl.setCoins(list);
		assertEquals(list, cl.getCoins());
	}

}
