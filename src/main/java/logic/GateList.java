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
	
	/**
	 * Create a GateList with empty list.
	 */
	public GateList() {
		gates = new ArrayList<Gate>();
	}

	@Override
	public Iterator createIterator() {
		return new GateListIterator(this.gates);
	}
	
	/**
	 * 	Add a gate to the list of gates.
	 * @param gate the gate to add
	 */
	public void add(Gate gate) {
		this.gates.add(gate);
	}

	/**
	 * Return the size of the list of gates.
	 * @return the size of the list of gates
	 */
	public int size() {
		return this.gates.size();
	}
	
	/**
	 * 
	 * @return whether the list of gates is empty
	 */
	public boolean empty() {
		return (this.size() == 0);
	}
	
	/**
	 * Get the gate at the corresponding position.
	 * @param place the position in the gate in the list
	 * @return the gate
	 */
	public Gate get(int place) {
		return this.gates.get(place);
	}

	/**
	 * 
	 * @return the gates from the GateList
	 */
	public ArrayList<Gate> getGates() {
		return gates;
	}

	/**
	 * 
	 * @param gates the gates to set
	 */
	public void setGates(ArrayList<Gate> gates) {
		this.gates = gates;
	}
	
	
}
