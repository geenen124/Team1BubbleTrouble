package guiobjects;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import logic.MyRectangle;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.TextField;

/**
 * Textfield class, representing a textfield in our GUI.
 * @author Mark
 */
public class Textfield extends Element {
	
	private TextField textfield;
	
	private static final int TEXT_FIELD_WIDTH = 800;
	private static final int TEXT_FIELD_HEIGHT = 60;
	private static final int TF_BACKGROUND_DEVIATION = 27;
	private static final int TC_X_DEVIATION = 15;
	private static final int TC_Y_DEVIATION = 7;
	private static final int TF_MAX_LENGTH = 34;
	
	private static Image fieldNorm;
	private static Image fieldAdd;
	private static Image fieldOnNorm;
	private static Image fieldOnAdd;
	private static Image fieldMouseNorm;
	private static Image fieldMouseAdd;
	private static Image cursorNorm;
	private static Image cursorAdd;
	
	private int stringlength;
	private int cursor;
	private boolean focusOverride;
	private boolean waitMove = false;
	
	private Button button;
	
	private static final int INPUT_FACTOR = 150;
	private static final int BUTTON_OFFSET = 250;
	private static final int BUTTON_WIDTH = 95;
	private static final int BUTTON_HEIGHT = 35;
	
	/**
	 * Textfield constructor. Creates a drawable textfield element for our GUI.
	 * @param x location of textfield
	 * @param y location of textfield
	 * @param text run-time text in textfield
	 * @param container necessary appgamegontainer for it to live in
	 * @throws SlickException probably the images cant be found
	 */
	public Textfield(float x, float y, String text, GameContainer container) throws SlickException {
		textfield = new TextField(container, null, 0, 0,
				TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);
		textfield.setBackgroundColor(null);
		textfield.setBorderColor(null);
		
		setText(text);
		this.stringlength = getText().length();
		
		textfield.setText(text);
		textfield.setCursorPos(text.length());
		textfield.setMaxLength(TF_MAX_LENGTH);
		textfield.setFocus(false);
		
		focusOverride = false;
		
		button = new Button(x + TEXT_FIELD_WIDTH - BUTTON_OFFSET, y, "< BACK");
		
		setX((int) x);
		setY((int) y);
		setWidth(TEXT_FIELD_WIDTH);
		setHeight(TEXT_FIELD_HEIGHT);
	}
	
	/**
	 * Initialize Textfields by loading images etc.
	 * @throws SlickException  meaning you're missing images!
	 */
	public static void init() throws SlickException {
		fieldNorm = new Image("resources/images_UI/textfield_Norm.png");
		fieldAdd = new Image("resources/images_UI/textfield_Add.png");
		fieldOnNorm = new Image("resources/images_UI/textfield2_Norm.png");
		fieldOnAdd = new Image("resources/images_UI/textfield2_Add.png");
		fieldMouseNorm = new Image("resources/images_UI/textfield3_Norm.png");
		fieldMouseAdd = new Image("resources/images_UI/textfield3_Add.png");
		cursorNorm = new Image("resources/images_UI/textcursor_normal.png");
		cursorAdd = new Image("resources/images_UI/textcursor_Add.png");
	}
	
	/**
	 * Provides updates to textfield, so it can properly run button inputs etc.
	 * @param input the Input object to use
	 */
	@Override
	public void update(Input input) {
		textfield.setFocus(focusOverride);
		// handle button input
		processDeleteButton(input);
		// handle enter/mouse input
		processEnterMouse(input);
		// handle backspace input
		processBackspace(input);
		// handle text input
		processTextInput(input);
	}
	
	/**
	 * Update textfield accordingly when the BAK button is pressed.
	 * @param input the Input object to use.
	 */
	private void processDeleteButton(Input input) {
		button.setSelected(button.isMouseOver(input));
		if (button.isMouseOver(input) 
				&& (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)  
				&& input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))) {
			focusOverride = true;
			getList().setTextfieldActive(true);
			textfield.setText("");
			cursor = 0;
		}
	}
	
	/**
	 * Update textfield accordingly when enter (or a mouse button) is pressed.
	 * @param input the Input object to use.
	 */
	public void processEnterMouse(Input input) {
		if (isSelected()
				&& ((input.isMousePressed(Input.MOUSE_LEFT_BUTTON) 
						&& input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) 
					|| 
					(input.isKeyPressed(Input.KEY_ENTER) 
						&& input.isKeyDown(Input.KEY_ENTER))
					)) {
			focusOverride = !focusOverride;
			getList().setTextfieldActive(!getList().isTextfieldActive());
			cursor = getText().length();
			textfield.setCursorPos(cursor);
		}
	}
	
	/**
	 * Update textfield accordingly when backspace is pressed.
	 * @param input the Input object to use.
	 */
	public void processBackspace(Input input) {
		if (isSelected() && input.isKeyPressed(Input.KEY_BACK)
				&& input.isKeyDown(Input.KEY_BACK)
				&& !focusOverride) {
			focusOverride = true;
			getList().setTextfieldActive(true);
			textfield.setText("");
			cursor = 0;
		}
	}
	
	/**
	 * Provides updates to keyboard functions, so the cursor etc can work properly.
	 * @param input the Input object to use.
	 */
	private void processTextInput(Input input) {
		if (textfield.hasFocus()) {
			// process typing in field, move cursor accordingly
			if (stringlength < getText().length()) {
				cursor += (getText().length() - stringlength);
				stringlength = getText().length();
			} else if (stringlength > getText().length()) {
				cursor -= (stringlength - getText().length());
				stringlength = getText().length();
			}
			// safety containment check for empty field
			if (getText().length() == 0) {
				stringlength = 0;
				cursor = 0;
			}
			// process arrow keys, move cursor accordingly
			if (input.isKeyDown(Input.KEY_RIGHT) && cursor < getText().length() && !waitMove) {
				cursor++;
				scheduleWait();
			} else if (input.isKeyDown(Input.KEY_LEFT) && cursor > 0 && !waitMove) {
				cursor--;
				scheduleWait();
			} 
			// update data
			textfield.setCursorPos(cursor);
			setText(textfield.getText());
		}
	}
	
	/**
	 * Disallow arrow keys in the textfield for a while.
	 */
	private void scheduleWait() {
		waitMove = true;
		Executors.newScheduledThreadPool(1).schedule(() -> waitMove = false,
				INPUT_FACTOR, TimeUnit.MILLISECONDS);
	}
	
	/**
	 * get bounding box rectangle of the button.
	 * @return the Rectangle object of this button.
	 */
	public MyRectangle getRectangle() {
		return new MyRectangle(getX(), getY(), TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);
	}
	
	/**
	 * Reset this Textfield to an initial state.
	 */
	@Override
	public void reset() {
		super.reset();
		focusOverride = false;
	}
	
	/**
	 * Draws the textfield.
	 * @param graphics context to draw in.
	 * @param color to draw with.
	 */
	@Override
	public void render(Graphics graphics, Color color) {
		// draw box
		if (isSelected() && focusOverride) {
			renderTextfieldActive(graphics, color);
		} else if (isSelected() && !focusOverride) {
			renderTextfieldSelected(graphics, color);
		} else {
			renderTextfieldNormal(graphics, color);
		}
		button.render(graphics, color);
		// draw text
		RND.getInstance().textSpecifiedColor(graphics, getX(), getY(), getText(), color);
	}
	
	/**
	 * Render function used to draw the textfield's normal state.
	 * @param graphics context to draw in.
	 * @param color to draw with.
	 */
	private void renderTextfieldNormal(Graphics graphics, Color color) {
		RND.getInstance().drawColor(new RenderOptions(graphics, fieldNorm, fieldAdd, 
				getX() - TF_BACKGROUND_DEVIATION, 
				getY() - TF_BACKGROUND_DEVIATION, color));
	}
	
	/**
	 * Render function used to draw the textfield's selection state.
	 * @param graphics context to draw in.
	 * @param color to draw with.
	 */
	private void renderTextfieldSelected(Graphics graphics, Color color) {
		RND.getInstance().drawColor(new RenderOptions(graphics, fieldMouseNorm, fieldMouseAdd, 
				getX() - TF_BACKGROUND_DEVIATION, 
				getY() - TF_BACKGROUND_DEVIATION, color));
	}
	
	/**
	 * Render function used to draw the textfield's active state.
	 * @param graphics context to draw in.
	 * @param color to draw with.
	 */
	private void renderTextfieldActive(Graphics graphics, Color color) {
		RND.getInstance().drawColor(new RenderOptions(graphics, fieldOnNorm, fieldOnAdd, 
				getX() - TF_BACKGROUND_DEVIATION, getY() - TF_BACKGROUND_DEVIATION, color));
		if (cursor > 0 && cursor <= getText().length()) {
			String s = getText().substring(0, cursor);
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
