package guimenu;

import guigame.GameState;
import guiobjects.Button;
import guiobjects.ElementList;
import guiobjects.Popup;
import guiobjects.RND;
import guiobjects.Separator;
import guiobjects.Textfield;

import java.net.InetAddress;
import java.net.UnknownHostException;

import lan.Client;
import lan.Host;
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
 * This class represents the state of the multiplayer menu.
 * @author Mark
 *
 */
public class MenuMultiplayerState extends BasicGameState {

	private ElementList elements;
	
	private Button returnButton;
	private Button hostButton;
	private Button joinButton;

	private MainGame mainGame;
	private GameState gameState;
	private Input input;
	
	private Popup popup;
	
	private Textfield nameField;
	private Textfield ipField;
	
	private Separator separatorTop;
	private String separatorTopTitle = "";
	private Separator separatorHost;
	private String separatorHostTitle = " Host Game ";
	private Separator separatorJoin;
	private String separatorJoinTitle = " Join Game ";
	private Separator separatorMisc;
	private String separatorMiscTitle = " Miscellaneous ";

	private String message;
	
	private static final int LOGO_X = 160;
	private static final int LOGO_Y = 110;
	private static final int SEPARATOR_X = 164;
	private static final int SEPARATOR_Y = 190;
	private static final int SEPARATOR_Y_2 = 388;
	private static final int SEPARATOR_Y_3 = 488;
	private static final int SEPARATOR_Y_4 = 658;
	
	private static final int BOTTOM_TEXT_OFFSET_X = 250;
	private static final int BOTTOM_TEXT_OFFSET_Y = 75;
	
	private static final int RETURN_BUTTON_X = 164;
	private static final int RETURN_BUTTON_Y = 188;
	private static final int RETURN_BUTTON_WIDTH = 1000;
	private static final int RETURN_BUTTON_HEIGHT = 50;
	
	private static final int TEXT_HELP_X = 164;
	private static final int TEXT_HELP_Y_1 = 238;
	private static final int TEXT_HELP_Y_2 = 288;
	private static final int TEXT_HELP_Y_3 = 338;
	private static final int TEXT_HELP_Y_4 = 738;
	
	private static final int HOST_BUTTON_X = 164;
	private static final int HOST_BUTTON_Y = 438;
	private static final int TEXT_HOST_X = 538;
	private static final int TEXT_HOST_Y = 438;
	
	private static final int JOIN_BUTTON_X = 164;
	private static final int JOIN_BUTTON_Y = 538;
	private static final int TEXT_JOIN_Y = 588;
	
	private static final int TEXT_FIELD_X = 564;
	private static final int TEXT_FIELD_Y = 738;
	private static final int TEXT_FIELD_Y_2 = 588;
	
	private static final String START_IP = "localhost";
	
	/**
	 * Construct a SettingsState.
	 * @param mainGame the MainGame that uses this state.
	 * @param gameState the GameState that uses this MenuMultiplayerState
	 */
	public MenuMultiplayerState(MainGame mainGame, GameState gameState) {
		this.mainGame = mainGame;
		this.gameState = gameState;
	}
	
	/**
	 * setup all variables when entering this state.
	 * @param container the Container this state is part of
	 * @param arg1 The statebasedgame this state is part of
	 * @throws SlickException sometimes.
	 */
	@Override
	public void enter(GameContainer container, StateBasedGame arg1) throws SlickException {
		Logger.getInstance().log("Entering MenuMultiplayerState", 
				Logger.PriorityLevels.LOW, "States");
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
				RND.getInstance().setOpacity(RND.getInstance().getOpacity() 
						- ((float) delta) / mainGame.getOpacityFadeTimer());
			} else {
				Logger.getInstance().log("Exiting MenuMultiplayerState", 
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
		nameField = new Textfield(TEXT_FIELD_X, TEXT_FIELD_Y, "Player", container);
		ipField = new Textfield(TEXT_FIELD_X, TEXT_FIELD_Y_2, START_IP, container);
		popup = new Popup("", MainGame.getxRes(), MainGame.getyRes());
		initElements();
		separatorTop = new Separator(SEPARATOR_X, SEPARATOR_Y, true, separatorTopTitle,
				container.getWidth());
		separatorHost = new Separator(SEPARATOR_X, SEPARATOR_Y_2, false, separatorHostTitle,
				container.getWidth());
		separatorJoin = new Separator(SEPARATOR_X, SEPARATOR_Y_3, false, separatorJoinTitle,
				container.getWidth());
		separatorMisc = new Separator(SEPARATOR_X, SEPARATOR_Y_4, false, separatorMiscTitle,
				container.getWidth());
	}
	
	/**
	 * Initialize the buttons.
	 * @throws SlickException if something goes wrong / file not found
	 */
	private void initElements() throws SlickException {
		elements = new ElementList();
		returnButton = new Button(RETURN_BUTTON_X, RETURN_BUTTON_Y, "< Return");
		hostButton = new Button(HOST_BUTTON_X, HOST_BUTTON_Y, "> Host Game");
		joinButton = new Button(JOIN_BUTTON_X, JOIN_BUTTON_Y, "> Join Game");
		elements.add(returnButton);
		elements.add(hostButton);
		elements.add(joinButton);
		elements.add(ipField);
		elements.add(nameField);
		elements.add(popup);
		elements.coupleVertical(returnButton, hostButton);
		elements.coupleVertical(hostButton, joinButton);
		elements.coupleVertical(joinButton, ipField);
		elements.coupleVertical(ipField, nameField);
		elements.coupleVertical(nameField, returnButton);
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
		nameField.update(input);
		ipField.update(input);
		elements.update(input);
		
		if ((input.isMousePressed(Input.MOUSE_LEFT_BUTTON) || input.isKeyDown(Input.KEY_ENTER))
				&& !mainGame.getShouldSwitchState()) {
			processButtons(input);
		}

		SoundPlayer.getInstance().playEffects();
		
		exit(container, sbg, delta);
	}
	
	/**
	 * Process the buttons.
	 * @param input the keyboard/mouse input of the user
	 */
	private void processButtons(Input input) {
		boolean playSFX = true;
		if (returnButton.isSelected()) {
			processReturnButton();
		}
		else if (hostButton.isSelected()) {
			attemptHost();
		}
		else if (joinButton.isSelected()) {
			attemptJoin();
		} else {
			playSFX = false;
		}

		if (playSFX) {
			SoundPlayer.getInstance().addEffect(new MenuTransitionSoundEffect(false));
		}
	}
	
	/**
	 * Process a click on the return button.
	 */
	private void processReturnButton() {
		mainGame.killMultiplayer();
		mainGame.setSwitchState(mainGame.getMainState());
	}
	
	/**
	 * Attempt to host a game.
	 */
	private void attemptHost() {
		mainGame.killMultiplayer();
		mainGame.setLanMultiplayer(true);
		processPlayerHost();
		mainGame.resetLifeCount();
		mainGame.resetLevelCount();
		mainGame.setScore(0);
		mainGame.spawnHost(new Host(MainGame.getMultiplayerPort(), mainGame, gameState));
		mainGame.setIsHost(true);
		mainGame.setIsClient(false);
		Logger.getInstance().log("Host started", Logger.PriorityLevels.VERYHIGH, "multiplayer");
	}
	
	/**
	 * Attempt to join a game.
	 */
	private void attemptJoin() {
		mainGame.killMultiplayer();
		mainGame.setLanMultiplayer(true);
		processPlayerClient();
		mainGame.resetLifeCount();
		mainGame.resetLevelCount();
		mainGame.setScore(0);
		Client client = new Client(ipField.getText(),
				MainGame.getMultiplayerPort(), mainGame, gameState);
		mainGame.spawnClient(client);
		mainGame.setIsClient(true);
		mainGame.setIsHost(false);
		Logger.getInstance().log("Client started", Logger.PriorityLevels.VERYHIGH, "multiplayer");
	}

	/**
	 * Prepare players for the host's viewpoint.
	 */
	private void processPlayerHost() {
		mainGame.getPlayerList().getPlayers().get(0).setPlayerName(nameField.getText());
		mainGame.getPlayerList().getPlayers().get(0).setControlsForPlayer1();
		mainGame.getPlayerList().getPlayers().get(1).setControlsDisabled();
	}
	
	/**
	 * Prepare players for the client's viewpoint.
	 */
	private void processPlayerClient() {
		mainGame.getPlayerList().getPlayers().get(1).setPlayerName(nameField.getText());
		mainGame.getPlayerList().getPlayers().get(1).setControlsForPlayer1();
		mainGame.getPlayerList().getPlayers().get(0).setControlsDisabled();
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
		drawText(graphics, container);
		separatorTop.drawColor(graphics, mainGame.getColor());
		separatorHost.drawColor(graphics, mainGame.getColor());
		separatorJoin.drawColor(graphics, mainGame.getColor());
		separatorMisc.drawColor(graphics, mainGame.getColor());
		mainGame.drawWaterMark();
		RND.getInstance().drawLogo(graphics, LOGO_X, LOGO_Y);
		elements.render(graphics, input, mainGame.getColor());
		RND.getInstance().drawForeGround(graphics);
	}
	
	/**
	 * Draw all the text in this state.
	 * @param graphics context to use
	 * @param container appgamecontainer to use
	 */
	private void drawText(Graphics graphics, GameContainer container) {
		RND.getInstance().text(graphics, TEXT_HELP_X, TEXT_HELP_Y_1,
				"# You can play a game together with another player, over LAN.");
		RND.getInstance().text(graphics, TEXT_HELP_X, TEXT_HELP_Y_2,
				"# If you are the host, you will have to wait until another player joins you.");
		RND.getInstance().text(graphics, TEXT_HELP_X, TEXT_HELP_Y_3,
				"# If you wish to join another player,"
						+ " please enter their IP-address below.");
		RND.getInstance().text(graphics, TEXT_HELP_X, TEXT_HELP_Y_4, "# Your player name:");
		if (mainGame.isHost()) {
			try {
				RND.getInstance().text(graphics, TEXT_HOST_X, TEXT_HOST_Y, "# Hosting game on IP: " 
						+ InetAddress.getLocalHost().getHostAddress());
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
		RND.getInstance().text(graphics, TEXT_HELP_X, TEXT_JOIN_Y, "# Join this IP: ");
		RND.getInstance().text(graphics, container.getWidth() / 2 - BOTTOM_TEXT_OFFSET_X,
				container.getHeight() - BOTTOM_TEXT_OFFSET_Y, "Waiting for user input...");
	}
	

	@Override
	public int getID() {
		return mainGame.getMultiplayerState();
	}

	/**
	 * Return the mainGame.
	 * @return the mainGame to return
	 */
	public MainGame getmainGame() {
		return mainGame;
	}

	/**
	 * Set the mainGame.
	 * @param mainGame the mainGame to set
	 */
	public void setmainGame(MainGame mainGame) {
		this.mainGame = mainGame;
	}

	/**
	 * Add message when returning from game.
	 * @param message Message to display
	 */
	public void addMessage(String message) {
		mainGame.resetLifeCount();
		mainGame.resetLevelCount();
		mainGame.setScore(0);
		this.message = message;
		elements.throwPopup(message);
	}
}
