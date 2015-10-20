package guiobjects;

import logic.MyRectangle;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.TextField;

/**
 * Textfield class, representing a textfield in our GUI. Extended from Element object.
 * Supports updating though ElementList, if necessary.
 * @author Mark
 */
public class Element_Textfield extends Element{

	private static Image fieldNorm;
	private static Image fieldAdd;
	private static Image fieldOnNorm;
	private static Image fieldOnAdd;
	private static Image cursorNorm;
	private static Image cursorAdd;
	
	private TextField textfield;
	private Element_Button button;
	private String text;
	private int stringlength;
	private int cursor;
	
	private static final int TEXT_FIELD_WIDTH = 800;
	private static final int TEXT_FIELD_HEIGHT = 60;
	private static final int TF_BACKGROUND_DEVIATION = 27;
	private static final int TC_X_DEVIATION = 15;
	private static final int TC_Y_DEVIATION = 7;
	private static final int TF_MAX_LENGTH = 40;
	
	/**
	 * Set the Textfield's images.
	 * @throws SlickException 
	 */
	public static void init() throws SlickException {
		fieldNorm = new Image("resources/images_UI/textfield_Norm.png");
		fieldAdd = new Image("resources/images_UI/textfield_Add.png");
		fieldOnNorm = new Image("resources/images_UI/textfield2_Norm.png");
		fieldOnAdd = new Image("resources/images_UI/textfield2_Add.png");
		cursorNorm = new Image("resources/images_UI/textcursor_normal.png");
		cursorAdd = new Image("resources/images_UI/textcursor_Add.png");
	}
	
	/**
	 * Textfield constructor. Creates a drawable textfield element for our GUI.
	 * @param x location of textfield
	 * @param y location of textfield
	 * @param text run-time text in textfield
	 * @param container necessary appgamegontainer for it to live in
	 * @throws SlickException probably the images cant be found
	 */
	public Element_Textfield(int x, int y, String text, GameContainer container) 
			throws SlickException {
		textfield = new TextField(container, null, (int) x, (int) y,
				TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);
		button = new Element_Button(100, 100, 100, 100, "X");
		textfield.setBackgroundColor(null);
		textfield.setBorderColor(null);
		
		this.text = text;
		this.stringlength = this.text.length();
		setX(x);
		setY(y);
		
		textfield.setText(text);
		textfield.setCursorPos(text.length());
		textfield.setMaxLength(TF_MAX_LENGTH);
		textfield.setFocus(false);
	}

	/**
	 * Abstract for update method.
	 * @param input context
	 */
	@Override
	public void update(Input input) {
		if (hasFocus()) {
			if (!textfield.hasFocus()) {
				textfield.setFocus(true);
				//textfield.setText("");
				//cursor = 0;
			}
		} else {
			textfield.setFocus(false);
		}
		
//		// process mouse input
//		if (getRectangle().contains(input.getMouseX(), input.getMouseY())
//				&& input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
//			textfield.setFocus(true);
//			setFocus(true);
//			textfield.setText("");
//			cursor = 0;
//		}
//		// process the bloody textfield input edgecase
//		if (textfield.hasFocus()) {
//			if (!hasFocus()) {
//				textfield.setText("");
//				cursor = 0;
//				setFocus(true);
//			}
//		} else {
//			setFocus(false);
//		}
		// process keyboard input
		if (textfield.hasFocus()) {
			processTextInput(input);
		}
	}

	/**
	 * Provides updates to keyboard functions, so the cursor etc can work properly.
	 * @param input the Input object to use.
	 */
	private void processTextInput(Input input) {
		// process typing in field
		if (stringlength < text.length()) {
			cursor += (text.length() - stringlength);
			stringlength = text.length();
		} else if (stringlength > text.length()) {
			cursor -= (stringlength - text.length());
			stringlength = text.length();
		}
		// safety check for empty field
		if (text.length() == 0) {
			stringlength = 0;
			cursor = 0;
		}
		// process arrow keys
		if (input.isKeyPressed(Input.KEY_RIGHT) && cursor < text.length()) {
			cursor++;
		} else if (input.isKeyPressed(Input.KEY_LEFT) && cursor > 0) {
			cursor--;
		}
		textfield.setCursorPos(cursor);
	}
	
	/**
	 * get bounding box rectangle of the button.
	 * @return the Rectangle object of this button.
	 */
	public MyRectangle getRectangle() {
		return new MyRectangle(getX(), getY(), TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);
	}
	
	@Override
	public void render(Graphics graphics, Color color) {
		button.render(graphics, color);
		text = textfield.getText();
		if (hasFocus()) {
			renderHighlight(graphics, color);
		} else {
			RND.getInstance().drawColor(new RenderOptions(graphics, fieldNorm, fieldAdd, 
					getX() - TF_BACKGROUND_DEVIATION, getY() - TF_BACKGROUND_DEVIATION, color));
		}
		RND.getInstance().textSpecifiedColor(graphics, getX(), getY(), textfield.getText(), color);
	}

	/**
	 * Draw this textfields highlighting effects.
	 * @param graphics context to draw in
	 * @param color to draw with
	 */
	private void renderHighlight(Graphics graphics, Color color) {
		RND.getInstance().drawColor(new RenderOptions(graphics, fieldOnNorm, fieldOnAdd, 
				getX() - TF_BACKGROUND_DEVIATION, getY() - TF_BACKGROUND_DEVIATION, color));
		if (cursor > 0 && cursor <= textfield.getText().length()) {
			String s = textfield.getText().substring(0, cursor);
			float length = RND.getInstance().getStringPixelWidth(s);
			RND.getInstance().drawColor(new RenderOptions(graphics, cursorNorm, cursorAdd, 
					getX() - TF_BACKGROUND_DEVIATION + length, 
					getY() - TC_Y_DEVIATION, color));
		} else if (cursor == 0) {
			RND.getInstance().drawColor(new RenderOptions(graphics, cursorNorm, cursorAdd, 
					getX() - TF_BACKGROUND_DEVIATION + TC_X_DEVIATION, 
					getY() - TC_Y_DEVIATION, color));
		}
	}
	
}
