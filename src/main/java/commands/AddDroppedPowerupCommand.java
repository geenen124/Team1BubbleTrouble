package commands;

import powerups.Powerup;
import powerups.PowerupList;


/**
 * Command which removes dropped powerup from given list.
 * @author Bart
 *
 */
public class AddDroppedPowerupCommand extends Command {

	private final PowerupList list;
	private final Powerup item;
	
	/**
	 * @param list	- the list to remove from
	 * @param item	- the item to remove
	 */
	public AddDroppedPowerupCommand(PowerupList list, Powerup item) {
		this.list = list;
		this.item = item;
	}



	@Override
	public void execute() {
		synchronized (list) {
			if (!list.contains(item)) {
				list.add(item);
			}
		}
	}
}
