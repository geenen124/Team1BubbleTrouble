package logic;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import iterator.GateListIterator;
import iterator.Iterator;

public class GateListTest {
	
	GateList gl;
	Gate g1;
	Gate g2;
	Gate g3;

	@Before
	public void setUp() throws Exception {
		g1 = new Gate(1, 2, 3, 4);
		g2 = new Gate(1, 2, 3, 4);
		g3 = new Gate(5, 6, 7, 8);
	}

	@Test
	public void testGateListArrayListOfGate() {
		ArrayList<Gate> list = new ArrayList<Gate>();
		list.add(g1);
		gl = new GateList(list);
		assertEquals(list, gl.getGates());
	}

	@Test
	public void testGateList() {
		gl = new GateList();
		assertNotNull(gl.getGates());
	}

	@Test
	public void testCreateIterator() {
		gl = new GateList();
		Iterator iterator = gl.createIterator();
		if (!(iterator instanceof GateListIterator)) {
			fail("Wrong iterator type");
		}
	}

	@Test
	public void testAdd() {
		gl = new GateList();
		gl.add(g1);
		assertTrue(gl.getGates().contains(g1));
	}

	@Test
	public void testSize() {
		gl = new GateList();
		gl.add(g1);
		gl.add(g2);
		assertEquals(2, gl.size());
	}

	@Test
	public void testEmptyTrue() {
		gl = new GateList();
		assertTrue(gl.empty());
	}
	
	@Test
	public void testEmptyFalse() {
		gl = new GateList();
		gl.add(g1);
		assertFalse(gl.empty());
	}

	@Test
	public void testGet() {
		gl = new GateList();
		gl.add(g1);
		gl.add(g3);
		assertEquals(g1, gl.get(0));
	}

	@Test
	public void testGetGates() {
		ArrayList<Gate> expected = new ArrayList<Gate>();
		gl = new GateList();
		gl.add(g1);
		expected.add(g1);
		assertEquals(expected, gl.getGates());
	}

	@Test
	public void testSetGates() {
		ArrayList<Gate> list = new ArrayList<Gate>();
		gl = new GateList();
		list.add(g1);
		gl.setGates(list);
		assertEquals(list, gl.getGates());
	}

}
