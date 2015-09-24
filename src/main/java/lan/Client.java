package lan;

import gui.GameState;
import gui.MainGame;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.Callable;

import logic.BouncingCircle;
import logic.Logger;
import logic.Weapon;

/**
 * Client class which connects to server for LAN multiplayer.
 * @author alexandergeenen
 */
public class Client implements Callable {

    private int portNumber;
    private String host;
    private Socket socket;
    private boolean isConnected;
    private Logger logger;
    private Queue<String> messageQueue;
    private PrintWriter writer;
    private Scanner reader;
    private MainGame mainGame;
    private GameState gameState;
    
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int FIVE = 5;

    /**
     * Create a new Client connection for LAN multiplayer.
     * @param host Host server address
     * @param portNumber Port number for multiplayer
     * @param mainGame javadoc
     * @param gameState javadoc
     */
    public Client(String host, int portNumber, MainGame mainGame, GameState gameState) {
        this.host = host;
        this.mainGame = mainGame;
        this.gameState = gameState;
        this.portNumber = portNumber;
        this.isConnected = false;
        this.messageQueue = new LinkedList<>();
    }

    @Override
    public Boolean call() throws IOException {
    	System.out.println("CLIENT.call");
        socket = new Socket();
        socket.connect(new InetSocketAddress(InetAddress.getLocalHost(), portNumber));
		mainGame.setSwitchState(mainGame.getGameState());
        writer = new PrintWriter(socket.getOutputStream(), true);
        reader = new Scanner(new InputStreamReader(socket.getInputStream()));
        System.out.println("Connected to server");
        // This continues ad infinitum
        while (true) {
            // READ AND WRITE LOGIC HERE
            if (!this.messageQueue.isEmpty()) {
                writer.println(this.messageQueue.poll());
            }
            readServerCommands();
        }
    }

    /**
     * Process server commands.
     */
    private void readServerCommands() {
        while (reader.hasNextLine()) {
        	String message = reader.nextLine();
        	System.out.println(message);
        	String message2 = message.trim();
        	if (message2.startsWith("NEW")) {
        		newMessage(message2.replaceFirst("NEW", ""));
        	} else if (message2.startsWith("SYSTEM")) {
        		systemMessage(message2.replaceFirst("SYSTEM", ""));
        	} else if (message2.startsWith("UPDATE")) {
        		updateMessage(message2.replaceFirst("UPDATE", ""));
        	} else if (message2.startsWith("CIRCLE")) {
        		circleMessage(message2.replaceFirst("CIRCLE", ""));
        	} else if (message2.startsWith("PLAYER")) {
        		playerMessage(message2.replaceFirst("PLAYER", ""));
        	}	
        }
    }
    
    /**
     * javadoc.
     * @param message .
     */
    private void playerMessage(String message) {
    	String message2 = message.trim();
    	
    	if (message2.startsWith("MOVEMENT")) {
    		movementMessage(message2.replaceFirst("MOVEMENT", ""));
    	}
    }
    
    /**
     * javadoc.
     * @param message .
     */
    private void movementMessage(String message) {
    	String message2 = message.trim();
    
    	if (message2.startsWith("STARTED")) {
    		movementStarted(message2.replaceFirst("STARTED", ""));
    	} else if (message2.startsWith("STOPPED")) {
    		movementStopped(message2.replaceFirst("STOPPED", ""));
    	}
    }
    
    /**
     * javadoc.
     * @param message .
     */
    private void movementStarted(String message) {
    	String message2 = message.trim();
    	String[] stringList = message2.split(" ");
    	
    	float x = Float.parseFloat(stringList[0]);
    	float y = Float.parseFloat(stringList[1]);
    	int playerNumber = Integer.parseInt(stringList[2]);
        String direction = stringList[THREE];
    
        mainGame.getPlayerList().getPlayers().get(playerNumber).setX(x);
        mainGame.getPlayerList().getPlayers().get(playerNumber).setY(y);
        
        if (direction.equals("LEFT")) {
        	mainGame.getPlayerList().getPlayers().get(playerNumber).setMovingLeft(true);
        } else if (direction.equals("RIGHT")) {
        	mainGame.getPlayerList().getPlayers().get(playerNumber).setMovingRight(true);
        }
    }
    
    /**
     * javadoc.
     * @param message .
     */
    private void movementStopped(String message) {
    	String message2 = message.trim();
    	String[] stringList = message2.split(" ");
    	
    	float x = Float.parseFloat(stringList[0]);
    	float y = Float.parseFloat(stringList[1]);
    	int playerNumber = Integer.parseInt(stringList[2]);
    	
    	mainGame.getPlayerList().getPlayers().get(playerNumber).setX(x);
        mainGame.getPlayerList().getPlayers().get(playerNumber).setY(y);
        
    	mainGame.getPlayerList().getPlayers().get(playerNumber).setMovingRight(false);
    	mainGame.getPlayerList().getPlayers().get(playerNumber).setMovingLeft(false);
    }
    
    /**
     * javadoc.
     * @param message .
     */
    private void circleMessage(String message) {
    	String message2 = message.trim();
    	String[] stringList = message2.split(" ");
    	gameState.getCircleList().add(new BouncingCircle(Float.parseFloat(stringList[0]), 
    			Float.parseFloat(stringList[1]), Float.parseFloat(stringList[2]), 
    			Float.parseFloat(stringList[THREE]), Float.parseFloat(stringList[FOUR]), 
    			Float.parseFloat(stringList[FIVE])));
    }
    
    /**
     * javadoc.
     * @param message .
     */
    private void updateMessage(String message) {
    	String message2 = message.trim();
    	if (message2.equals("CIRCLELIST")) {
    		gameState.setCircleList(new ArrayList<BouncingCircle>());
    	}
    	
    }
    
    /**
     * javadoc.
     * @param message .
     */
    private void newMessage(String message) {
    	String message2 = message.trim();
    	if (message2.startsWith("PLAYERLOCATION")) {
    		playerLocation(message2.replaceFirst("PLAYERLOCATION", ""));
    	} else if (message2.startsWith("LASER")) {
    		laserMessage(message2.replaceFirst("LASER", ""));
    	}
    }
    
    /**
     * javadoc.
     * @param message .
     */
    private void laserMessage(String message) {
    	String message2 = message.trim();
    	String[] stringList = message2.split(" ");
    	
    	Weapon weapon = new Weapon(Float.parseFloat(stringList[0]), 
    			Float.parseFloat(stringList[1]), Float.parseFloat(stringList[2]), 
    			Float.parseFloat(stringList[THREE]));
    	
    	gameState.getWeaponList().setWeapon(0, weapon);
    	mainGame.getPlayerList().getPlayers().get(0).setShot(true);
    }
    
    /**
     * javadoc.
     * @param message .
     */
    private void playerLocation(String message) {
    	String message2 = message.trim();
    	String[] stringList = message2.split(" ");
    	
    	float x = Float.parseFloat(stringList[0]);
    	float y = Float.parseFloat(stringList[1]);
    	
    	mainGame.getPlayerList().getPlayers().get(0).setX(x);
    	mainGame.getPlayerList().getPlayers().get(0).setY(y);
    }
    
    /**
     * javadoc.
     * @param message .
     */
    private void systemMessage(String message) {
    	String message2 = message.trim();
    	if (message2.startsWith("LEVEL")) {
    		levelMessage(message2.replaceFirst("LEVEL", ""));
    	}
    }
    
    /**
     * javadoc.
     * @param message .
     */
    private void levelMessage(String message) {
    	String message2 = message.trim();
    	if (message2.equals("STARTED")) {
    		gameState.setLevelStarted(true);
    	}
    }

    /**
     * Attempt to gracefully shut down the LAN threads.
     * @throws InterruptedException Thrown if shutdown takes longer than expected.
     */
    public void shutdown() throws InterruptedException {
        logger.log("Shutting down client connection", Logger.PriorityLevels.LOW, "lan");
        try {
            socket.close();
        } catch (IOException er) {
            logger.log(er.getMessage(), Logger.PriorityLevels.LOW, "lan");
        }
    }

    /**
     * Send a message to the host.
     * @param toWrite The message to send
     */
    public void sendMessageToHost(String toWrite) {
        this.messageQueue.add(toWrite);
    }

    /**
     * Set the logger for this host.
     * @param logger the logger
     */
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    /**
     * @return Whether or not the client has connected to the remote server
     */
    public boolean connectedToServer() {
        return this.socket.isConnected();
    }
}
