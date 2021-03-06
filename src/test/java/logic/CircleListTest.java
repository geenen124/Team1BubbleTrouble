package logic;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import guigame.GameState;
import guigame.GameStateCirclesHelper;
import guigame.GameStateInterfaceHelper;
import guigame.GameStateItemsHelper;
import guigame.GameStateLogicHelper;
import guigame.GameStatePauseHelper;
import guigame.GameStatePlayerHelper;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class CircleListTest {

	GameState gs;
	
	GameStateCirclesHelper ch;
	GameStateItemsHelper ih;
	GameStateInterfaceHelper ifh;
	GameStatePlayerHelper ph;
	GameStateLogicHelper lh;
	GameStatePauseHelper pah;
	
	@Before
	public void setUp() throws Exception {
		gs = mock(GameState.class);
		ch = mock(GameStateCirclesHelper.class);
		ih = mock(GameStateItemsHelper.class);
		ifh = mock(GameStateInterfaceHelper.class);
		ph = mock(GameStatePlayerHelper.class);
		lh = mock(GameStateLogicHelper.class);
		pah = mock(GameStatePauseHelper.class);
		when(gs.getItemsHelper()).thenReturn(ih);
		when(gs.getCirclesHelper()).thenReturn(ch);
		when(gs.getInterfaceHelper()).thenReturn(ifh);
		when(gs.getPlayerHelper()).thenReturn(ph);
		when(gs.getLogicHelper()).thenReturn(lh);
		when(gs.getPauseHelper()).thenReturn(pah);
	}

	@Test
	public void testCircleList() {
		ArrayList<BouncingCircle> list = new ArrayList<BouncingCircle>();
		CircleList cl = new CircleList(list);
		
		assertEquals(1, cl.getNewID());
	}
	
	@Test
	public void testCircleList2() {
		ArrayList<BouncingCircle> list = new ArrayList<BouncingCircle>();
		CircleList cl = new CircleList(list);
		assertEquals(list, cl.getCircles());
		
	}
	
	@Test
	public void testCircleList3() {
		ArrayList<BouncingCircle> list = new ArrayList<BouncingCircle>();
		list.add(new BouncingCircle(1, 2, 10, 4, 5, 6, 1));
		CircleList cl = new CircleList(list);
		assertEquals(2, cl.getNewID());
	}

	@Test
	public void testAdd() {
		BouncingCircle circle = mock(BouncingCircle.class);
		when(circle.getId()).thenReturn(2);
		ArrayList<BouncingCircle> list = new ArrayList<BouncingCircle>();
		CircleList cl = new CircleList(list);
		cl.add(circle);
		assertEquals(3, cl.getNewID());
	}
	
	@Test
	public void testAdd2() {
		BouncingCircle circle = mock(BouncingCircle.class);
		when(circle.getId()).thenReturn(2);
		ArrayList<BouncingCircle> list = new ArrayList<BouncingCircle>();
		CircleList cl = new CircleList(list);
		cl.add(circle);
		assertEquals(1, cl.getCircles().size());
	}
	
	@Test
	public void testAdd3() {
		BouncingCircle circle = mock(BouncingCircle.class);
		when(circle.getId()).thenReturn(-1);
		ArrayList<BouncingCircle> list = new ArrayList<BouncingCircle>();
		CircleList cl = new CircleList(list);
		cl.add(circle);
		assertEquals(1, cl.getNewID());
	}
	
	
	


	@Test
	public void testSetCircles() {
		ArrayList<BouncingCircle> list = new ArrayList<BouncingCircle>();
		CircleList cl = new CircleList(list);
		ArrayList<BouncingCircle> list2 = new ArrayList<BouncingCircle>();
		cl.setCircles(list2);
		assertEquals(list2, cl.getCircles());
	}
	
	@Test
	public void testSetAllMultipliers() {
		ArrayList<BouncingCircle> list = new ArrayList<BouncingCircle>();
		BouncingCircle c = new BouncingCircle(1, 2, 10, 4, 5, 6, 1);
		list.add(c);
		CircleList cl = new CircleList(list);
		cl.setAllMultipliers(10);
		assertEquals(10, c.getMultiplier(), 0);
	}

	@Test
	public void testGetCircleForID1() {
		ArrayList<BouncingCircle> list = new ArrayList<BouncingCircle>();
		BouncingCircle c = new BouncingCircle(1, 2, 10, 4, 5, 6, 1);
		list.add(c);
		CircleList cl = new CircleList(list);
		
		assertEquals(c, cl.getCircleForID(1));
	}
	
	@Test
	public void testGetCircleForID2() {
		ArrayList<BouncingCircle> list = new ArrayList<BouncingCircle>();
		BouncingCircle c = new BouncingCircle(1, 2, 10, 4, 5, 6, 1);
		list.add(c);
		CircleList cl = new CircleList(list);
		
		assertNull(cl.getCircleForID(2));
	}

	@Test
	public void testGetIndexForCircleWithID() {
		ArrayList<BouncingCircle> list = new ArrayList<BouncingCircle>();
		BouncingCircle c = new BouncingCircle(1, 2, 10, 4, 5, 6, 1);
		list.add(c);
		CircleList cl = new CircleList(list);
		assertEquals(0, cl.getIndexForCircleWithID(1));
	}
}
