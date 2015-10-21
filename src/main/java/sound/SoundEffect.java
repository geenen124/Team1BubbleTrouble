package sound;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

/**
 * Abstract superclass for soundeffects.
 * @author Bart
 *
 */
public abstract class SoundEffect {
	
	private Sound sound;
	
	/**
	 * Constructor which initializes the sound.
	 * @param filePath the path to the sound file.
	 * @param testing if we are testing or not.
	 */
	public SoundEffect(String filePath, boolean testing) {
		if (testing) {
			return;
		}
		
		try {
			this.sound = new Sound(filePath);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Play the sound effect.
	 */
	public abstract void playSound();

	/**
	 * @return the sound
	 */
	public Sound getSound() {
		return sound;
	}

	/**
	 * @param sound the sound to set
	 */
	public void setSound(Sound sound) {
		this.sound = sound;
	}
	
	

}
