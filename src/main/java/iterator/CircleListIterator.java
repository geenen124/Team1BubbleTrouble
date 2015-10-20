package iterator;

import java.util.ArrayList;

import logic.BouncingCircle;

/**
 * Iterator for CircleList.
 * @author Menno, Stefan
 *
 */
public class CircleListIterator implements Iterator {
	private ArrayList<BouncingCircle> list;
	private int position;
	
	/**
	 * Construct a new CircleListIterator.
	 * @param list the list with circles to iterate over.
	 */
	public CircleListIterator(ArrayList<BouncingCircle> list) {
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
