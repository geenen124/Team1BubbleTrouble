package sound;

/**
 * Sound effect when switching menus/states.
 * @author alexandergeenen
 *
 */
public class MenuTransitionSoundEffect extends SoundEffect {

    private static final String FILE_PATH = "resources/sound/effects/menu_transition_effect.ogg";

    /**
     *
     * @param testing if we are testing or not.
     */
    public MenuTransitionSoundEffect(boolean testing) {
        super(FILE_PATH, testing);
    }


    @Override
    public void playSound() {
        this.getSound().play();

    }

}
