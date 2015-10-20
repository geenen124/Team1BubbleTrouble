package powerups;

import java.util.ArrayList;

import iterator.Aggregate;
import iterator.Iterator;
import iterator.PowerupListIterator;

/**
 * Container class for powerups.
 * @author Menno
 *
 */
public class PowerupList implements Aggregate {
	
	private ArrayList<Powerup> powerups;
	
	/**
	 * Create a PowerupList with empty element list.
	 */
	public PowerupList() {
		powerups = new ArrayList<Powerup>();
	}
	
	/**
	 * Create a PowerupList with specified element list.
	 * @param list the element list to use.
	 */
	public PowerupList(ArrayList<Powerup> list) {
		this.powerups = list;
	}
	
	/**
	 * Add a Powerup.
	 * @param element the powerup to add.
	 */
	public void add(Powerup element) {
		powerups.add(element);
	}
	
	/**
	 * Get a Powerup from the list.
	 * @param index the index of the Powerup to get.
	 * @return the Powerup at index index.
	 */
	public Powerup get(int index) {
		if (index < 0 || index >= powerups.size()) {
			throw new IndexOutOfBoundsException();
		}
		return powerups.get(index);
	}
	
	/**
	 * Return the amount of elements in this list.
	 * @return the size of this list.
	 */
	public int size() {
		return powerups.size();
	}
	
	/**
	 * Check if this list is empty.
	 * @return true if empty, false otherwise
	 */
	public boolean isEmpty() {
		return powerups.isEmpty();
	}

	@Override
	public Iterator createIterator() {
		return new PowerupListIterator(powerups);
	}

}