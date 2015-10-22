package sound;

/**
 * Sound effect when switching items/buttons in a menu.
 * @author alexandergeenen
 *
 */
public class MenuSelectionChangeSoundEffect extends SoundEffect {

    private static final String FILE_PATH =
            "resources/sound/effects/menu_selection_change_effect.ogg";

    /**
     *
     * @param testing if we are testing or not.
     */
    public MenuSelectionChangeSoundEffect(boolean testing) {
        super(FILE_PATH, testing);
    }


    @Override
    public void playSound() {
        this.getSound().play();

    }

}
