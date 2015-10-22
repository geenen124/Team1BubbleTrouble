package guigame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import guimenu.MainGame;
import iterator.GateListIterator;
import iterator.Iterator;
import logic.Gate;
import logic.GateList;

/**
 * GameState Helper class for managing all gates. This is done to prevent GameState 
 * from holding too much responsibility and/or knowledge. The class should only
 * be used in conjunction with GameState.
 * @author Mark
 */
public class GameStateGateHelper extends GameStateHelper {
	
	private GateList gateList;
	
	/**
	 * Constructor for a GameStateGateHelper object.
	 * @param app the Main Game this class draws data from.
	 * @param state the GameState parent.
	 */
	public GameStateGateHelper(MainGame app, GameState state) {
		mainGame = app;
		parentState = state;
	}
	
	@Override
	public void enter() {
		gateList = new GateList(parentState.getLevelsHelper().getLevelContainer().getLevel(
				mainGame.getLevelCounter()).getGates());
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg,
			float deltaFloat) {		
		Iterator iterator = this.gateList.createIterator();
		while (iterator.hasNext()) {
			Gate gate = (Gate) iterator.next();
			mainGame.getHost().updateRequiredForGateList(
					gate.getUnlockCircles(), (((GateListIterator) iterator).getPosition() - 1));
		}
	}
	
	/**
	 * Update the existence of the gates. Remove them if the required balls have disappeared.
	 * @param deltaFloat the time in seconds since the last frame.
	 */
	public void updateGateExistence(float deltaFloat) {
		GateList tempGateList = new GateList();
		synchronized (gateList) {
			Iterator iterator = gateList.createIterator();
			while (iterator.hasNext()) {
				Gate gate = (Gate) iterator.next();
				if (gate.getUnlockCircles().isEmpty()) {
					tempGateList.add(gate);
					gate.setFading(true);
				}
			}
		}
		Iterator iterator = tempGateList.createIterator();
		while (iterator.hasNext()) {
			Gate gate = (Gate) iterator.next();
			if (gateList.getGates().contains(gate) && gate.isDone()) {
				gateList.getGates().remove(gate);
			} else if (gateList.getGates().contains(gate) && gate.isFading()) {
				gate.update(deltaFloat);
			}
		}
	}
	
	@Override
	public void render(Graphics graphics, GameContainer container) {
		synchronized (gateList) {
			Iterator iterator = gateList.createIterator();
			while (iterator.hasNext()) {
				Gate gate = (Gate) iterator.next();
				gate.draw(graphics, mainGame, parentState, container);
			}		
		}
	}
	
	//Synchronous methods still don't use the GateList class
	//PlayerGateHelper processGates
	//the two methods above
	//Menno: Now they do
	
	/**
	 * 
	 * @return the GateList object to return
	 */
	public GateList getGateList() {
		return gateList;
	}
}
