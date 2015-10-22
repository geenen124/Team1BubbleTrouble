package guimenu;
import guiobjects.Button;
import guiobjects.ElementList;
import guiobjects.RND;
import guiobjects.Separator;
import logic.Logger;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import sound.SoundPlayer;
import sound.SoundPlayer.MusicLists;

/**
 * This class represents the state of the settings menu.
 * @author Menno
 *
 */
public class MenuSplashState extends BasicGameState {

	private ElementList elements;
	private Button enterButton;

	private Separator separator;
	private String separatorTitle = "";
	
	private MainGame mainGame;
	private Input input;
	
	private static final int LOGO_X = 570;
	private static final int LOGO_Y = 300;
	private static final int SEPARATOR_X = 500;
	private static final int SEPARATOR_Y = 450;
	private static final int BOTTOM_TEXT_OFFSET_X = 250;
	private static final int BOTTOM_TEXT_OFFSET_Y = 75;
	private static final int BUTTON_X = 120, BUTTON_Y = 500;
	private static final int TEXT_X = 590, TEXT_Y = 400;
	
	/**
	 * Construct a SettingsState.
	 * @param mainGame the MainGame that uses this state.
	 */
	public MenuSplashState(MainGame mainGame) {
		this.mainGame = mainGame;
	}
	
	/**
	 * setup all variables when entering this state.
	 * @param container the Container this state is part of
	 * @param arg1 The statebasedgame this state is part of
	 * @throws SlickException sometimes.
	 */
	@Override
	public void enter(GameContainer container, StateBasedGame arg1) throws SlickException {
		Logger.getInstance().log("Entering MenuSettingsState", 
				Logger.PriorityLevels.LOW, "States");
		elements.reset();
		RND.getInstance().setOpacity(0.0f);
		mainGame.stopSwitchState();

		SoundPlayer.getInstance().setActiveList(MusicLists.MENU_LIST);
	}
	
	/**
	 * Exit function for state. Fades out and everything.
	 * @param container the GameContainer we are running in
	 * @param sbg the gamestate cont.
	 * @param delta the deltatime in ms
	 */
	public void exit(GameContainer container, StateBasedGame sbg, int delta) {
		if (mainGame.getShouldSwitchState()) {
			if (RND.getInstance().getOpacity() > 0.0f) {
				RND.getInstance().setOpacity(RND.getInstance().getOpacity() 
						- ((float) delta) / mainGame.getOpacityFadeTimer());
			} else {
				Logger.getInstance().log("Exiting MenuSettingsState", 
						Logger.PriorityLevels.LOW, "States");
				if (mainGame.getSwitchState() == -1) {
					container.exit();
				} else {
					mainGame.switchColor();
					sbg.enterState(mainGame.getSwitchState());
				}
			}	
		}
	}
	
	/**
	 * Initialize this state.
	 * @param container the GameContainer that contains this state
	 * @param arg1 the state based game that uses this state
	 * @throws SlickException if something goes wrong
	 */
	public void init(GameContainer container, StateBasedGame arg1) throws SlickException {
		enterButton = new Button(container.getWidth() / 2 - BUTTON_X, BUTTON_Y, "> Enter game <");
		System.out.println("DADADA" + enterButton.getWidth());
		initElements();
		separator = new Separator(SEPARATOR_X, SEPARATOR_Y, false, separatorTitle,
				container.getWidth());
	}
	
	/**
	 * Initialize the buttons.
	 * @throws SlickException if something goes wrong / file not found
	 */
	private void initElements() throws SlickException {
		elements = new ElementList();
		elements.add(enterButton);
	}
	
	/**
	 * Update this state.
	 * @param container The gamecontainer that contains this state
	 * @param sbg the state based game that uses this state
	 * @param delta the time in ms since the last frame
	 * @throws SlickException if something goes wrong
	 */
	public void update(GameContainer container, StateBasedGame sbg, int delta) 
			throws SlickException {
		
		if (RND.getInstance().getOpacity() < 1.0f && !mainGame.getShouldSwitchState()) {
			RND.getInstance().setOpacity(RND.getInstance().getOpacity() 
					+ ((float) delta) / mainGame.getOpacityFadeTimer());
		}
		
		input = container.getInput();
		elements.update(input);
		if ((input.isMousePressed(Input.MOUSE_LEFT_BUTTON) 
				|| (input.isKeyDown(Input.KEY_ENTER) && input.isKeyPressed(Input.KEY_ENTER))) 
				&& !mainGame.getShouldSwitchState()) {
			processButtons(input);
		}
		exit(container, sbg, delta);
	}

	/**
	 * Process some of the buttons.
	 * @param input the keyboard/mouse input of the user
	 */
	private void processButtons(Input input) {
		if (enterButton.isSelected()) {
			mainGame.setSwitchState(mainGame.getMainState());
		}
	}
	
	/**
	 * Render this state.
	 * @param container the Gamecontainer that contains this state
	 * @param arg1 the state based game that uses this state
	 * @param graphics the Graphics object used in this state
	 * @throws SlickException if something goes wrong.
	 */
	public void render(GameContainer container, StateBasedGame arg1, Graphics graphics)
			throws SlickException {
		this.input = container.getInput();
		
		RND.getInstance().drawBackground(graphics);

		//mainGame.drawWaterMark();
		RND.getInstance().drawLogo(graphics, LOGO_X, LOGO_Y);
		separator.drawColor(graphics, mainGame.getColor());
		RND.getInstance().textSpecifiedColor(graphics, TEXT_X, TEXT_Y, 
				"Welcome to Bubble Trouble!", mainGame.getColor());

		RND.getInstance().text(graphics, container.getWidth() / 2 - BOTTOM_TEXT_OFFSET_X,
				container.getHeight() - BOTTOM_TEXT_OFFSET_Y, "Waiting for user input...");
		elements.render(graphics, input, mainGame.getColor());
		// any and all drawing is done BEFORE THESE TWO FOR THE 1000TH TIME
		RND.getInstance().drawForeGround(graphics);
		// NO DRAWING HERE. BAD. BOO. SHOO. BEGONE.
	}
	

	@Override
	public int getID() {
		return mainGame.getSplashState();
	}

	/**
	 * @return the mainGame
	 */
	public MainGame getmainGame() {
		return mainGame;
	}

	/**
	 * @param mainGame the mainGame to set
	 */
	public void setmainGame(MainGame mainGame) {
		this.mainGame = mainGame;
	}
	
	
}
