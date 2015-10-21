package gui;
import static org.junit.Assert.*;
import guimenu.MainGame;
import guimenu.MenuMainState;

import org.junit.Before;
import org.junit.Test;

public class StartStateTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetID() {
		MainGame mg = new MainGame("hello", true);
		MenuMainState s = new MenuMainState(mg);
		assertEquals(0, s.getID());
	}

	@Test
	public void testStartState() {
		MainGame mg = new MainGame("hello", true);
		MenuMainState s = new MenuMainState(mg);
		assertEquals(s.getMainGame(), mg);
	}

}
