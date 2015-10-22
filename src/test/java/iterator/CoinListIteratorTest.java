package iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import logic.CircleList;
import logic.Coin;
import logic.CoinList;

public class CoinListIteratorTest {
	
	CoinListIterator iterator;
	CoinList list;
	Coin b1;
	Coin b2;
	Coin b3;

	@Before
	public void setUp() throws Exception {
		b1 = new Coin(1, 2, true);
		b2 = new Coin(2, 2, true);
		b3 = new Coin(3, 2, true);
		ArrayList<Coin> arraylist = new ArrayList<Coin>();
		arraylist.add(b1);
		arraylist.add(b2);
		arraylist.add(b3);
		list = new CoinList(arraylist);
		iterator = (CoinListIterator) list.createIterator();
		
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
		Coin result = (Coin) iterator.next();
		assertEquals(b2, result);
	}

	@Test
	public void testRemove() {
		Coin result = (Coin) iterator.next();
		System.out.println(result);
		iterator.remove();
		assertEquals(2, list.getCoins().size());
	}

}
