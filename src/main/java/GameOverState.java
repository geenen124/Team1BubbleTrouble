import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Class that represents the state of the game when the game is over.
 * @author Menno
 *
 */
public class GameOverState extends BasicGameState {

	private Button playAgainButton;
	private MainGame mg;
	private TextField tf;
	private Image tfBackground;
	private String inputMessage;
	
	private static final int PLAY_AGAIN_BUTTON_X = 300;
	private static final int PLAY_AGAIN_BUTTON_Y = 475;
	private static final int PLAY_AGAIN_BUTTON_WIDTH = 200;
	private static final int PLAY_AGAIN_BUTTON_HEIGHT = 45;
	
	private static final int GAME_OVER_X = 100;
	private static final int GAME_OVER_Y = 100;
	private static final int POINTS_X = 100;
	private static final int POINTS_Y = 140;
	private static final int NAME_X = 100;
	private static final int NAME_Y = 180;
	private static final int TEXT_FIELD_X = 115;
	private static final int TEXT_FIELD_Y = 245;
	private static final int TEXT_FIELD_WIDTH = 700;
	private static final int TEXT_FIELD_HEIGHT = 60;
	private static final int TF_BACKGROUND_DEVIATION = 27;
	private static final int INPUT_MESSAGE_X = 100;
	private static final int INPUT_MESSAGE_Y = 320;
	
	private static final int HIGHSCORES_X = 1000;
	private static final int HIGHSCORES_Y = 100;
	
	/**
	 * Constructor.
	 * @param mg the maingame in which this state will be used.
	 */
	public GameOverState(MainGame mg) {
		this.mg = mg;
	}
	
	/**
	 * Init method - load resources here.
	 * @param container The container this state should be initialized in.
	 * @param arg1 the StateBasedGame this state is in.
	 * @throws SlickException idk when
	 */
	public void init(GameContainer container, StateBasedGame arg1)
			throws SlickException {

		playAgainButton = new Button(PLAY_AGAIN_BUTTON_X, PLAY_AGAIN_BUTTON_Y,
				PLAY_AGAIN_BUTTON_WIDTH, PLAY_AGAIN_BUTTON_HEIGHT,
				new Image("resources/play_again_button.png"));
		tfBackground = new Image("resources/textfield.png");
			}
	
	@Override
	public void enter(GameContainer container, StateBasedGame sbg) {
		tf = new TextField(container, mg.getDosFont(), TEXT_FIELD_X, TEXT_FIELD_Y,
				TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);
		tf.setBackgroundColor(null);
		tf.setBorderColor(null);
		tf.setFocus(true);
		inputMessage = null;

	}
	
	/**
	 * update method - called every frame.
	 * @param container The container this state is in.
	 * @param sbg the StateBasedGame this state is in
	 * @param delta the time in ms since the last frame
	 * @throws SlickException sometimes.
	 */
	public void update(GameContainer container, StateBasedGame sbg, int delta)
			throws SlickException {
			Input input = container.getInput();
			
			// If the mouse is pressed inside the playAgainButton, enter the gameState
			if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				if (playAgainButton.getRectangle().contains(input.getMouseX(), input.getMouseY())) {
					mg.resetLifeCount();
					mg.resetLevelCount();
					sbg.enterState(1);
				}
			}
			
			if (tf.hasFocus() && input.isKeyPressed(Input.KEY_ENTER)) {
				Score score = new Score(mg.getScore(), tf.getText());
				mg.getHighscores().add(score);
				HighScoresParser.writeHighScores(mg.getHighscoresFile(), mg.getHighscores());
				
				inputMessage = tf.getText() + ", your score of " + mg.getScore();
				inputMessage += " points is saved!";
			}
		}

	/**
	 * Render method - draw things to screen.
	 * @param container The container this state is in
	 * @param arg1 the statebasedgame this state is in.
	 * @param graphics the Graphics used in this gameoverstate.
	 * @throws SlickException idk when
	 */
	public void render(GameContainer container, StateBasedGame arg1, Graphics graphics)
			throws SlickException {
		// draw string and button

		// draw background
		graphics.drawImage(mg.getBackgroundImage(), 0, 0);

		mg.getDosFont().drawString(GAME_OVER_X, GAME_OVER_Y , ">Game Over Sucker!");
		mg.getDosFont().drawString(POINTS_X, POINTS_Y, ">Your score was: " + mg.getScore());
		mg.getDosFont().drawString(NAME_X, NAME_Y, ">Please enter your name below");
		graphics.drawImage(playAgainButton.getImage(), playAgainButton.getX(), 
				playAgainButton.getY());
		graphics.drawImage(tfBackground, tf.getX() - TF_BACKGROUND_DEVIATION, 
				tf.getY() - TF_BACKGROUND_DEVIATION);
		tf.render(container, graphics);
		
		if (inputMessage != null) {
			mg.getDosFont().drawString(INPUT_MESSAGE_X, INPUT_MESSAGE_Y, inputMessage);
		}

		// draw foreground
		graphics.drawImage(mg.getForeGroundImage(), 0, 0);
		graphics.drawImage(mg.getTerminalImage(), 0, 0);
		
		String highScoresString = mg.getHighscores().toString();
		mg.getDosFont().drawString(HIGHSCORES_X, HIGHSCORES_Y, highScoresString);
	}

	
	
	/**
	 * returns id of the state.
	 */
	@Override
	public int getID() {
		return 2;
	}
}
