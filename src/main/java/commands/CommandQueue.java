package commands;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import iterator.Aggregate;
import iterator.CommandQueueIterator;
import iterator.Iterator;

/**
 * Queue of commands.
 * @author Bart
 *
 */
public final class CommandQueue implements Aggregate {
	

	private static volatile CommandQueue instance;
	private Queue<Command> queue;
	
	/**
	 * Return instance of RND.
	 * @return	the instance
	 */
	public static CommandQueue getInstance() {
	 	if (instance == null) {
	 		synchronized (CommandQueue.class) {
	 			if (instance == null) {
	 				instance = new CommandQueue();
	 			}
	 		}
	 	}
	 	
	 	return instance;
	}

	/**
	 * 
	 */
	private CommandQueue() {
		queue = new ConcurrentLinkedQueue<Command>();
	}
	
	/**
	 * Add a command to the queue.
	 * @param command	the command to add
	 */
	public synchronized void addCommand(Command command) {
		queue.add(command);
	}
	
	/**
	 * Execute all commands in the queue.
	 */
	public synchronized void executeQueue() {
		Iterator iterator = createIterator();
		while (iterator.hasNext()) {
			Command c = (Command) iterator.next();
			c.execute();
			iterator.remove();
		}
	}

	@Override
	public Iterator createIterator() {
		return new CommandQueueIterator(queue);
	}
}
