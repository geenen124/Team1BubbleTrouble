package guiobjects;

import logic.MyRectangle;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

/**
 * Abstract class representing an interactive UI-element. This is used to better support coherence,
 * interaction and keyboard-input when working with buttons, textboxes, etc.
 * @author Mark
 *
 */
public abstract class Element {
	
	private boolean enabled = true; // determine whether an element is 'interactive'.
	private boolean selected = false; // determine whether an element is the one selected.
	private int x, y, width, height;
	
	private ElementList list;
	
	private static final float HALF = 0.5f;
	
	/**
	 * Abstract for update method.
	 * @param input context
	 */
	public abstract void update(Input input);
	
	/**
	 * Abstract for render method.
	 * @param graphics context.
	 * @param color to draw with.
	 */
	public abstract void render(Graphics graphics, Color color);

	/**
	 * returns boolean if the mouse is hovering over button right now.
	 * @param input input used to find mouse.
	 * @return boolean whether or not the mouse is hovering over the button.
	 */
	public boolean isMouseOver(Input input) {
		return getRectangle().contains(input.getMouseX(), input.getMouseY());
	}
	
	/**
	 * get bounding box rectangle of the button.
	 * @return the Rectangle object of this button.
	 */
	public MyRectangle getRectangle() {
		return new MyRectangle(x, y, width, height);
	}
	
	/**
	 * @return whether the element is currently capable of being selected.
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param enabled enable the element.
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return whether the element is currently in focus for input.
	 */
	public boolean hasFocus() {
		return selected;
	}

	/**
	 * @param focus make the element focussed on.
	 */
	public void setFocus(boolean focus) {
		this.selected = focus;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
	/**
	 * Get the center x value of the button.
	 * @return the x value of the center of the button.
	 */
	public float getCenterX() {
		return x + (HALF * width);
	}
	
	/**
	 * Get the center y value of the button.
	 * @return the y value of the center of the button.
	 */
	public float getCenterY() {
		return y + (HALF * height);
	}
	
	/**
	 * Get the maximum x value of the button.
	 * @return the maximum x value of this button.
	 */
	public float getMaxX() {
		return x + width;
	}
	
	/**
	 * Get the maximum y value of the button.
	 * @return the maximum y value of this button.
	 */
	public float getMaxY() {
		return y + height;
	}
	
	/**
	 * @return whether the button is highlighted.
	 */
	public boolean isSelected() {
		return selected;
	}

	/**
	 * @param selected make the button highlight.
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	/**
	 * Set the element list this element belongs to.
	 * @param list the list to set.
	 */
	public void setList(ElementList list) {
		this.list = list;
	}
	
}
