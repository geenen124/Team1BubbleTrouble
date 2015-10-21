package powerups;

import logic.Weapon;
import sound.LaserFireSoundEffect;
import sound.SoundEffect;

/**
 * Created by alexandergeenen on 09/09/15.
 */
public class InstantLaser extends Weapon {

    private static final float INSTANT_SPEED = 4000f;

    /**
     * Create instant laser.
     * @param x the x coord
     * @param y the y coord
     * @param laserWidth the laser width
     */
    public InstantLaser(float x, float y, float laserWidth) {
        super(x, y, INSTANT_SPEED, laserWidth);
    }

    @Override
    public SoundEffect getWeaponSoundEffect() {
        return new LaserFireSoundEffect(false);
    }
}
