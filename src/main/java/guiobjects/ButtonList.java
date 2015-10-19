package guiobjects;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

/**
 * A class responsible for storing buttons in a menu, as well as handling keyboard/mouse inputs.
 * @author Mark
 *
 */
public class ButtonList {

	private ArrayList<Button> buttonList;
	private int index = 0;
	private boolean mouseOverride = true;
	
	
	/**
	 * Constructor class for a ButtonList Object.
	 */
	public ButtonList() {
		buttonList = new ArrayList<Button>();
	}
	
	/**
	 * Add a button to the buttonList.
	 * @param button the button to add.
	 */
	public void addButton(Button button) {
		buttonList.add(button);
	}
	
	/**
	 * Return a button from the buttonList.
	 * @param button integer to return
	 * @return the button you want.
	 * @throws IndexOutOfBoundsException index out of bounds.
	 */
	public Button getButton(int button) {
		if (button < 0 || button > buttonList.size()) {
			IndexOutOfBoundsException baby = new IndexOutOfBoundsException();
			throw baby;
		}
		return buttonList.get(button);
	}
	
	/**
	 * @return the ArrayList of buttons.
	 */
	public ArrayList<Button> getButtonList() {
		return buttonList;
	}
	
	/**
	 * Update all buttons, handling their input and highlighting.
	 * @param input the input to process with.
	 */
	public void update(Input input) {
		// keyboard inputs
		if (input.isKeyPressed(Input.KEY_DOWN)) {
			index++; mouseOverride = false;
		} else if (input.isKeyPressed(Input.KEY_UP)) {
			index--; mouseOverride = false;
		}
		// mouse inputs
		for (int i = 0; i < buttonList.size(); i++) {
			if (buttonList.get(i).isMouseOver(input)) {
				index = i;
				mouseOverride = true;
			}
		}
		// checks
		if (index < 0) {
			index = buttonList.size() - 1;
		} else if (index > buttonList.size() - 1) {
			index = 0;
		}
	}
	
	/**
	 * Render all buttons in the buttonList.
	 * @param graphics context to draw in.
	 * @param input the input to use for highlighting.
	 * @param color the color to draw buttons in.
	 */
	public void render(Graphics graphics, Input input, Color color) {
		for (int i = 0; i < buttonList.size(); i++) {
			if (i == index) {
				buttonList.get(i).setHighlight(true);
			} else {
				buttonList.get(i).setHighlight(false);
			}
			if (mouseOverride && !buttonList.get(index).isMouseOver(input)) {
				buttonList.get(index).setHighlight(false);
			}
			buttonList.get(i).drawColor(graphics, input, color);
		}
	}
	
}
