package sound;

/**
 * Sound effect when confirming/entering the selected item/choice in a menu.
 * @author alexandergeenen
 *
 */
public class MenuItemConfirmationSoundEffect extends SoundEffect {

    private static final String FILE_PATH =
            "resources/sound/effects/menu_item_confirmation_effect.ogg";

    /**
     *
     * @param testing if we are testing or not.
     */
    public MenuItemConfirmationSoundEffect(boolean testing) {
        super(FILE_PATH, testing);
    }


    @Override
    public void playSound() {
        this.getSound().play();

    }

}
