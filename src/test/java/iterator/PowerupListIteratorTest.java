package iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import powerups.Powerup;
import powerups.Powerup.PowerupType;
import powerups.PowerupList;

public class PowerupListIteratorTest {
	PowerupListIterator iterator;
	PowerupList list;
	Powerup p1;
	Powerup p2;
	Powerup p3;

	@Before
	public void setUp() throws Exception {
		list = new PowerupList();
		p1 = new Powerup(1, 2, PowerupType.SHIELD);
		p2 = new Powerup(3, 4, PowerupType.SHIELD);
		p3 = new Powerup(5, 6, PowerupType.SHIELD);
		list.add(p1);
		list.add(p2);
		list.add(p3);
		iterator = (PowerupListIterator) list.createIterator();
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
		assertEquals(p2, iterator.next());
	}

	@Test
	public void testRemove() {
		iterator.next();
		iterator.remove();
		assertEquals(2, list.size());
	}

}
