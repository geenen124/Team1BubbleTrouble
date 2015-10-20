package iterator;

import java.util.ArrayList;

import powerups.Powerup;

/**
 * Iterator for CircleList.
 * @author Menno, Stefan
 *
 */
public class PowerupListIterator implements Iterator {
	private ArrayList<Powerup> list;
	private int position;
	
	/**
	 * Construct a new PowerupListIterator.
	 * @param list the list with circles to iterate over.
	 */
	public PowerupListIterator(ArrayList<Powerup> list) {
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
