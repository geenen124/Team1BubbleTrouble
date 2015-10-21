package iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import guimenu.MainGame;
import levels.Level;
import levels.LevelFactory;
import levels.LevelFactorySinglePlayer;
import logic.LevelContainer;

public class LevelContainerIteratorTest {
	
	LevelContainerIterator iterator;
	LevelContainer list;
	Level l1;
	Level l2;
	Level l3;

	@Before
	public void setUp() throws Exception {
		LevelFactory lf = new LevelFactorySinglePlayer();
		list = new LevelContainer(new MainGame("Test", true));
		l1 = lf.orderLevel(1, new MainGame("Test", true));
		l2 = lf.orderLevel(2, new MainGame("Test", true));
		l3 = lf.orderLevel(3, new MainGame("Test", true));
		list.add(l1);
		list.add(l2);
		list.add(l3);
		iterator = (LevelContainerIterator) list.createIterator();
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
		assertEquals(l2, iterator.next());
	}

	@Test
	public void testRemove() {
		iterator.next();
		iterator.remove();
		assertEquals(2, list.size());
	}

}
