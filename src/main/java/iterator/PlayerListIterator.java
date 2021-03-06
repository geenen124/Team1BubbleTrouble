package iterator;

import java.util.ArrayList;

import player.Player;

/**
 * Iterator for CircleList.
 * @author Menno, Stefan
 *
 */
public class PlayerListIterator implements Iterator {
	private ArrayList<Player> list;
	private int position;
	
	/**
	 * Construct a new PlayerListIterator.
	 * @param list the list with circles to iterate over.
	 */
	public PlayerListIterator(ArrayList<Player> list) {
		this.list = list;
		reset();
	}
	
	/**
	 * Reset the position.
	 */
	private void reset() {
		position = 0;
	}


	@Override
	public boolean hasNext() {
		return position < list.size();
	}

	@Override
	public Object next() {
		position++;
		return list.get(position - 1);
		
	}

	@Override
	public void remove() {
		list.remove(position - 1);
		position--;
	}

}
