package sound;

/**
 * Sound effect for a laser weapon shot.
 * @author alexandergeenen
 */
public class LaserFireSoundEffect extends SoundEffect {

    private static final String SOUND_FILE = "resources/sound/effects/laser_fire_effect.ogg";

    /**
     * @param testing Whether or not testing
     */
    public LaserFireSoundEffect(boolean testing) {
        super(SOUND_FILE, testing);
    }

    @Override
    public void playSound() {
        this.getSound().play();
    }
}
