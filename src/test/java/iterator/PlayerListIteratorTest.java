package iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Image;

import guigame.GameState;
import guimenu.MainGame;
import player.Player;
import player.PlayerList;

public class PlayerListIteratorTest {
	
	PlayerListIterator iterator;
	PlayerList list;
	Player p1;
	Player p2;
	Player p3;

	@Before
	public void setUp() throws Exception {
		MainGame mg = new MainGame("Test");
		Image i1 = mock(Image.class);
		Image i2 = mock(Image.class);
		Image i3 = mock(Image.class);
		Image i4 = mock(Image.class);
		p1 = new Player(1, 2, 3, 4, i1, i2, i3, i4, mg);
		p2 = new Player(2, 3, 4, 5, i1, i2, i3, i4, mg);
		list = new PlayerList(p1, mg, new GameState(mg));
		list.add(p2);
		iterator = (PlayerListIterator) list.createIterator();
	}


	@Test
	public void testHasNextTrue() {
		assertTrue(iterator.hasNext());
	}
	
	@Test
	public void testHasNextFalse() {
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
		assertEquals(1, list.getPlayers().size());
	}

}
