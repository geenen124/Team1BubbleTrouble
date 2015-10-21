package gui;

import guiobjects.PlayerButton;

import org.junit.Before;

public class PlayerButtonTest {

	PlayerButton a;
	PlayerButton b;
	PlayerButton c;
	
	@Before
	public void setup() {
		a = new PlayerButton(0, 0, PlayerButton.PlayerType.GAMEBOY, 1);
		b = new PlayerButton(0, 0, PlayerButton.PlayerType.GAMEBOY, 1);
		b = new PlayerButton(1, 1, PlayerButton.PlayerType.PHONE, 2);
	}
	
}
