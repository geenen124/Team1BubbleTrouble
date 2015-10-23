package sound;

/**
 * Sound effect for a (basic) weapon shot.
 * @author alexandergeenen
 */
public class WeaponFireSoundEffect extends SoundEffect {

    private static final String SOUND_FILE = "resources/sound/effects/laser_fire_effect.ogg";

    /**
     * @param testing Whether or not testing
     */
    public WeaponFireSoundEffect(boolean testing) {
        super(SOUND_FILE, testing);
    }

}
