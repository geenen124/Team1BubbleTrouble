package iterator;

import java.util.Queue;

import commands.Command;

/**
 * Iterator for a CommandQueue.
 * @author Menno
 *
 */
public class CommandQueueIterator implements Iterator {
	
	private Queue<Command> queue;
	private int position;
	private Object[] array;
	
	/**
	 * Construct a new CommandQueueIterator.
	 * @param queue the queue to iterate over.
	 */
	public CommandQueueIterator(Queue<Command> queue) {
		this.queue = queue;
		reset();
	}
	
	/**
	 * Reset this iterator.
	 */
	private void reset() {
		position = 0;
		array = queue.toArray();
	}

	@Override
	public boolean hasNext() {
		return position < queue.size();
	}

	@Override
	public Object next() {
		position++;
		return array[position - 1];
	}

	@Override
	public void remove() {
		Command c = (Command) array[position - 1];
		queue.remove(c);
		array = queue.toArray();
		position--;
	}

}
