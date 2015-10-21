package gui;

import static org.junit.Assert.*;
import guiobjects.ElementList;
import guiobjects.PlayerButton;

import org.junit.Before;
import org.junit.Test;

public class PlayerButtonTest {

	PlayerButton a;
	PlayerButton b;
	PlayerButton c;
	
	@Before
	public void setup() {
		a = new PlayerButton(0, 0, PlayerButton.PlayerType.GAMEBOY, 1, true);
		b = new PlayerButton(0, 0, PlayerButton.PlayerType.GAMEBOY, 1, true);
		b = new PlayerButton(1, 1, PlayerButton.PlayerType.PHONE, 1, true);
	}
	
	@Test
	public void testGetType() {
		assertTrue(a.getType() == PlayerButton.PlayerType.GAMEBOY);
	}
	
	@Test
	public void testSetType() {
		a.setType(PlayerButton.PlayerType.PHONE);
		assertTrue(a.getType() == PlayerButton.PlayerType.PHONE);
		a.setType(PlayerButton.PlayerType.ARIE);
		assertTrue(a.getType() == PlayerButton.PlayerType.ARIE);
	}
	
	@Test
	public void testGettersSetters() {
		a.setActive(true);
		assertTrue(a.isActive());
		assertTrue(a.getImageN() == null && a.getImageA() == null);
		assertTrue(a.getSpriteN() == "Playersprite_Norm.png"
				&& a.getSpriteA() == "Playersprite_Add.png");
		assertEquals(a.getPlayer(), 1);
	}
	
	@Test
	public void testMakeActive() {
		ElementList list = new ElementList();
		b.setActive(true);
		list.add(a);
		list.add(b);
		a.makeActive();
		assertTrue(a.isActive() && !b.isActive());
	}
	
}
