package sound;

/**
 * Sound effect for a spiky weapon shot.
 * @author alexandergeenen
 */
public class SpikyFireSoundEffect extends SoundEffect {

    private static final String SOUND_FILE = "resources/sound/effects/spiky_fire_effect.ogg";

    /**
     * @param testing Whether or not testing
     */
    public SpikyFireSoundEffect(boolean testing) {
        super(SOUND_FILE, testing);
    }

}
