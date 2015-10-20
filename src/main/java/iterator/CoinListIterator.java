package iterator;

import java.util.ArrayList;

import logic.Coin;

/**
 * Iterator for CircleList.
 * @author Menno, Stefan
 *
 */
public class CoinListIterator implements Iterator {
	private ArrayList<Coin> list;
	private int position;
	
	/**
	 * Construct a new CoinListIterator.
	 * @param list the list with circles to iterate over.
	 */
	public CoinListIterator(ArrayList<Coin> list) {
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
