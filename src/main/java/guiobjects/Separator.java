package guiobjects;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;


/**
 * Separator class, which is mainly a GUI element. The separator is a line that may contain a title.
 * Please note this class is HIGHLY untestable, as it is dependent on AngelCodeFont and RND,
 * both of which dive very, very deeply into Slick2D.
 * @author Mark
 */
public class Separator {
	
	private float x;
	private float y;
	private float screenWidth;
	private String text;
	private String title;
	private boolean hasButton;
	
	private static final int RETURN_BUTTON_WIDTH = 9;
	
	/**
	 * Constructor for a new Separator Object, which is used to draw lines in the GUI.
	 * @param x location X-coordinate
	 * @param y location Y-coordinate
	 * @param button whether or not it should have a return button
	 * @param title the text it should show. can be an empty string.
	 * @param width width of the screen you want this in
	 */
	public Separator(float x, float y, boolean button, String title, float width) {
		this.x = x;
		this.y = y;
		this.screenWidth = width;
		this.hasButton = button;
		this.title = title;
		update();
	}

	/**
	 * Performs call to RND to draw the text elements of the separator in the right color.
	 * @param graphics context to draw in
	 * @param color to draw in
	 */
	public void drawColor(Graphics graphics, Color color) {
		RND.getInstance().textSpecifiedColor(graphics, x, y, text, color);
	}
	
	/**
	 * Recalculates the position, size and layout of text.
	 */
	private void update() {

		StringBuffer firstHalf = new StringBuffer(), secondHalf = new StringBuffer();
		// lengths
		int titleLength = RND.getInstance().getStringPixelWidth(title);
		int secondLength = (int) (screenWidth / 2 - x) - titleLength / 2;
		int firstLength = secondLength;
		
		// if there's a return button in front!
		if (hasButton) {
			for (int i  = 0; i < RETURN_BUTTON_WIDTH; i++) {
				firstHalf.append(' ');
			}
		}
		
		// fill with characters
		while (RND.getInstance().getStringPixelWidth(firstHalf.toString()) < firstLength) {
			firstHalf.append('=');
		}
		while (RND.getInstance().getStringPixelWidth(secondHalf.toString()) < firstLength) {
			secondHalf.append('=');
		}

		// add together
		this.text = firstHalf.toString() + title + secondHalf.toString();
		
		if (titleLength == 0) {
			this.text = this.text.concat("=");
		}
	}
	
	/**
	 * @return the x location of the separator.
	 */
	public float getX() {
		return x;
	}

	/**
	 * @return the y location of the separator.
	 */
	public float getY() {
		return y;
	}

	/**
	 * @return the text shown in the separator.
	 */
	public String getText() {
		return text;
	}

	/**
	 * @return the title shown in the separator.
	 */
	public String getTitle() {
		return text;
	}
	
	/**
	 * @param text to show in the separator.
	 */
	public void setTitle(String text) {
		this.title = text;
		update();
	}

	/**
	 * @return whether there is a button in here somewhere.
	 */
	public boolean hasButton() {
		return hasButton;
	}
	
	

}
