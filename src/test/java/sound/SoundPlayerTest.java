package sound;

import static org.junit.Assert.*;

import org.junit.Test;

public class SoundPlayerTest {

	@Test
	public void testPlayEffects() {
		SoundPlayer sp = SoundPlayer.getInstance(true);
		sp.playEffects();
		assertNotNull(sp);		
	}

	@Test
	public void testAddEffect() {
		
	}

	@Test
	public void testSetActiveList() {
		
	}

	@Test
	public void testGetInstance() {
		
	}

	@Test
	public void testGetInstanceBoolean() {
		
	}

	@Test
	public void testPlay() {
		
	}

	@Test
	public void testPause() {
		
	}

	@Test
	public void testMusicEnded() {
		
	}

	@Test
	public void testMusicSwapped() {
		
	}

	@Test
	public void testLogShit() {
		
	}

}
