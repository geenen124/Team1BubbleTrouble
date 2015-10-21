package commands;

import iterator.Iterator;
import logic.Coin;
import logic.CoinList;

/**
 * Command which removes coin from given list.
 * @author Bart
 *
 */
public class RemoveDroppedCoinCommand extends Command {

	private final CoinList list;
	private final Coin item;
	
	/**
	 * @param list	- the list to remove from
	 * @param item	- the item to remove
	 */
	public RemoveDroppedCoinCommand(CoinList list, Coin item) {
		this.list = list;
		this.item = item; 
	}



	@Override
	public void execute() {
		synchronized (list) {
			if (list.contains(item)) {
				Iterator iterator = list.createIterator();
				Coin c = null;
				while (!c.equals(item)) {
					c = (Coin) iterator.next();
				}
				iterator.remove();
			}
		}
	}

}
