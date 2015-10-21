package iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import guigame.GameState;
import guimenu.MainGame;
import logic.Weapon;
import logic.WeaponList;

public class WeaponListIteratorTest {
	WeaponListIterator iterator;
	WeaponList list;
	Weapon w1;
	Weapon w2;
	Weapon w3;

	@Before
	public void setUp() throws Exception {
		w1 = new Weapon(1, 2, 3, 4);
		list = new WeaponList(w1, new MainGame("Test", true), new GameState(new MainGame("Test", true)), true);
		iterator = (WeaponListIterator) list.createIterator();
	}
	
	@Test
	public void testHasNextTrue() {
		assertTrue(iterator.hasNext());
	}
	
	@Test
	public void testHasNextFalse() {
		iterator.next();
		assertFalse(iterator.hasNext());
	}

	@Test
	public void testNext() {
		assertEquals(w1, iterator.next());
	}

	@Test
	public void testRemove() {
		iterator.next();
		iterator.remove();
		assertEquals(0, list.getWeaponList().size());
	}

}
