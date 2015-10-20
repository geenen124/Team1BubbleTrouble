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
public class ElementList {

	// storage data
	private ArrayList<Element> list;
	private int index = 0;
	
	// mouse data
	private boolean mouseActive = true;
	private boolean textfieldActive = false;
	private boolean mouseMoved = false;
	private int mouseX = 0, mouseY = 0;
	
	// misc data
	private boolean selectable = true;
	
	/**
	 * Constructor class for a ButtonList Object.
	 */
	public ElementList() {
		list = new ArrayList<Element>();
	}
	
	/**
	 * Add a button to the buttonList.
	 * @param button the button to add.
	 */
	public void add(Button button) {
		button.setList(this);
		list.add(button);
	}
	
	/**
	 * Remove a button from the list at the specified index.
	 * @param i index of button to remove.
	 */
	public void remove(int i) {
		if (i < 0 || i > list.size()) {
			IndexOutOfBoundsException baby = new IndexOutOfBoundsException();
			throw baby;
		}
		list.remove(i);
	}
	
	/**
	 * Remove a button from the list at the specified index.
	 * @param b button to remove.
	 */
	public void remove(Button b) {
		list.remove(b);
	}
	
	/**
	 * @return The number of buttons in the list.
	 */
	public int getSize() {
		return list.size();
	}
	
	/**
	 * Return an element from the buttonList.
	 * @param element integer to return
	 * @return the button you want.
	 * @throws IndexOutOfBoundsException index out of bounds.
	 */
	public Element get(int element) {
		if (element < 0 || element > list.size()) {
			IndexOutOfBoundsException baby = new IndexOutOfBoundsException();
			throw baby; // woooh!
		}
		return list.get(element);
	}
	
	/**
	 * Reset all buttons in this list to an initial state. Should be used when
	 * entering a state!
	 */
	public void reset() {
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setSelected(false);
		}
		index = 0;
		mouseActive = true;
		mouseMoved = false;
	}
	
	/**
	 * @return the ArrayList of buttons.
	 */
	public ArrayList<Element> getList() {
		return list;
	}
	
	/**
	 * Update selectivity of buttons, if there is a popup.
	 * @param popup to use.
	 */
	public void update(Popup popup) {
		if (popup.getActive()) {
			selectable = false;
		} else {
			selectable = true;
		}
	}
	
	/**
	 * Update all buttons, handling their input and highlighting.
	 * @param input the input to process with.
	 */
	public void update(Input input) {
		// check if the mouse has moved
		updateMouseMovement(input);
		// respond to keyboard inputs
		updateButtonsKeyboard(input);
		// respond to mouse inputs
		updateButtonsMouseOver(input);
		// loop-around the index.
		if (index < 0) {
			index = list.size() - 1;
		} else if (index > list.size() - 1) {
			index = 0;
		}
		// make sure all buttons have the appropriate highlighting.
		updateHighlighting(input);
	}
	
	/**
	 * Update mouse location to check whether the mouse has moved.
	 * @param input the input context to use.
	 */
	private void updateMouseMovement(Input input) {
		if (input.getMouseX() != mouseX || input.getMouseY() != mouseY) {
			mouseMoved = true;
			mouseX = input.getMouseX();
			mouseY = input.getMouseY();
		}
	}
	
	/**
	 * Update button index for the mouse.
	 * @param input the input context to use.
	 */
	private void updateButtonsMouseOver(Input input) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).isMouseOver(input)) {
				updateMouseMovement(input);
				if (mouseMoved) {
					index = i;
					mouseActive = true;
				}
				break;
			}
		}
	}
	
	/**
	 * Update button index for the keyboard.
	 * @param input the input context to use.
	 */
	private void updateButtonsKeyboard(Input input) {
		if (input.isKeyPressed(Input.KEY_DOWN)) {
			mouseActive = false; 
			mouseMoved = false;
			if (!list.get(index).isSelected()) {
				index = 0;
			} else {
				index++;
			}
		} else if (input.isKeyPressed(Input.KEY_UP)) {
			mouseActive = false;
			mouseMoved = false;
			if (!list.get(index).isSelected()) {
				index = 0;
			} else {
				index--;
			}
		}
	}
	
	/**
	 * Update highlighting for all buttons.
	 * @param input context to use.
	 */
	private void updateHighlighting(Input input) {
		for (int i = 0; i < list.size(); i++) {
			if (i == index && selectable) {
				list.get(i).setSelected(true);
			} else {
				list.get(i).setSelected(false);
			}
			if (mouseActive && !list.get(index).isMouseOver(input)) {
				list.get(index).setSelected(false);
			}
		}
	}
	
	/**
	 * Render all buttons in the buttonList.
	 * @param graphics context to draw in.
	 * @param input the input to use for highlighting.
	 * @param color the color to draw buttons in.
	 */
	public void render(Graphics graphics, Input input, Color color) {
		for (int i = 0; i < list.size(); i++) {
			list.get(i).render(graphics, color);
		}
	}
	
	/**
	 * @return whether or not one of all the buttons is highlighted.
	 */
	public boolean anyHighlighted() {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).isSelected()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @return whether buttons are selectable (for mouseovers etc)
	 */
	public boolean isSelectable() {
		return selectable;
	}

	/**
	 * @param selectable turns on/off button activity
	 */
	public void setSelectable(boolean selectable) {
		this.selectable = selectable;
	}
	
	
}
