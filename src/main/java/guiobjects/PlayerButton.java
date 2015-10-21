package guiobjects;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * An element representing a selectable player image in the settings menu.
 * @author Mark
 */
public class PlayerButton extends Button {

	private static SpriteSheet mannetjeN, mannetjeA, mannetjeS, mannetjeS2;
	private static SpriteSheet telefoonN, telefoonA, telefoonS, telefoonS2;
	private static SpriteSheet arieN, arieA, arieS, arieS2;
	
	private static Image highLightN;
	private static Image highLightA;
	
	private static final int PLAYER_SPRITE_WIDTH = 120;
	private static final int PLAYER_SPRITE_HEIGHT = 120;
	
	private boolean active = false;
	
	private SpriteSheet imageN, imageA, imageS, imageS2;
	private PlayerType type;
	private String spriteN;
	private String spriteA;
	private int player;
	

	private static final String PLAYER_NORM = "Playersprite_Norm.png";
	private static final String PLAYER_ADD = "Playersprite_Add.png";
	private static final String PLAYER2_NORM = "Player2sprite_Norm.png";
	private static final String PLAYER2_ADD = "Player2sprite_Add.png";
	private static final String ARIE_NORM = "arieSprite.png";
	private static final String ARIE_ADD = "arieSprite_Add.png";
	
	/**
	 * Enum to more easily select player types.
	 * @author Mark
	 */
	public enum PlayerType {
		GAMEBOY, 
		PHONE, 
		ARIE
	}
	
	/**
	 * Constructor for the PlayerButton object.
	 * @param x location
	 * @param y location
	 * @param type the player image type.
	 * @param player player 1 or 2.
	 */
	public PlayerButton(float x, float y, PlayerType type, int player) {
		super(x, y, "");
		setWidth(PLAYER_SPRITE_WIDTH); setHeight(PLAYER_SPRITE_HEIGHT);
		setType(type); this.player = player;
	}
	
	/**
	 * Testing Constructor for the PlayerButton object.
	 * @param x location
	 * @param y location
	 * @param type the player image type.
	 * @param player player 1 or 2.
	 * @param testing for evading RND. CANNOT BE MOCKED.
	 */
	public PlayerButton(float x, float y, PlayerType type, int player, boolean testing) {
		super(x, y, "", testing);
		setWidth(PLAYER_SPRITE_WIDTH);
		setHeight(PLAYER_SPRITE_HEIGHT);
		setType(type);
		this.player = player;
	}
	
	/**
	 * Properly load all PlayerButton images.
	 * @throws SlickException  means player images are missing.
	 */
	public static void init() throws SlickException {
		highLightN = new Image("resources/images_UI/Menu_Highlight_Norm.png");
		highLightA = new Image("resources/images_UI/Menu_Highlight_Add.png");
		mannetjeN = new SpriteSheet("resources/images_Player/Playersprite_Norm.png",
				PLAYER_SPRITE_WIDTH, PLAYER_SPRITE_HEIGHT);
		mannetjeA = new SpriteSheet("resources/images_Player/Playersprite_Add.png",
				PLAYER_SPRITE_WIDTH, PLAYER_SPRITE_HEIGHT);
		mannetjeS = new SpriteSheet("resources/images_Player/Playersprite_Sel.png",
				PLAYER_SPRITE_WIDTH, PLAYER_SPRITE_HEIGHT);
		mannetjeS2 = new SpriteSheet("resources/images_Player/Playersprite_Sel2.png",
				PLAYER_SPRITE_WIDTH, PLAYER_SPRITE_HEIGHT);
		telefoonN = new SpriteSheet("resources/images_Player/Player2sprite_Norm.png",
				PLAYER_SPRITE_WIDTH, PLAYER_SPRITE_HEIGHT);
		telefoonA = new SpriteSheet("resources/images_Player/Player2sprite_Add.png",
				PLAYER_SPRITE_WIDTH, PLAYER_SPRITE_HEIGHT);
		telefoonS = new SpriteSheet("resources/images_Player/Player2sprite_Sel.png",
				PLAYER_SPRITE_WIDTH, PLAYER_SPRITE_HEIGHT);
		telefoonS2 = new SpriteSheet("resources/images_Player/Player2sprite_Sel2.png",
				PLAYER_SPRITE_WIDTH, PLAYER_SPRITE_HEIGHT);
		arieN = new SpriteSheet("resources/images_Player/arieSprite.png",
				PLAYER_SPRITE_WIDTH, PLAYER_SPRITE_HEIGHT);
		arieA = new SpriteSheet("resources/images_Player/arieSprite_Add.png",
				PLAYER_SPRITE_WIDTH, PLAYER_SPRITE_HEIGHT);
		arieS = new SpriteSheet("resources/images_Player/arieSprite_Sel.png",
				PLAYER_SPRITE_WIDTH, PLAYER_SPRITE_HEIGHT);
		arieS2 = new SpriteSheet("resources/images_Player/arieSprite_Sel2.png",
				PLAYER_SPRITE_WIDTH, PLAYER_SPRITE_HEIGHT);
	}
	
	/**
	 * Draw this PlayerButton to the screen.
	 * @param graphics context to draw in.
	 * @param color to draw with.
	 */
	@Override
	public void render(Graphics graphics, Color color) {
		RenderOptions ro;
		if (isSelected()) {
			ro = new RenderOptions(graphics, imageS.getSprite(2, 0), 
					imageS2.getSprite(2, 0),
					getX(), getY(), color);
		} else {
			ro = new RenderOptions(graphics, imageN.getSprite(2, 0), 
					imageA.getSprite(2, 0),
					getX(), getY(), color);
		}
		RND.getInstance().drawColor(ro);
		if (active) {
			RenderOptions h = new RenderOptions(graphics, highLightN, highLightA,
					getX(), getY(), color);
			RND.getInstance().drawColor(h);
		}
	}

	/**
	 * Set the player image type of this PlayerButton.
	 * @param type to set.
	 */
	public void setType(PlayerType type) {
		this.type = type;
		switch (type) {
			case GAMEBOY: 
				imageN = mannetjeN;
				imageA = mannetjeA;
				imageS = mannetjeS;
				imageS2 = mannetjeS2;
				spriteN = PLAYER_NORM; spriteA = PLAYER_ADD;
				break;
			case PHONE: 
				imageN = telefoonN;
				imageA = telefoonA;
				imageS = telefoonS;
				imageS2 = telefoonS2;
				spriteN = PLAYER2_NORM; spriteA = PLAYER2_ADD;
				break;
			case ARIE: 
				imageN = arieN;
				imageA = arieA;
				imageS = arieS;
				imageS2 = arieS2;
				spriteN = ARIE_NORM; spriteA = ARIE_ADD;
				break;
			default: 
				break;
		}
	}
	
	/**
	 * @return the PlayerType of this PlayerButton.
	 */
	public PlayerType getType() {
		return type;
	}
	
	/**
	 * @return this is the active player button.
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Make this the active player button. Warning -others can still be active.
	 * Use makeActive() instead!
	 * @param active boolean.
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	
	/**
	 * Make this button the only active player button in the element list.
	 */
	public void makeActive() {
		for (int i = 0; i < getList().getSize(); i++) {
			if (getList().get(i) instanceof PlayerButton) {
				PlayerButton n = (PlayerButton) getList().get(i);
				if (n.getPlayer() == player) {
					n.setActive(false);
				}
			}
		}
		this.setActive(true);
	}

	/**
	 * @return the imageN for a player.
	 */
	public SpriteSheet getImageN() {
		return imageN;
	}

	/**
	 * @return the imageA for a player.
	 */
	public SpriteSheet getImageA() {
		return imageA;
	}

	/**
	 * @return the spriteN location string.
	 */
	public String getSpriteN() {
		return spriteN;
	}

	/**
	 * @return the spriteA location string.
	 */
	public String getSpriteA() {
		return spriteA;
	}

	/**
	 * @return the player number this button belongs to.
	 */
	public int getPlayer() {
		return player;
	}
	
	
	
}
