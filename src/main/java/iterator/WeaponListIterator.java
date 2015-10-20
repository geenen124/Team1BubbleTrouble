package iterator;

import java.util.ArrayList;

import logic.Weapon;

/**
 * Iterator for CircleList.
 * @author Menno, Stefan
 *
 */
public class WeaponListIterator implements Iterator {
	private ArrayList<Weapon> list;
	private int position;
	
	/**
	 * Construct a new CoinListIterator.
	 * @param list the list with circles to iterate over.
	 */
	public WeaponListIterator(ArrayList<Weapon> list) {
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
