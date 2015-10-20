package guiobjects;
import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * Class that respresents a button - you can click on it and something might happen.
 * @author Menno
 */
public class Button extends Element {
	
	// assets
	private static Image imageButtonHeadN;
	private static Image imageButtonHeadA;
	private static Image imageButtonBodyN;
	private static Image imageButtonBodyA;
	private static Image imageButtonTailN;
	private static Image imageButtonTailA;
	private static AngelCodeFont dosFontM;
	
	private String text;
	
	private static final int BUTTON_BEGIN_OFFSET = 4;
	private static final int BUTTON_END_OFFSET = 22;
	private static final int BUTTON_X_OFFSET = 14;
	private static final int BUTTON_Y_OFFSET = 14;
	private static final int BUTTON_HEIGHT = 53;
	private static final float BUTTON_TEXT_OPACITY = 0.85f;
	
	/**
	 * Button constructor class.
	 * @param x coordinate
	 * @param y coordinate
	 * @param width of the button
	 * @param height of the button
	 * @param text the text to show in the button
	 */
	public Button(float x, float y, float width, float height, String text) {
		super();
		setX((int) x);
		setY((int) y);
		setWidth((int) width);
		setHeight((int) height);
		this.text = text;
	}
	
	/**
	 * Set the button's images.
	 * @throws SlickException 
	 */
	public static void init() throws SlickException {
		imageButtonHeadN = 
				new Image("resources/images_UI/images_Buttons/Menu_Button_Head_Norm.png");
		imageButtonHeadA = 
				new Image("resources/images_UI/images_Buttons/Menu_Button_Head_Add.png");
		imageButtonBodyN = 
				new Image("resources/images_UI/images_Buttons/Menu_Button_Body_Norm.png");
		imageButtonBodyA = 
				new Image("resources/images_UI/images_Buttons/Menu_Button_Body_Add.png");
		imageButtonTailN = 
				new Image("resources/images_UI/images_Buttons/Menu_Button_Tail_Norm.png");
		imageButtonTailA = 
				new Image("resources/images_UI/images_Buttons/Menu_Button_Tail_Add.png");
		dosFontM = new AngelCodeFont("resources/images_Font/dosfont.fnt",
				"resources/images_Font/dosfont_Mask.png");
	}
	
	/**
	 * returns boolean if the mouse is hovering over button right now.
	 * @param input input used to find mouse.
	 * @return boolean whether or not the mouse is hovering over the button.
	 */
	public boolean isMouseOver(Input input) {
		return getRectangle().contains(input.getMouseX(), input.getMouseY());
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

	@Override
	public boolean equals(Object other) {
		if (other instanceof Button) {
			Button that = (Button) other;
			if (this.getX() == that.getX()
					&& this.getY() == that.getY()
					&& this.getWidth() == that.getWidth()
					&& this.getHeight() == that.getHeight()
					&& this.text.equals(that.getText())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return 0;
	}
	
	/**
	 * Render this button.
	 * @param graphics the context to draw in.
	 * @param color the color to draw with.
	 */
	public void render(Graphics graphics, Color color) {
		if (isSelected()) {
			// draw head
			RenderOptions ro1 = new RenderOptions(graphics, imageButtonHeadN, imageButtonHeadA, 
					getX() - BUTTON_X_OFFSET, 
					getY() - BUTTON_Y_OFFSET, color);
			RND.getInstance().drawColor(ro1);
			// draw body
			RenderOptions ro2 = new RenderOptions(graphics, imageButtonBodyN, imageButtonBodyA, 
					getX() + BUTTON_BEGIN_OFFSET, 
					getY() - BUTTON_Y_OFFSET, color);
			RND.getInstance().drawColor(ro2, 
					dosFontM.getWidth(text) - BUTTON_END_OFFSET, BUTTON_HEIGHT);
			// draw tail
			RenderOptions ro3 = new RenderOptions(graphics, imageButtonTailN, imageButtonTailA, 
					getX() + BUTTON_BEGIN_OFFSET + dosFontM.getWidth(text) 
					- BUTTON_END_OFFSET, getY() - BUTTON_Y_OFFSET, color);
			RND.getInstance().drawColor(ro3);
			// draw text
			dosFontM.drawString(getX(), getY(), text, 
					new Color(0, 0, 0, BUTTON_TEXT_OPACITY));
			RND.getInstance().textSpecifiedColor(graphics, getX(), getY(), text, 
					new Color(color.r, color.g, color.b, 1f - BUTTON_TEXT_OPACITY));
		} else {
			RND.getInstance().text(graphics, getX(), getY(), text);
		}
	}

	@Override
	public void update(Input input) {
		// TODO Auto-generated method stub
		
	}
}
