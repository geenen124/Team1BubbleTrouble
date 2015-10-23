package sound;

/**
 * Sound effect when player dies.
 * @author Bart
 *
 */
public class PlayerDeathSoundEffect extends SoundEffect {

	private static final String FILE_PATH = "resources/sound/effects/player_death_effect.ogg";
	
	/**
	 * 
	 * @param testing if we are testing or not.
	 */
	public PlayerDeathSoundEffect(boolean testing) {
		super(FILE_PATH, testing);
	}

}
