package sound;

/**
 * Sound effect for a weapon pickup.
 * @author alexandergeenen
 */
public class WeaponPickupSoundEffect extends SoundEffect {

    private static final String SOUND_FILE = "resources/sound/effects/powerup_weapon_effect.ogg";
    /**
     * @param testing Whether or not testing
     */
    public WeaponPickupSoundEffect(boolean testing) {
        super(SOUND_FILE, testing);
    }

    @Override
    public void playSound() {
        this.getSound().play();
    }
}
