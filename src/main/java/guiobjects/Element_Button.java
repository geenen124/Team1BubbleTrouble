package guiobjects;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * Class that respresents a button - you can click on it and something might happen.
 * @author Mark
 */
public class Element_Button extends Element {
	
	private String text;
	
	private static Image imageButtonHeadN;
	private static Image imageButtonHeadA;
	private static Image imageButtonBodyN;
	private static Image imageButtonBodyA;
	private static Image imageButtonTailN;
	private static Image imageButtonTailA;
	private static AngelCodeFont dosFontM; // masking (button) font
	
	private static final int BUTTON_BEGIN_OFFSET = 4;
	private static final int BUTTON_END_OFFSET = 22;
	private static final int BUTTON_X_OFFSET = 14;
	private static final int BUTTON_Y_OFFSET = 14;
	private static final int BUTTON_HEIGHT = 53;
	private static final float BUTTON_TEXT_OPACITY = 0.85f;
	
	private static final float HALF = 0.5f;
	
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
	 * Constructor for Button object.
	 * @param x coordinate
	 * @param y coordinate
	 * @param width of the button
	 * @param height of the button
	 * @param text the text to show in the button
	 */
	public Element_Button(int x, int y, int width, int height, String text) {
		super();
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
		this.text = text;
	}

	/**
	 * Abstract for update method.
	 * @param input context
	 */
	@Override
	public void update(Input input) {
		// stubbed. Buttons don't update live.
	}

	@Override
	public void render(Graphics graphics, Color color) {
		if (isEnabled() && hasFocus()) {
			renderHighlight(graphics, color);
		} else {
			RND.getInstance().text(graphics, getX(), getY(), text);
		}
	}

	/**
	 * Draw this button's highlighting effects.
	 * @param graphics context to draw in
	 * @param color to draw with
	 */
	private void renderHighlight(Graphics graphics, Color color) {
		// draw head
		RenderOptions ro1 = new RenderOptions(graphics, imageButtonHeadN, imageButtonHeadA, 
				getX() - BUTTON_X_OFFSET, 
				getY() - BUTTON_Y_OFFSET, color);
		RND.getInstance().drawColor(ro1);
		
		// draw body
		RND.getInstance().drawColor(new RenderOptions(graphics, imageButtonBodyN, imageButtonBodyA,
				getX() + BUTTON_BEGIN_OFFSET, getY() - BUTTON_Y_OFFSET, color),
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
	}
	
	
}
