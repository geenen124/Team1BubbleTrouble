package iterator;

/**
 * Interface for Iterable class.
 * @author Menno, Stefan
 *
 */
public interface Aggregate {

	/**
	 * Create an iterator for this class.
	 * @return an iterator
	 */
	Iterator createIterator();
}
