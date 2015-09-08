import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SpriteSheet;


public class Player {
	//Method 1 code
	//int lifeCount;
	private float x;
	private float y;
	private float width;
	private float height;
	private int movement = 0;
	private int movementCounter = 0;
	private int movementCounter_max = 18;
	private Image image;
	private SpriteSheet spritesheet;
	private boolean freeToRoam;
	private MainGame mg;
	private GameState gs;
	private Gate intersectingGate;
	
	
	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param image
	 */
	public Player(float x, float y, float width, float height, Image image, MainGame mg) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.image = image;
		this.spritesheet = new SpriteSheet(image, 120, 120);
		this.mg = mg;
		this.gs = (GameState) mg.getState(mg.GAME_STATE);
	}
	
	public void update(float deltaFloat) {
		processGates();
		processWeapon(mg.getContainer(), deltaFloat);
		processPlayerMovement(mg.getContainer(), deltaFloat);
	}
	
	private void processGates() {
		// Check the intersection of a player with a gate
		freeToRoam = true;
		for(Gate someGate :gs.gateList) {
			if(this.getRectangle().intersects(someGate.getRectangle())) {
				freeToRoam = false;
				intersectingGate = someGate;
			}
		}
		// Reset intersecting gate to none if there is no intersection
		if(freeToRoam) {
			intersectingGate = null;
		}
	}

	private void processWeapon(GameContainer container, float deltaFloat) {
		// Shoot laser when spacebar is pressed and no laser is active
		if (gs.input.isKeyPressed(Input.KEY_SPACE) && !gs.shot) {
            gs.shot = true;
            float x = this.getCenterX();
            gs.laser = new Laser(x, container.getHeight() - gs.floor.getHeight(), mg.laserSpeed, mg.laserWidth);
        }

		// Update laser
		if (gs.shot) {
            gs.laser.update(gs, deltaFloat);
            // Disable laser when it has reached the ceiling
            if (!gs.laser.visible) {
                gs.shot = false;
            }
        }
	}
	
	private void processPlayerMovement(GameContainer container, float deltaFloat) {
		// Walk left when left key pressed and not at left wall OR a gate
		if (gs.input.isKeyDown(Input.KEY_LEFT) && this.getX() > gs.leftWall.getWidth()) {
            if(freeToRoam || (this.getCenterX() < intersectingGate.getRectangle().getCenterX())) {
            	this.setX(this.getX() - mg.playerSpeed * deltaFloat);
            	this.movement = 1;
            }
        }

		// Walk right when right key pressed and not at right wall OR a gate
		if (gs.input.isKeyDown(Input.KEY_RIGHT) && this.getMaxX() < (container.getWidth() - gs.rightWall.getWidth())) {
           if(freeToRoam || (this.getCenterX() > intersectingGate.getRectangle().getCenterX())) {
        	   this.setX(this.getX() + mg.playerSpeed * deltaFloat);
        	   this.movement = 2;
           }
        }
	}

	/**
	 * Return a bounding box rectangle of the player
	 * @return
	 */
	public MyRectangle getRectangle() {
		return new MyRectangle(x,y,width,height);
	}
	
	/**
	 * Get the center X coordinate
	 * @return
	 */
	public float getCenterX() {
		return x + (0.5f * width);
	}
	
	/**
	 * Get the center Y coordinate
	 * @return
	 */
	public float getCenterY() {
		return y + (0.5f * height);
	}
	
	/**
	 * Get the maximum x value
	 * @return
	 */
	public float getMaxX() {
		return x + width;
	}
	
	/**
	 * Get the maximum Y value
	 * @return
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
	 * @return the player spritesheet
	 */
	public SpriteSheet getSpritesheet() {
		return spritesheet;
	}
	/**
	 * @param Spritesheet the spritesheet to set
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
	 * increment the movement counter used for spritesheets
	 */
	public void incrementMovementCounter() {
		movementCounter++;
		if(movementCounter > movementCounter_max) {
			resetMovementCounter();
		}
	}
	
	/**
	 * reset the movement counter used for spritesheets
	 */
	public void resetMovementCounter() {
		movementCounter = 0;
	}
	
	/**
	 * @return movementCounter_max get the movement counter maximum used for spritesheets
	 */
	public int getMovementCounter_Max() {
		return movementCounter_max;
	}
	
	/**
	 * @param MovementCounter_max set the movement counter maximum used for spritesheets
	 */
	public void setMovementCounter_Max(int newMax) {
		movementCounter_max = newMax;
	}
	
	//Method 1 code
//	public void decreaselifeCount() {
//		lifeCount = lifeCount -1;
//	}
//	
//	public int getLifeCount() {
//		return lifeCount;
//	}
}
