package logic;

import iterator.Aggregate;
import iterator.FloatingScoreListIterator;
import iterator.Iterator;

import java.util.ArrayList;

/**
 * Stores a list of FloatingScores.
 * @author Stefan
 *
 */
public class FloatingScoreList implements Aggregate {

	private ArrayList<FloatingScore> floatingScores;
	
	/**
	 * 
	 * @param floatingScores the FloatingScore to keep track off
	 */
	public FloatingScoreList(ArrayList<FloatingScore> floatingScores) {
		this.floatingScores = floatingScores;
	}
	
	/**
	 * Construct a new FloatingScoreList with empty scores.
	 */
	public FloatingScoreList() {
		floatingScores = new ArrayList<FloatingScore>();
	}

	@Override
	public Iterator createIterator() {
		return new FloatingScoreListIterator(this.floatingScores);
	}
	
	/**
	 * 	Add a floatingScore to the list of floatingScore.
	 * @param floatingScore the floatingScore to add
	 */
	public void add(FloatingScore floatingScore) {
		this.floatingScores.add(floatingScore);
	}

	/**
	 * Return the size of the list of floatingScores.
	 * @return the size of the list of floatingScores
	 */
	public int size() {
		return this.floatingScores.size();
	}
	
	/**
	 * 
	 * @return whether the list of floatingScores is empty
	 */
	public boolean empty() {
		return (this.size() == 0);
	}
	
	/**
	 * Get the gate at the corresponding position.
	 * @param place the position in the gate in the list
	 * @return the gate
	 */
	public FloatingScore get(int place) {
		return this.floatingScores.get(place);
	}
	
	/**
	 * Check if this FloatingScoreList contains element.
	 * @param element the element to check for
	 * @return true if this list contains element, false otherwise
	 */
	public boolean contains(FloatingScore element) {
		return floatingScores.contains(element);
	}
}
