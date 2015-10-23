package sound;

/**
 * Sound effect for a general powerup pickup.
 * @author alexandergeenen
 */
public class GeneralPickupSoundEffect extends SoundEffect {

    private static final String SOUND_FILE = "resources/sound/effects/powerup_other_effect.ogg";
    /**
     * @param testing Whether or not testing
     */
    public GeneralPickupSoundEffect(boolean testing) {
        super(SOUND_FILE, testing);
    }
}
