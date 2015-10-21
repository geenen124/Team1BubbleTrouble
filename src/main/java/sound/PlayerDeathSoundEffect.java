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
	 * @param filePath
	 */
	public PlayerDeathSoundEffect() {
		super(FILE_PATH);
	}

	@Override
	public void playSound() {
		this.getSound().play();
		
	}

}
