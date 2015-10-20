package logic;

import iterator.Aggregate;
import iterator.GateListIterator;
import iterator.Iterator;

import java.util.ArrayList;

/**
 * A class that stores an arraylist of gates.
 * @author Stefan
 *
 */
public class GateList implements Aggregate {

	private ArrayList<Gate> gates;
	
	/**
	 * 
	 * @param gates the gates that the GateList object stores
	 */
	public GateList(ArrayList<Gate> gates) {
		this.gates = gates;
	}

	@Override
	public Iterator createIterator() {
		return new GateListIterator(this.gates);
	}
	
	//Probably needs add and remove methods
}
