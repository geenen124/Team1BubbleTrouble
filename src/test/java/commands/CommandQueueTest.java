package commands;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import logic.Coin;
import logic.CoinList;

import org.junit.Test;

public class CommandQueueTest {

	@Test
	public void testGetInstance() {
		CommandQueue cq= CommandQueue.getInstance();
		assertNotNull(cq);
	}

	@Test
	public void testAddCommandAndExecute() {
		CommandQueue cq = CommandQueue.getInstance();
		cq.clear();
		CoinList testList = new CoinList();
		Coin testItem = new Coin(1, 1, true);
		cq.addCommand(new AddDroppedCoinCommand(testList, testItem));
		cq.executeQueue();
		assertEquals(testItem, testList.get(0));
	} 

}
