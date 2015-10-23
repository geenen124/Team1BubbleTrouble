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

import sound.MenuTransitionSoundEffect;
import sound.SoundPlayer;
import sound.SoundPlayer.MusicLists;

/**
 * This class represents the state of the start menu.
 * @author Menno
 *
 */
public class MenuMainState extends BasicGameState {

	 
	private MainGame mainGame;

	private ElementList elements;
	private Button playButton;
	private Button play2Button;
	private Button lanButton;
	private Button optionsButton;
	private Button quitButton;
	
	private static final int LOGO_X = 160;
	private static final int LOGO_Y = 110;
	private static final int SEPARATOR_X = 164;
	private static final int SEPARATOR_Y = 190;
	
	private static final int BOTTOM_TEXT_OFFSET_X = 250;
	private static final int BOTTOM_TEXT_OFFSET_Y = 75;
	
	private static final int BUTTON_WIDTH = 1000;
	private static final int BUTTON_HEIGHT = 50;
	private static final int BUTTON_X = 164;
	private static final int PLAYBUTTON_Y = 238;
	private static final int PLAYBUTTON2_Y = 288;
	private static final int PLAYBUTTONLAN_Y = 338;
	private static final int OPTIONSBUTTON_Y = 388;
	private static final int QUITBUTTON_Y = 438;
	private static final int HIGHSCORES_X = 900;
	private static final int HIGHSCORES_Y = 288;
	private static final int HIGHSCORES_TITLE_X = 760;
	private static final int HIGHSCORES_TITLE_Y = 238;
	
	private static final int EXIT_FACTOR = 8;
	
	private static final String USER_INPUT = "user-input";
	
	private Separator separatorTop;
	private String separatorTopTitle = "";
	
	/**
	 * constructor.
	 * 
	 * @param mainGame - the maingame this state belongs to
	 */
	public MenuMainState(MainGame mainGame) {
		this.mainGame = mainGame;
	}
	
	/**
	 * Initialize this state.
	 * @param container The container that contains this state
	 * @param arg1 the State based game that uses this state
	 * @throws SlickException if something goes wrong
	 */
	public void init(GameContainer container, StateBasedGame arg1) throws SlickException {
		separatorTop = new Separator(SEPARATOR_X, SEPARATOR_Y, false, separatorTopTitle,
				container.getWidth());
		elements = new ElementList();
		playButton = new Button(BUTTON_X, PLAYBUTTON_Y, "> Play 1-player game");
		play2Button = new Button(BUTTON_X, PLAYBUTTON2_Y, "> Play 2-player game");
		lanButton = new Button(BUTTON_X, PLAYBUTTONLAN_Y, "> Play LAN game");
		optionsButton = new Button(BUTTON_X, OPTIONSBUTTON_Y, "> Options");
		quitButton = new Button(BUTTON_X, QUITBUTTON_Y, "> Quit");
		elements.add(playButton);
		elements.add(play2Button);
		elements.add(lanButton);
		elements.add(optionsButton);
		elements.add(quitButton);
		elements.coupleVertical(playButton, play2Button);
		elements.coupleVertical(play2Button, lanButton);
		elements.coupleVertical(lanButton, optionsButton);
		elements.coupleVertical(optionsButton, quitButton);
		elements.coupleVertical(quitButton, playButton);
	}
	
	/**
	 * setup all variables when entering this state.
	 * @param container the Container this state is part of
	 * @param arg1 The statebasedgame this state is part of
	 * @throws SlickException sometimes.
	 */
	@Override
	public void enter(GameContainer container, StateBasedGame arg1) throws SlickException {
		Logger.getInstance().log("Entering MenuMainState", Logger.PriorityLevels.LOW, "States");
		RND.getInstance().setOpacity(0.0f);
		elements.reset();
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
				int fadeTimer = mainGame.getOpacityFadeTimer();
				if (mainGame.getSwitchState() == -1) {
					fadeTimer = EXIT_FACTOR * fadeTimer;
				}
				RND.getInstance().setOpacity(RND.getInstance().getOpacity() 
						- ((float) delta) / fadeTimer);
			} else {
				Logger.getInstance().log("Exiting MenuMainState", 
						Logger.PriorityLevels.LOW, "States");
				if (mainGame.getSwitchState() == -1) {
					System.exit(0);
				} else {
					mainGame.switchColor();
					sbg.enterState(mainGame.getSwitchState());
				}
			}	
		}
	}
	
	/**
	 * Update this state.
	 * @param container The container that contains this state
	 * @param sbg the state based game that uses this state
	 * @param delta the time in ms since the last frame
	 * @throws SlickException if something goes wrong
	 */
	public void update(GameContainer container, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = container.getInput();
		if (RND.getInstance().getOpacity() < 1.0f && !mainGame.getShouldSwitchState()) {
			RND.getInstance().setOpacity(RND.getInstance().getOpacity() 
					+ ((float) delta) / mainGame.getOpacityFadeTimer());
		}

		elements.update(input);

		if ((input.isMousePressed(Input.MOUSE_LEFT_BUTTON) 
				|| (input.isKeyDown(Input.KEY_ENTER) && input.isKeyPressed(Input.KEY_ENTER)))
				&& !mainGame.getShouldSwitchState()) {
			processButtonsMouse(input);
		}

		SoundPlayer.getInstance().playEffects();
		exit(container, sbg, delta);
	}
	
	/**
	 * Process mouse input concerning the buttons.
	 * @param input the keyboard/mouse input of the user.
	 */
	private void processButtonsMouse(Input input) {
		boolean playSFX = true;
		if (playButton.isSelected()) { // Go to gamestate in singleplayer
			processPlayButton();
		} else if (play2Button.isSelected()) { // Go to gamestate in multiplayer
			processTwoPlayerPlayButton();
		} else if (lanButton.isSelected()) { // Go to gamestate in multiplayer
			processLanButton();
		} else if (optionsButton.isSelected()) { // Go to settingsState
			processOptionsButton();
		} else if (quitButton.isSelected()) { // Quit game
			processExitButton();
		} else {
			playSFX = false;
		}

		if (playSFX) {
			SoundPlayer.getInstance().addEffect(new MenuTransitionSoundEffect(false));
		}
	}
	
	/**
	 * Process a click on the play button.
	 */
	private void processPlayButton() {
		mainGame.setMultiplayer(false);
		mainGame.setSwitchState(mainGame.getGameState());
		mainGame.getPlayerList().getPlayers().get(0).setControlsForPlayer1();
		Logger.getInstance().log("Play button pressed", 
				Logger.PriorityLevels.MEDIUM, USER_INPUT);
	}
	
	/**
	 * Process a click on the two player play button.
	 */
	private void processTwoPlayerPlayButton() {
		mainGame.setMultiplayer(true);
		mainGame.setSwitchState(mainGame.getGameState());
		mainGame.getPlayerList().getPlayers().get(0).setPlayerName("PLayer_1");
		mainGame.getPlayerList().getPlayers().get(1).setPlayerName("PLayer_2");
		mainGame.getPlayerList().getPlayers().get(0).setControlsForPlayer1();
		mainGame.getPlayerList().getPlayers().get(1).setControlsForPlayer2();
		Logger.getInstance().log("Play multiplayer button pressed", 
				Logger.PriorityLevels.MEDIUM, USER_INPUT);
	}
	
	/**
	 * Process a click on the lan button.
	 */
	private void processLanButton() {
		mainGame.setSwitchState(mainGame.getMultiplayerState());
		Logger.getInstance().log("Play lan button pressed", 
				Logger.PriorityLevels.MEDIUM, USER_INPUT);
	}
	
	/**
	 * Process a click on the options button.
	 */
	private void processOptionsButton() {
		mainGame.setSwitchState(mainGame.getSettingsState());
		Logger.getInstance().log("options button pressed", 
				Logger.PriorityLevels.MEDIUM, USER_INPUT);
	}
	
	/**
	 * Process a click on the exit button.
	 */
	private void processExitButton() {
		mainGame.setSwitchState(-1);
		Logger.getInstance().log("quit button pressed", 
				Logger.PriorityLevels.MEDIUM, USER_INPUT);
	}

	/**
	 * Render this state.
	 * @param container The gamecontainer that contains this state
	 * @param arg1 the state based game that contains this state
	 * @param graphics The Graphics used to draw things in this state
	 * @throws SlickException if something goes wrong
	 */
	public void render(GameContainer container, StateBasedGame arg1, Graphics graphics) 
			throws SlickException {
		RND.getInstance().drawBackground(graphics);
		RND.getInstance().text(graphics, container.getWidth() / 2 - BOTTOM_TEXT_OFFSET_X,
				container.getHeight() - BOTTOM_TEXT_OFFSET_Y, "Waiting for user input...");
		renderButtons(container, graphics);
		mainGame.drawWaterMark();
		RND.getInstance().drawLogo(graphics, LOGO_X, LOGO_Y);
		separatorTop.drawColor(graphics, mainGame.getColor());
		String highScoresString = mainGame.getHighscores().toString();
		RND.getInstance().text(graphics, HIGHSCORES_X, HIGHSCORES_Y, highScoresString);
		RND.getInstance().text(graphics, HIGHSCORES_TITLE_X, HIGHSCORES_TITLE_Y, 
				"The best scores of your predecessors!");
		// NO DRAWING AFTER THIS POINT UNLESS YOUR NAME STARTS WITH AN M. AND NOT M-E. BOO.
		RND.getInstance().drawForeGround(graphics);
		// NO DRAWING HERE. BAD PROGRAMMER. BAD.
	}

	/**
	 * Method renders buttons in StartState to screen.
	 * @param container the GameContainer used
	 * @param graphics graphics context used
	 */
	private void renderButtons(GameContainer container, Graphics graphics) {
		Input input = container.getInput();
		elements.render(graphics, input, mainGame.getColor());
	}

	@Override
	public int getID() {
		return 0;
	}
	
	/**
	 * Get the main game.
	 * @return the maingame
	 */
	public MainGame getMainGame() {
		return mainGame;
	}
}
