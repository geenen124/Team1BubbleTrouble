import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SpriteSheet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * This class represents a Player.
 * @author Menno
 *
 */
public class Player {
	//Method 1 code
	//int lifeCount;

	private boolean shield;
	private float x;
	private float y;
	private float width;
	private float height;
	private int movement = 0;
	private int movementCounter = 0;
	private int movementCounterMax = DEFAULT_MOVEMENTCOUNTER_MAX;
	private Image image;
	private SpriteSheet spritesheet;
	private boolean freeToRoam;
	private GameState gs;
	private Gate intersectingGate;
	// weapon management
	private LinkedList<Powerup.PowerupType> weapons;
	
	//Kick Container
	private MainGame mg;

	private static final int DEFAULT_MOVEMENTCOUNTER_MAX = 18;
	private static final int SPRITESHEET_VALUE = 120;
	private static final float HALF = 0.5f;
	private int moveLeftKey;
	private int moveRightKey;
	private int shootKey;
	
	private static final int POWERUP_DURATION = 10;

	/**
	 * @param x the x coordinate of the player
	 * @param y the y coordinate of the player
	 * @param width the width of the player
	 * @param height the height of the player
	 * @param image the image used on the player
	 * @param mg the MainGame within the player is
	 */
	public Player(float x, float y, float width, float height, Image image, MainGame mg) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.image = image;
		this.spritesheet = new SpriteSheet(image, SPRITESHEET_VALUE, SPRITESHEET_VALUE);
		moveLeftKey = Input.KEY_LEFT;
		moveRightKey = Input.KEY_RIGHT;
		shootKey = Input.KEY_SPACE;
		this.weapons = new LinkedList<>();
		this.shield = false;
		
		this.mg = mg;
		this.gs = (GameState) mg.getState(mg.getGameState());
	}
	
	/**
	 * Update this player.
	 * @param deltaFloat the time in ms since the last frame.
	 */
	public void update(float deltaFloat) {
		processGates();
		processWeapon(deltaFloat);
		processPlayerMovement(deltaFloat);
		processPowerups(deltaFloat);
	}
	
	/**
	 * Process gates.
	 */
	public void processGates() {
		// Check the intersection of a player with a gate
		freeToRoam = true;
		for (Gate someGate :gs.getGateList()) {
			if (this.getRectangle().intersects(someGate.getRectangle())) {
				freeToRoam = false;
				intersectingGate = someGate;
			}
		}
		// Reset intersecting gate to none if there is no intersection
		if (freeToRoam) {
			intersectingGate = null;
		}
	}
	
	/**
	 * 
	 * @param deltaFloat the delta
	 */
	public void processPowerups(float deltaFloat) {
		ArrayList<Powerup> usedPowerups = new ArrayList<>();
		for (Powerup powerup : gs.getDroppedPowerups()) {
			powerup.update(gs, mg.getContainer(), deltaFloat);

			if (powerup.getRectangle().intersects(this.getRectangle())) {
				this.addPowerup(powerup.getType());
				usedPowerups.add(powerup);
			}
		}

		for (Powerup used : usedPowerups) {
			gs.getDroppedPowerups().remove(used);
		}
	}

	/**
	 * 
	 * @param deltaFloat the delta
	 */
	public void processWeapon(float deltaFloat) {
		// Shoot laser when spacebar is pressed and no laser is active
		if (gs.getSavedInput().isKeyPressed(shootKey)
				&& (!gs.isShot() || (gs.getWeapon().getClass() == Spiky.class))) {
			gs.setShot(true);
			gs.setWeapon(this.getWeapon());
		}

		// Update laser
		if (gs.isShot()) {
			gs.getWeapon().update(gs, deltaFloat);
			// Disable laser when it has reached the ceiling
			if (!gs.getWeapon().isVisible()) {
				gs.setShot(false);
			}
		}
	}
	
	/**
	 * 
	 * @param deltaFloat the delta
	 */
	public void processPlayerMovement(float deltaFloat) {
		// Walk left when left key pressed and not at left wall OR a gate
		boolean isKeyLeft = gs.getSavedInput().isKeyDown(moveLeftKey);
		if (isKeyLeft && this.getX() > gs.getLeftWall().getWidth()) {
            if (freeToRoam || (this.getCenterX() < intersectingGate.getRectangle().getCenterX())) {
            	this.setX(this.getX() - mg.getPlayerSpeed() * deltaFloat);
            	this.movement = 1;
            }
        }

		// Walk right when right key pressed and not at right wall OR a gate
		if (gs.getSavedInput().isKeyDown(moveRightKey) && this.getMaxX()
				< (mg.getContainerWidth() - gs.getRightWall().getWidth())) {
           if (freeToRoam || (this.getCenterX() > intersectingGate.getRectangle().getCenterX())) {
        	   this.setX(this.getX() + mg.getPlayerSpeed() * deltaFloat);
        	   this.movement = 2;
           }
        }
	}
	
	/**
	 * 
	 * @return the weapon
	 */
	public Weapon getWeapon() {
		if (weapons.isEmpty()) {
			return new Weapon(this.getCenterX(), mg.getContainerHeight()
					- gs.getFloor().getHeight(),
					mg.getLaserSpeed(), mg.getLaserWidth());
		}
		Powerup.PowerupType subType = weapons.peekLast();
		if (subType == Powerup.PowerupType.SPIKY) {
			return new Spiky(this.getCenterX(), mg.getContainerHeight() - gs.getFloor().getHeight(),
					mg.getLaserSpeed(), mg.getLaserWidth());
		}
		if (subType == Powerup.PowerupType.INSTANT) {
			return new InstantLaser(this.getCenterX(),
					mg.getContainerHeight() - gs.getFloor().getHeight(),
					mg.getLaserWidth());
		}
		// Wrong weapon type, time to crash hard.
		throw new EnumConstantNotPresentException(Powerup.PowerupType.class, subType.toString());
	}

	/**
	 * Return a bounding box rectangle of the player.
	 * @return a bounding box rectangle
	 */
	public MyRectangle getRectangle() {
		return new MyRectangle(x, y, width, height);
	}
	
	/**
	 * Get the center X coordinate.
	 * @return the center x
	 */
	public float getCenterX() {
		return x + (HALF * width);
	}
	
	/**
	 * Get the center Y coordinate.
	 * @return the center Y
	 */
	public float getCenterY() {
		return y + (HALF * height);
	}
	
	/**
	 * Get the maximum x value.
	 * @return the maximum x.
	 */
	public float getMaxX() {
		return x + width;
	}
	
	/**
	 * Get the maximum Y value.
	 * @return the maximum Y.
	 */
	public float getMaxY() {
		return y + height;
	}
	
	
	
	
	/**
	 * @return the x
	 */
	public float getX() {
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(float x) {
		this.x = x;
	}
	/**
	 * @return the y
	 */
	public float getY() {
		return y;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(float y) {
		this.y = y;
	}
	
	/**
	 * @return the width
	 */
	public float getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(float width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public float getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(float height) {
		this.height = height;
	}

	
	/**
	 * @return the player image
	 */
	public Image getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	/**
	 * @return Whether or not the player has a shield
	 */
	public boolean hasShield() {
		return shield;
	}

	/**
	 * Add a powerup to the player.
	 * @param type powerup type
	 */
	public void addPowerup(Powerup.PowerupType type) {
		if (type == Powerup.PowerupType.INSTANT) {
			addWeapon(type);
		}
		if (type == Powerup.PowerupType.SHIELD) {
			addShield();
		}
		if (type == Powerup.PowerupType.SPIKY) {
			addWeapon(type);
		}
	}

	private void addShield() {
		shield = true;
		Executors.newScheduledThreadPool(1).schedule(() -> shield = false,
				POWERUP_DURATION, TimeUnit.SECONDS);
	}

	private void addWeapon(Powerup.PowerupType type) {
		weapons.add(type);
		Executors.newScheduledThreadPool(1).schedule(() -> weapons.removeFirst(),
				POWERUP_DURATION, TimeUnit.SECONDS);
	}
	
	/**
	 * @return the player spritesheet
	 */
	public SpriteSheet getSpritesheet() {
		return spritesheet;
	}
	/**
	 * @param spritesheet the spritesheet to set.
	 */
	public void setSpritesheet(SpriteSheet spritesheet) {
		this.spritesheet = spritesheet;
	}
	
	/**
	 * @param movement the movement integer used to determine movement state. 
	 */
	public void setMovement(int movement) {
		this.movement = movement;
	}
	
	/**
	 * @return the current movement integer used to determine movement 
	 */
	public int getMovement() {
		return movement;
	}
	
	/**
	 * @return movement counter for spritesheets
	 */
	public int getMovementCounter() {
		return movementCounter;
	}
	
	/**
	 * increment the movement counter used for spritesheets.
	 */
	public void incrementMovementCounter() {
		movementCounter++;
		if (movementCounter > movementCounterMax) {
			resetMovementCounter();
		}
	}
	
	/**
	 * reset the movement counter used for spritesheets.
	 */
	public void resetMovementCounter() {
		movementCounter = 0;
	}
	
	/**
	 * @return movementCounter_max get the movement counter maximum used for spritesheets
	 */
	public int getMovementCounter_Max() {
		return movementCounterMax;
	}
	
	/**
	 * @param newMax set the movement counter maximum used for spritesheets
	 */
	public void setMovementCounter_Max(int newMax) {
		movementCounterMax = newMax;
	}

	/**
	 * @return the gs
	 */
	public GameState getGs() {
		return gs;
	}

	/**
	 * @param gs the gs to set
	 */
	public void setGs(GameState gs) {
		this.gs = gs;
	}

	/**
	 * @return the moveLeftKey
	 */
	public int getMoveLeftKey() {
		return moveLeftKey;
	}

	/**
	 * @param moveLeftKey the moveLeftKey to set
	 */
	public void setMoveLeftKey(int moveLeftKey) {
		this.moveLeftKey = moveLeftKey;
	}

	/**
	 * @return the moveRightKey
	 */
	public int getMoveRightKey() {
		return moveRightKey;
	}

	/**
	 * @param moveRightKey the moveRightKey to set
	 */
	public void setMoveRightKey(int moveRightKey) {
		this.moveRightKey = moveRightKey;
	}

	/**
	 * @return the shootKey
	 */
	public int getShootKey() {
		return shootKey;
	}

	/**
	 * @param shootKey the shootKey to set
	 */
	public void setShootKey(int shootKey) {
		this.shootKey = shootKey;
	}
}
