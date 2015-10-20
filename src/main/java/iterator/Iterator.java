package iterator;

/**
 * Interface for Iterator classes.
 * @author Menno, Stefan
 *
 */
public interface Iterator {
	
	/**
	 * Checks if this aggregate has a next element.
	 * @return true if next element is present, false otherwise
	 */
	boolean hasNext();
	
	/**
	 * Returns the next element.
	 * @return the next element.
	 */
	Object next();
	
	/**
	 * Remove the current element.
	 */
	void remove();
}
