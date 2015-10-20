package iterator;

import java.util.ArrayList;

import logic.FloatingScore;

/**
 * Iterator for FloatingScoreList.
 * @author Stefan
 *
 */
public class FloatingScoreListIterator implements Iterator {

	private ArrayList<FloatingScore> list;
	private int position;
	
	/**
	 * 
	 * @param list the arrayList of floatingScore objects to iterate over
	 */
	public FloatingScoreListIterator(ArrayList<FloatingScore> list) {
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
