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
	private boolean mouseMoved = false;
	private boolean mouseActive = true;
	private boolean textfieldActive = false;
	private int mouseX = 0, mouseY = 0;
	
	// popup data
	private Popup popup; // an elementlist can throw "OK" popups!
	
	// misc data
	private boolean selectable = true;
	
	/**
	 * Constructor class for a ButtonList Object.
	 */
	public ElementList() {
		list = new ArrayList<Element>();
	}
	
	/**
	 * Add an element to the elementlist.
	 * @param element the element to add.
	 */
	public void add(Element element) {
		element.setList(this);
		list.add(element);
	}
	
	/**
	 * Couple two elements horizontally for navigation.
	 * @param a the left element.
	 * @param b the right element.
	 */
	public void coupleHorizontal(Element a, Element b) {
		a.setRight(b);
		b.setLeft(a);
	}
	
	/**
	 * Couple two elements vertically for navigation.
	 * @param a the top element.
	 * @param b the bottom element.
	 */
	public void coupleVertical(Element a, Element b) {
		a.setBottom(b);
		b.setTop(a);
	}
	
	/**
	 * Couple two elements horizontally for navigation, in a loop.
	 * @param a one element.
	 * @param b another element.
	 */
	public void loopHorizontal(Element a, Element b) {
		a.setLeft(b);
		a.setRight(b);
		b.setLeft(a);
		b.setRight(a);
	}
	
	/**
	 * Couple two elements vertically for navigation, in a loop.
	 * @param a one element.
	 * @param b another element.
	 */
	public void loopVertical(Element a, Element b) {
		a.setBottom(b);
		a.setTop(b);
		b.setBottom(a);
		b.setTop(a);
	}
	
	/**
	 * Optionally, add a popup to this list for convenience.
	 * @param popup to add.
	 */
	public void add(Popup popup) {
		this.popup = popup;
		this.popup.setParentList(this);
	}
	
	/**
	 * Remove an element from the list at the specified index.
	 * @param i index of element to remove.
	 */
	public void remove(int i) {
		if (i < 0 || i > list.size()) {
			IndexOutOfBoundsException baby = new IndexOutOfBoundsException();
			throw baby;
		}
		list.get(i).getLeft().removeRight();
		list.get(i).getRight().removeLeft();
		list.get(i).getTop().removeBottom();
		list.get(i).getBottom().removeTop();
		list.remove(i);
	}
	
	/**
	 * Remove a specified element from the list.
	 * @param b element to remove.
	 */
	public void remove(Element b) {
//		if (!list.contains(b)) {
//			IndexOutOfBoundsException baby = new IndexOutOfBoundsException();
//			throw baby;
//		}
		if (b.getLeft() != null) {
			b.getLeft().removeRight();
		}
		if (b.getRight() != null) {
			b.getRight().removeLeft();
		}
		if (b.getTop() != null) {
			b.getTop().removeBottom();
		}
		if (b.getBottom() != null) {
			b.getBottom().removeTop();
		}
		list.remove(b);
	}
	
	/**
	 * @return The number of elements in the list.
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
			list.get(i).reset();
		}
		index = 0;
		mouseActive = true;
		mouseMoved = false;
		textfieldActive = false;
	}
	
	/**
	 * @return the ArrayList of buttons.
	 */
	public ArrayList<Element> getList() {
		return list;
	}
	
	/**
	 * Update all buttons, handling their input and highlighting.
	 * @param input the input to process with.
	 */
	public void update(Input input) {
		for (int i = 0; i < list.size(); i++) {
			list.get(i).update(input);
		}
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
		if (popup != null) {
			popup.update(input);
		}
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
	 * Update index, when mouse moves over a button,
	 * and there is no textfield active.
	 * @param input the input context to use.
	 */
	private void updateButtonsMouseOver(Input input) {
		if (!textfieldActive) {
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
	}
	
	/**
	 * Update button index for the keyboard.
	 * @param input the input context to use.
	 */
	private void updateButtonsKeyboard(Input input) {
		if (!textfieldActive  && !(popup != null && popup.getActive())) {
			if (input.isKeyPressed(Input.KEY_DOWN) // double check IS necessary.
					&& input.isKeyDown(Input.KEY_DOWN)) { // don't refactor.
				navigateBottom();
			} else if (input.isKeyPressed(Input.KEY_UP)
					&& input.isKeyDown(Input.KEY_UP)) {
				navigateTop();
			} else if (input.isKeyPressed(Input.KEY_RIGHT)
					&& input.isKeyDown(Input.KEY_RIGHT)) {
				navigateRight();
			} else if (input.isKeyPressed(Input.KEY_LEFT)
					&& input.isKeyDown(Input.KEY_LEFT)) {
				navigateLeft();
			}
		}
	}
	
	/**
	 * Navigate one element to the right.
	 */
	private void navigateRight() {
		mouseActive = false; mouseMoved = false;
		if (!list.get(index).isSelected()) {
			index = 0;
		} else {
			int newIndex = findIndex(list.get(index).getRight());
			if (newIndex != -1) {
				index = newIndex;
			}
		}
	}
	
	/**
	 * Navigate one element to the left.
	 */
	private void navigateLeft() {
		mouseActive = false; mouseMoved = false;
		if (!list.get(index).isSelected()) {
			index = 0;
		} else {
			int newIndex = findIndex(list.get(index).getLeft());
			if (newIndex != -1) {
				index = newIndex;
			}
		}
	}
	
	/**
	 * Navigate one element down.
	 */
	private void navigateTop() {
		mouseActive = false; mouseMoved = false;
		if (!list.get(index).isSelected()) {
			index = 0;
		} else {
			int newIndex = findIndex(list.get(index).getTop());
			if (newIndex != -1) {
				index = newIndex;
			}
		}
	}
	
	/**
	 * Navigate one element up.
	 */
	private void navigateBottom() {
		mouseActive = false; mouseMoved = false;
		if (!list.get(index).isSelected()) {
			index = 0;
		} else {
			int newIndex = findIndex(list.get(index).getBottom());
			if (newIndex != -1) {
				index = newIndex;
			}
		}
	}
	
	/**
	 * Update highlighting for all buttons.
	 * @param input context to use.
	 */
	private void updateHighlighting(Input input) {
		if (!textfieldActive) {
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
	}
	
	/**
	 * @param e element to search for
	 * @return the index of this element
	 */
	private int findIndex(Element e) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) == e) {
				return i;
			}
		}
		return -1;
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
		if (popup != null) {
			popup.render(graphics, input, color);
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

	/**
	 * @return the mouseActive
	 */
	public boolean isMouseActive() {
		return mouseActive;
	}

	/**
	 * @param mouseActive the mouseActive to set
	 */
	public void setMouseActive(boolean mouseActive) {
		this.mouseActive = mouseActive;
	}

	/**
	 * @return the textfieldActive
	 */
	public boolean isTextfieldActive() {
		return textfieldActive;
	}

	/**
	 * @param textfieldActive the textfieldActive to set
	 */
	public void setTextfieldActive(boolean textfieldActive) {
		this.textfieldActive = textfieldActive;
	}
	
	/**
	 * Make the elementlist throw a popup with a warning.
	 * @param warning to set.
	 */
	public void throwPopup(String warning) {
		if (popup != null) {
			popup.setActive(true);
			popup.setText(warning);
			textfieldActive = false;
			mouseActive = false;
			selectable = false;
		}
	}
	
}
