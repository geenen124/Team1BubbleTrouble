package iterator;

import java.util.ArrayList;

import logic.Gate;

/**
 * Iterator for GateList.
 * @author Stefan
 *
 */
public class GateListIterator implements Iterator {

	private ArrayList<Gate> list;
	private int position;
	
	/**
	 * Construct a new GateListIterator.
	 * @param list the list with gates to iterate over.
	 */
	public GateListIterator(ArrayList<Gate> list) {
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
