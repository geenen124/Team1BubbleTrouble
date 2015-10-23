package sound;

/**
 * Sound effect for a coin pickup.
 * @author alexandergeenen
 */
public class CoinPickupSoundEffect extends SoundEffect {

    private static final String SOUND_FILE = "resources/sound/effects/coin_pickup_effect.ogg";
    /**
     * @param testing Whether or not testing
     */
    public CoinPickupSoundEffect(boolean testing) {
        super(SOUND_FILE, testing);
    }
}
