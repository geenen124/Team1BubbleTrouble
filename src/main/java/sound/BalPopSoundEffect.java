package sound;

/**
 * Sound effect for a coin pickup.
 * @author alexandergeenen
 */
public class BalPopSoundEffect extends SoundEffect {

    private static final String SOUND_FILE = "resources/sound/effects/ball_pop_effect.ogg";
    /**
     * @param testing Whether or not testing
     */
    public BalPopSoundEffect(boolean testing) {
        super(SOUND_FILE, testing);
    }

    @Override
    public void playSound() {
        this.getSound().play();
    }
}
