package guimenu;
import guiobjects.Button;
import guiobjects.ElementList;
import guiobjects.PlayerButton;
import guiobjects.RND;
import guiobjects.Separator;
import logic.Logger;

import org.newdawn.slick.Color;
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
public class MenuSettingsState extends BasicGameState {

	private ElementList elements;
	private Button returnButton;
	private Button shuffleButton;
	private Button redButton;
	private Button orangeButton;
	private Button greenButton;
	private Button blueButton;
	private Button whiteButton;
	private Button pinkButton;
	private Button yellowButton;
	private PlayerButton gameboy1Button;
	private PlayerButton phone1Button;
	private PlayerButton arie1Button;
	private PlayerButton gameboy2Button;
	private PlayerButton phone2Button;
	private PlayerButton arie2Button;
	 
	// Game Colors
	private static final Color COLOR_RED = new Color(0.9f, 0.15f, 0.1f);
	private static final Color COLOR_YELLOW = new Color(0.7f, 0.65f, 0.1f);
	private static final Color COLOR_ORANGE = new Color(1.0f, 0.4f, 0.1f);
	private static final Color COLOR_GREEN = new Color(0.35f, 0.6f, 0.05f);
	private static final Color COLOR_BLUE = new Color(0.15f, 0.5f, 0.8f);
	private static final Color COLOR_PINK = new Color(0.85f, 0.0f, 0.4f);
	private static final Color COLOR_WHITE = new Color(0.5f, 0.5f, 0.5f);

	private Separator separatorTop;
	private Separator separatorMiddle;
	private Separator separatorBottom;
	private String separatorTopTitle = "";
	private String separatorMiddleTitle = " Player Controls ";
	private String separatorBottomTitle = " Settings ";
	
	private MainGame mainGame;
	private Input input;
	
	private static final int LOGO_X = 160;
	private static final int LOGO_Y = 110;
	private static final int SEPARATOR_X = 164;
	private static final int SEPARATOR_Y = 190;
	private static final int SEPARATOR_Y_2 = 338;
	private static final int SEPARATOR_Y_3 = 510;
	
	private static final int BOTTOM_TEXT_OFFSET_X = 250;
	private static final int BOTTOM_TEXT_OFFSET_Y = 75;
	
	private static final int RETURN_BUTTON_X = 164;
	private static final int RETURN_BUTTON_Y = 188;
	
	private static final int TEXT_X = 164;
	private static final int TEXT_1_Y = 238;
	private static final int TEXT_2_Y = 288;
	private static final int PLAYER_1_TEXT_Y = 590;
	private static final int PLAYER_2_TEXT_Y = 710;
	
	private static final int CONTROL_X1 = 164;
	private static final int CONTROL_XBETW = 210;
	private static final int CONTROL_X2 = 834;
	private static final int P1_CONTROL_Y = 388;
	
	private static final int COLOR_TEXT_X = 800;
	private static final int COLOR_TEXT_1_Y = 550;
	private static final int COLOR_TEXT_2_Y = 600;
	private static final int COLOR_BUTTON_1_X = 800;
	private static final int COLOR_BUTTON_2_X = 1014;
	private static final int COLOR_BUTTON_3_X = 1200;
	private static final int COLOR_BUTTON_1_Y = 653;
	private static final int COLOR_BUTTON_2_Y = 703;
	private static final int COLOR_BUTTON_3_Y = 753;
	
	private static final int MANNETJE_1_X = 370;
	private static final int MANNETJE_1_Y = 540;
	private static final int TELEFOON_1_X = 500;
	private static final int TELEFOON_1_Y = 540;
	private static final int ARIE_1_X = 630;
	private static final int ARIE_1_Y = 540;

	private static final int MANNETJE_2_X = 370;
	private static final int MANNETJE_2_Y = 660;
	private static final int TELEFOON_2_X = 500;
	private static final int TELEFOON_2_Y = 660;
	private static final int ARIE_2_X = 630;
	private static final int ARIE_2_Y = 660;
	
	private static final String PLAYERSPRITE_NORM = "Playersprite_Norm.png";
	private static final String PLAYERS = "players";
	private static final String PLAYER2SPRITE_NORM = "Player2sprite_Norm.png";
	private static final String ARIESPRITE = "arieSprite.png";
	
	/**
	 * Construct a SettingsState.
	 * @param mainGame the MainGame that uses this state.
	 */
	public MenuSettingsState(MainGame mainGame) {
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
		initElements();
		separatorTop = new Separator(SEPARATOR_X, SEPARATOR_Y, true, separatorTopTitle,
				container.getWidth());
		separatorMiddle = new Separator(SEPARATOR_X, SEPARATOR_Y_2, false, separatorMiddleTitle,
				container.getWidth());
		separatorBottom = new Separator(SEPARATOR_X, SEPARATOR_Y_3, false, separatorBottomTitle,
				container.getWidth());
	}
	
	/**
	 * Initialize the buttons.
	 * @throws SlickException if something goes wrong / file not found
	 */
	private void initElements() throws SlickException {
		elements = new ElementList();
		returnButton = new Button(RETURN_BUTTON_X, RETURN_BUTTON_Y, "< Return");
		shuffleButton = new Button(COLOR_BUTTON_1_X, COLOR_BUTTON_1_Y, "> Shuffle");
		redButton = new Button(COLOR_BUTTON_1_X, COLOR_BUTTON_2_Y, "> Red");
		orangeButton = new Button(COLOR_BUTTON_1_X, COLOR_BUTTON_3_Y, "> Orange");
		greenButton = new Button(COLOR_BUTTON_2_X, COLOR_BUTTON_1_Y, "> Green");
		blueButton = new Button(COLOR_BUTTON_2_X, COLOR_BUTTON_2_Y, "> Blue");
		whiteButton = new Button(COLOR_BUTTON_2_X, COLOR_BUTTON_3_Y, "> White");
		pinkButton = new Button(COLOR_BUTTON_3_X, COLOR_BUTTON_1_Y, "> Purple");
		yellowButton = new Button(COLOR_BUTTON_3_X, COLOR_BUTTON_2_Y, "> Yellow");
		gameboy1Button = new PlayerButton(MANNETJE_1_X, MANNETJE_1_Y, 
				PlayerButton.PlayerType.GAMEBOY, 1);
		gameboy2Button = new PlayerButton(MANNETJE_2_X, MANNETJE_2_Y, 
				PlayerButton.PlayerType.GAMEBOY, 2);
		phone1Button = new PlayerButton(TELEFOON_1_X, TELEFOON_1_Y, 
				PlayerButton.PlayerType.PHONE, 1);
		phone2Button = new PlayerButton(TELEFOON_2_X, TELEFOON_2_Y, 
				PlayerButton.PlayerType.PHONE, 2);
		arie1Button = new PlayerButton(ARIE_1_X, ARIE_1_Y, 
				PlayerButton.PlayerType.ARIE, 1);
		arie2Button = new PlayerButton(ARIE_2_X, ARIE_2_Y, 
				PlayerButton.PlayerType.ARIE, 2);
		gameboy1Button.setActive(true); phone2Button.setActive(true);
		addElements();
		coupleElements();
	}
	
	/**
	 * Add the elements to an elementlist.
	 */
	private void addElements() {
		elements.add(returnButton);
		elements.add(shuffleButton);
		elements.add(redButton);
		elements.add(orangeButton);
		elements.add(greenButton);
		elements.add(blueButton);
		elements.add(whiteButton);
		elements.add(pinkButton);
		elements.add(yellowButton);
		elements.add(gameboy1Button); elements.add(gameboy2Button);
		elements.add(phone1Button); elements.add(phone2Button);
		elements.add(arie1Button); elements.add(arie2Button);
	}
	
	/**
	 * Couple the elements in this scene together for navigation.
	 */
	private void coupleElements() {
		returnButton.setBottom(gameboy1Button);
		elements.coupleVertical(returnButton, gameboy1Button);
		elements.coupleHorizontal(returnButton, gameboy1Button);
		gameboy2Button.setLeft(returnButton);
		elements.coupleVertical(gameboy1Button, gameboy2Button);
		gameboy2Button.setBottom(returnButton);
		elements.loopVertical(phone1Button, phone2Button);
		elements.loopVertical(arie1Button, arie2Button);
		elements.coupleHorizontal(gameboy1Button, phone1Button);
		elements.coupleHorizontal(gameboy2Button, phone2Button);
		elements.coupleHorizontal(phone1Button, arie1Button);
		elements.coupleHorizontal(phone2Button, arie2Button);
		elements.coupleHorizontal(arie1Button, shuffleButton);
		redButton.setLeft(arie1Button);
		elements.coupleHorizontal(arie2Button, orangeButton);
		elements.coupleVertical(shuffleButton, redButton); 
		elements.coupleVertical(redButton, orangeButton); 
		elements.coupleVertical(orangeButton, shuffleButton); 
		elements.coupleHorizontal(shuffleButton, greenButton);
		elements.coupleHorizontal(redButton, blueButton);
		elements.coupleHorizontal(orangeButton, whiteButton);
		elements.coupleVertical(greenButton, blueButton);
		elements.coupleVertical(blueButton, whiteButton);
		elements.coupleVertical(whiteButton, greenButton);
		elements.coupleHorizontal(greenButton, pinkButton);
		elements.coupleHorizontal(blueButton, yellowButton);
		elements.loopVertical(pinkButton, yellowButton);
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
			processColorButtons(input);
		}
		exit(container, sbg, delta);
	}

	/**
	 * Process some of the buttons.
	 * @param input the keyboard/mouse input of the user
	 */
	private void processButtons(Input input) {
		if (gameboy1Button.isSelected()) {
			gameboy1Button.makeActive();
			processMannetje1Button();
		} else if (gameboy2Button.isSelected()) {
			gameboy2Button.makeActive();
			processMannetje2Button();
		} else if (phone1Button.isSelected()) {
			phone1Button.makeActive();
			processTelefoon1Button();
		} else if (phone2Button.isSelected()) {
			phone2Button.makeActive();
			processTelefoon2Button();
		} else if (arie1Button.isSelected()) {
			arie1Button.makeActive();
			processArie1Button();
		} else if (arie2Button.isSelected()) {
			arie2Button.makeActive();
			processArie2Button();
		} else if (returnButton.isSelected()) {
			processReturnButton();
		}
	}
	
	/**
	 * Process a click on the mannetje for player 1.
	 */
	private void processMannetje1Button() {
		mainGame.setPlayer1ImageString(PLAYERSPRITE_NORM, "Playersprite_Add.png");
		mainGame.getPlayerList().setPlayerImage(0, mainGame.getPlayer1ImageStringN(), 
				mainGame.getPlayer1ImageStringA());
		Logger.getInstance().log("Player 1 sprite changed to gameboy", 
				Logger.PriorityLevels.MEDIUM, PLAYERS);
	}
	
	/**
	 * Process a click on the mannetje for player 2.
	 */
	private void processMannetje2Button() {
		mainGame.setPlayer2ImageString(PLAYERSPRITE_NORM, "Playersprite_Add.png");
		mainGame.getPlayerList().setPlayerImage(1, mainGame.getPlayer2ImageStringN(), 
				mainGame.getPlayer2ImageStringA());
		Logger.getInstance().log("Player 2 sprite changed to gameboy", 
				Logger.PriorityLevels.MEDIUM, PLAYERS);
	}
	
	/**
	 * Process a click on the telefoon for player 1.
	 */
	private void processTelefoon1Button() {
		mainGame.setPlayer1ImageString(PLAYER2SPRITE_NORM, "Player2sprite_Add.png");
		mainGame.getPlayerList().setPlayerImage(0, mainGame.getPlayer1ImageStringN(), 
				mainGame.getPlayer1ImageStringA());
		Logger.getInstance().log("Player 1 sprite changed to phone", 
				Logger.PriorityLevels.MEDIUM, PLAYERS);
	}
	
	/**
	 * Process a click on the telefoon for player 2.
	 */
	private void processTelefoon2Button() {
		mainGame.setPlayer2ImageString(PLAYER2SPRITE_NORM, "Player2sprite_Add.png");
		mainGame.getPlayerList().setPlayerImage(1, mainGame.getPlayer2ImageStringN(), 
				mainGame.getPlayer2ImageStringA());
		Logger.getInstance().log("Player 2 sprite changed to phone", 
				Logger.PriorityLevels.MEDIUM, PLAYERS);
	}
	
	/**
	 * Process a click on Arie for player 1.
	 */
	private void processArie1Button() {
		mainGame.setPlayer1ImageString(ARIESPRITE, "arieSprite_Add.png");
		mainGame.getPlayerList().setPlayerImage(0, mainGame.getPlayer1ImageStringN(), 
				mainGame.getPlayer1ImageStringA());
		Logger.getInstance().log("Player 1 sprite changed to arie", 
				Logger.PriorityLevels.MEDIUM, PLAYERS);
	}
	
	/**
	 * Process a click on Arie for player 2.
	 */
	private void processArie2Button() {
		mainGame.setPlayer2ImageString(ARIESPRITE, "arieSprite_Add.png");
		mainGame.getPlayerList().setPlayerImage(1, mainGame.getPlayer2ImageStringN(), 
				mainGame.getPlayer2ImageStringA());
		Logger.getInstance().log("Player 2 sprite changed to arie", 
				Logger.PriorityLevels.MEDIUM, PLAYERS);
	}
	
	/**
	 * Process a click on the return button.
	 */
	private void processReturnButton() {
		mainGame.setSwitchState(mainGame.getMainState());
	}
	
	
	/**
	 * Process the color of the buttons.
	 * @param input the keyboard/mouse input of the user
	 */
	private void processColorButtons(Input input) {
		if (shuffleButton.isSelected()) {
			mainGame.shuffleColor(true);
			mainGame.setSwitchState(mainGame.getSettingsState());
		} else if (redButton.isSelected()) {
			mainGame.shuffleColor(false); mainGame.setNextColor(COLOR_RED);
			mainGame.setSwitchState(mainGame.getSettingsState());
		} else if (blueButton.isSelected()) {
			mainGame.shuffleColor(false); mainGame.setNextColor(COLOR_BLUE);
			mainGame.setSwitchState(mainGame.getSettingsState());
		} else if (orangeButton.isSelected()) {
			mainGame.shuffleColor(false); mainGame.setNextColor(COLOR_ORANGE);
			mainGame.setSwitchState(mainGame.getSettingsState());
		} else if (greenButton.isSelected()) {
			mainGame.shuffleColor(false); mainGame.setNextColor(COLOR_GREEN);
			mainGame.setSwitchState(mainGame.getSettingsState());
		} else if (whiteButton.isSelected()) {
			mainGame.shuffleColor(false); mainGame.setNextColor(COLOR_WHITE);
			mainGame.setSwitchState(mainGame.getSettingsState());
		} else if (pinkButton.isSelected()) {
			mainGame.shuffleColor(false); mainGame.setNextColor(COLOR_PINK);
			mainGame.setSwitchState(mainGame.getSettingsState());
		} else if (yellowButton.isSelected()) {
			mainGame.shuffleColor(false); mainGame.setNextColor(COLOR_YELLOW);
			mainGame.setSwitchState(mainGame.getSettingsState());
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
		RND.getInstance().text(graphics, TEXT_X, TEXT_1_Y, 
				"# You can choose a player skin per player by clicking on it below.");
		RND.getInstance().text(graphics, TEXT_X, TEXT_2_Y, 
				"# We advice different skins for each player but it's your choice!");

		RND.getInstance().text(graphics, TEXT_X, PLAYER_1_TEXT_Y, "> Player 1:");
		RND.getInstance().text(graphics, TEXT_X, PLAYER_2_TEXT_Y, "> Player 2:");
	
		RND.getInstance().text(graphics, container.getWidth() / 2 - BOTTOM_TEXT_OFFSET_X,
				container.getHeight() - BOTTOM_TEXT_OFFSET_Y, "Waiting for user input...");

		mainGame.drawWaterMark();
		RND.getInstance().drawLogo(graphics, LOGO_X, LOGO_Y);
		separatorTop.drawColor(graphics, mainGame.getColor());
		separatorMiddle.drawColor(graphics, mainGame.getColor());
		separatorBottom.drawColor(graphics, mainGame.getColor());
		drawControls(graphics);
		drawColorControls(graphics);
		// any and all drawing is done BEFORE THESE TWO FOR THE 1000TH TIME
		RND.getInstance().drawForeGround(graphics);
		// NO DRAWING HERE. BAD. BOO. SHOO. BEGONE.
	}
	
	/**
	 * Draw the controls for the players.
	 * @param graphics the Graphics object to draw things on screen
	 */
	private void drawControls(Graphics graphics) {
		String controlsPlayer1 = "Move left = left arrow\nMove right = right arrow\n";
		controlsPlayer1 += "Shoot weapon = spacebar";
		String controlsPlayer2 = "Move left = a\nMove right = d\nShoot weapon = w";
		
		RND.getInstance().text(graphics, CONTROL_X1, P1_CONTROL_Y, "# Player 1:");
		RND.getInstance().text(graphics, CONTROL_X1 + CONTROL_XBETW, P1_CONTROL_Y, controlsPlayer1);
		RND.getInstance().text(graphics, CONTROL_X2, P1_CONTROL_Y, "# Player 2:");
		RND.getInstance().text(graphics, CONTROL_X2 + CONTROL_XBETW, P1_CONTROL_Y, controlsPlayer2);
		
	}

	/**
	 * Draw the color of the controls.
	 * @param graphics the Graphics object to draw things on screen.
	 */
	private void drawColorControls(Graphics graphics) {
		RND.getInstance().text(graphics, COLOR_TEXT_X, COLOR_TEXT_1_Y,
				"# Change game color manually,");
		RND.getInstance().text(graphics, COLOR_TEXT_X, COLOR_TEXT_2_Y,
				"# or let it shuffle!.");
		elements.render(graphics, input, mainGame.getColor());
	}
	

	@Override
	public int getID() {
		return mainGame.getSettingsState();
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
