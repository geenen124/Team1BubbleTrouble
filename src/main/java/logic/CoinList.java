package logic;

import java.util.ArrayList;

import iterator.Aggregate;
import iterator.CoinListIterator;
import iterator.Iterator;

/**
 * Container class for coins.
 * @author Menno
 *
 */
public class CoinList implements Aggregate {
	
	private ArrayList<Coin> coins;
	
	/**
	 * Construct a new CoinList with empty element list.
	 */
	public CoinList() {
		coins = new ArrayList<Coin>();
	}
	
	/**
	 * Construct a new CoinList with specified element list.
	 * @param coins the element list to use.
	 */
	public CoinList(ArrayList<Coin> coins) {
		this.coins = coins;
	}
	
	/**
	 * Add a coin.
	 * @param element the element to add.
	 */
	public void add(Coin element) {
		coins.add(element);
	}
	
	/**
	 * Get a coin.
	 * @param index the index of the coin to get.
	 * @return the coin on position index.
	 */
	public Coin get(int index) {
		if (index < 0 || index >= coins.size()) {
			throw new IndexOutOfBoundsException();
		}
		return coins.get(index);
	}
	
	/**
	 * Get the size.
	 * @return the size
	 */
	public int size() {
		return coins.size();
	}
	
	/**
	 * Check if this list is empty.
	 * @return true if empty, otherwise false.
	 */
	public boolean isEmpty() {
		return coins.isEmpty();
	}

	@Override
	public Iterator createIterator() {
		return new CoinListIterator(coins);
	}

	/**
	 * 
	 * @return the Coin objects stored
	 */
	public ArrayList<Coin> getCoins() {
		return coins;
	}

	/**
	 * 
	 * @param coins the Coins to store
	 */
	public void setCoins(ArrayList<Coin> coins) {
		this.coins = coins;
	}

}
