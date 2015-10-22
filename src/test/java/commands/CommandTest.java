package commands;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import logic.BouncingCircle;
import logic.CircleList;
import logic.Coin;
import logic.CoinList;
import logic.FloatingScore;
import logic.FloatingScoreList;
import powerups.Powerup;
import powerups.PowerupList;

public class CommandTest {

	CoinList coinList;
	
	@Before
	public void setup() {
		coinList = new CoinList();
		coinList.add(new Coin(0, 0, true));
	}
	
	@Test
	public void testAddDroppedCoinCommand() {
		AddDroppedCoinCommand c = new AddDroppedCoinCommand(coinList, 
				new Coin(0,0,false));
		c.execute();
		assertTrue(coinList.size() == 2);
	}
	
	@Test
	public void testRemoveDroppedCoinCommand() {
		RemoveDroppedCoinCommand c = new RemoveDroppedCoinCommand(coinList, 
				new Coin(0,0,false));
		c.execute();
		assertTrue(coinList.size() == 1);
	}
	
	@Test
	public void testRemoveCircleCommand() {
		BouncingCircle circle = new BouncingCircle(0, 0, 10,
				0, 0, 1, 0);
		ArrayList<BouncingCircle> circleAr = new ArrayList<BouncingCircle>();
		circleAr.add(circle);
		CircleList circ = new CircleList(circleAr);
		RemoveCircleCommand c = new RemoveCircleCommand(circ,circle);
		c.execute();
		assertTrue(circ.getCircles().isEmpty());
	}
	
	@Test
	public void testAddDroppedPowerup() {
		PowerupList powerlist = new PowerupList();
		Powerup p = new Powerup(0, 0, Powerup.PowerupType.SHIELD);
		AddDroppedPowerupCommand a = new AddDroppedPowerupCommand(powerlist, p);
		a.execute();
		assertTrue(!powerlist.isEmpty());
	}
	
	@Test
	public void testAddFloatingScoreCommand() {
		FloatingScore sc = new FloatingScore("dada", 0, 0);
		FloatingScoreList list = new FloatingScoreList();
		AddFloatingScoreCommand c = new AddFloatingScoreCommand(list, sc);
		c.execute();
		assertTrue(!list.empty());
	}
	
	@Test
	public void testRemoveDroppedPowerupCommand() {
		PowerupList powerlist = new PowerupList();
		Powerup p = new Powerup(0, 0, Powerup.PowerupType.SHIELD);
		powerlist.add(p);
		RemoveDroppedPowerupCommand a = new RemoveDroppedPowerupCommand(powerlist, p);
		a.execute();
		assertTrue(powerlist.isEmpty());
	}
	
	@Test
	public void testRemoveFloatingScoreCommand() {
		FloatingScore sc = new FloatingScore("dada", 0, 0);
		FloatingScoreList list = new FloatingScoreList();
		list.add(sc);
		RemoveFloatingScoreCommand c = new RemoveFloatingScoreCommand(list, sc);
		c.execute();
		assertTrue(list.empty());
	}
	
	@Test
	public void testSetCircleListCommand() {
		BouncingCircle circle = new BouncingCircle(0, 0, 10,
				0, 0, 1, 0);
		ArrayList<BouncingCircle> circleAr = new ArrayList<BouncingCircle>();
		ArrayList<BouncingCircle> circleAr2 = new ArrayList<BouncingCircle>();
		circleAr.add(circle);
		CircleList circ = new CircleList(circleAr2);
		SetCirclelistCommand c = new SetCirclelistCommand(circ, circleAr);
		c.execute();
		assertTrue(!circ.getCircles().isEmpty());
	}
	
}
