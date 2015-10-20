package guiobjects;

import logic.MyRectangle;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

/**
 * Abstract class representing an interactive UI-element. This is used to better support coherence,
 * interaction and keyboard-input when working with buttons, textboxes, etc. Subclass this element
 * if you want to create something for ElementList.
 * @author Mark
 *
 */
public abstract class Element {
	
	private ElementList list;
	
	private Element left;
	private Element right;
	private Element bottom;
	private Element top;
	
	private boolean enabled = true; // determine whether an element is 'interactive'.
	private boolean selected = false; // determine whether an element is the one selected.
	private int x, y, width, height;
	private String text;
	
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
	 * Reset this element to an initial state.
	 */
	public void reset() {
		enabled = true;
		selected = false;
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
	
	/**
	 * @return the ElementList belonging this object belongs to.
	 */
	public ElementList getList() {
		return list;
	}
	
	/**
	 * Set the text this button displays.
	 * @param text the text to set.
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * @return the text in this button.
	 */
	public String getText() {
		return text;
	}

	/**
	 * @return element to the left
	 */
	public Element getLeft() {
		return left;
	}

	/**
	 * @param left navigable element to the left.
	 */
	public void setLeft(Element left) {
		this.left = left;
	}

	/**
	 * @return element to the right
	 */
	public Element getRight() {
		return right;
	}

	/**
	 * @param right navigable element to the right.
	 */
	public void setRight(Element right) {
		this.right = right;
	}
	
	/**
	 * @return element to the top
	 */
	public Element getTop() {
		return top;
	}

	/**
	 * @param top navigable element to the left.
	 */
	public void setTop(Element top) {
		this.top = top;
	}

	/**
	 * @return element below
	 */
	public Element getBottom() {
		return bottom;
	}

	/**
	 * @param bottom navigable element below.
	 */
	public void setBottom(Element bottom) {
		this.bottom = bottom;
	}
	
	/**
	 * empty the bottom element.
	 */
	public void removeBottom() {
		bottom = null;
	}
	
	/**
	 * empty the top element.
	 */
	public void removeTop() {
		top = null;
	}
	
	/**
	 * empty the left element.
	 */
	public void removeLeft() {
		left = null;
	}
	
	/**
	 * empty the right element.
	 */
	public void removeRight() {
		right = null;
	}
	
}
