package iterator;

import java.util.ArrayList;

import logic.Score;

/**
 * Iterator for CircleList.
 * @author Menno, Stefan
 *
 */
public class HighScoresIterator implements Iterator {
	private ArrayList<Score> list;
	private int position;
	
	/**
	 * Construct a new ScoreListIterator.
	 * @param list the list with circles to iterate over.
	 */
	public HighScoresIterator(ArrayList<Score> list) {
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
