package gui;

import static org.junit.Assert.*;
import guiobjects.Button;
import guiobjects.ElementList;
import guiobjects.Popup;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class ElementListTest {

	ElementList list1;
	ElementList list2;
	Button a;
	Button b;
	Button c;
	
	@Before
	public void setup() {
		list1 = new ElementList();
		list2 = new ElementList();
		a = new Button(0, 0, "dada", true);
		b = new Button(1, 1, "dede", true);
		c = new Button(1, 1, "dede", true);
	}
	
	@Test
	public void testCoupling() {
		list1.add(a); list1.add(b); list1.add(c);
		list1.coupleHorizontal(a, b);
		list1.coupleVertical(a, c);
		assertTrue(a.getRight() == b && a.getBottom() == c
				&& b.getLeft() == a && c.getTop() == a);
	}
	
	@Test
	public void testLooping() {
		list1.add(a); list1.add(b); list1.add(c);
		list1.loopHorizontal(a, b);
		list1.loopVertical(a, c);
		assertTrue(a.getRight() == b && a.getLeft() == b
				&& b.getLeft() == a && b.getRight() == a
				&& a.getTop() == c && a.getBottom() == c
				&& c.getTop() == a && c.getBottom() == a);
	}
	
	@Test
	public void testAddRemove() {
		boolean one = list1.getSize() == 0;
		list1.add(a); list1.add(b);
		list1.loopHorizontal(a, b);
		list1.loopVertical(a, b);
		boolean two = list1.getSize() == 2;
		list1.remove(a);
		boolean three = list1.getSize() == 1;
		list1.add(b);
		list1.loopHorizontal(a, b);
		list1.loopVertical(a, b);
		list1.remove(0);
		boolean four = list1.getSize() == 1;
		assertTrue(one && two && three && four);
	}
	
	@Test
	public void testGettersSetters() {
		list1.add(a);
		list1.reset();
		assertEquals(list1.get(0), a);
		assertEquals(list1.getSize(), list1.getList().size());
		list1.setSelectable(false);
		assertFalse(list1.isSelectable());
		list1.setMouseActive(false);
		assertFalse(list1.isMouseActive());
		list1.setTextfieldActive(true);
		assertTrue(list1.isTextfieldActive());
	}
	
//	@Test
//	public void testKeyboardNavigation() {
//		list1.add(a);
//		list1.add(b);
//		list1.loopHorizontal(a, b);
//		list1.loopVertical(a, b);
//		assertFalse(a.isSelected());
//		assertFalse(list1.anyHighlighted());
//		Input input = mock(Input.class);
//		when(input.getMouseX()).thenReturn(0);
//		when(input.getMouseY()).thenReturn(0);
//		list1.update(input);
//		
//		when(input.isKeyPressed(Input.KEY_RIGHT)).thenReturn(true);
//		when(input.isKeyDown(Input.KEY_RIGHT)).thenReturn(true);
//		list1.update(input);
//		assertTrue(a.isSelected());
//		list1.update(input);
//		assertTrue(b.isSelected());
//		when(input.isKeyPressed(Input.KEY_RIGHT)).thenReturn(false);
//		when(input.isKeyDown(Input.KEY_RIGHT)).thenReturn(false);
//		b.setSelected(false);
//		
//		when(input.isKeyPressed(Input.KEY_LEFT)).thenReturn(true);
//		when(input.isKeyDown(Input.KEY_LEFT)).thenReturn(true);
//		list1.update(input);
//		assertTrue(a.isSelected());
//		list1.update(input);
//		assertTrue(b.isSelected());
//		when(input.isKeyPressed(Input.KEY_LEFT)).thenReturn(false);
//		when(input.isKeyDown(Input.KEY_LEFT)).thenReturn(false);
//		b.setSelected(false);
//		
//		when(input.isKeyPressed(Input.KEY_UP)).thenReturn(true);
//		when(input.isKeyDown(Input.KEY_UP)).thenReturn(true);
//		list1.update(input);
//		assertTrue(a.isSelected());
//		list1.update(input);
//		assertTrue(b.isSelected());
//		when(input.isKeyPressed(Input.KEY_UP)).thenReturn(false);
//		when(input.isKeyDown(Input.KEY_UP)).thenReturn(false);
//		b.setSelected(false);
//		
//		when(input.isKeyPressed(Input.KEY_DOWN)).thenReturn(true);
//		when(input.isKeyDown(Input.KEY_DOWN)).thenReturn(true);
//		list1.update(input);
//		assertTrue(a.isSelected());
//		list1.update(input);
//		assertTrue(b.isSelected());
//		when(input.isKeyPressed(Input.KEY_DOWN)).thenReturn(false);
//		when(input.isKeyDown(Input.KEY_DOWN)).thenReturn(false);
//		assertTrue(list1.anyHighlighted());
//		b.setSelected(false);
//	}
//	
//	@Test
//	public void testMouseNavigaiton() {
//		list1.add(a);
//		list1.add(b);
//		Input input = mock(Input.class);
//		when(input.getMouseX()).thenReturn(800);
//		when(input.getMouseY()).thenReturn(800);
//		assertFalse(list1.get(0).isMouseOver(input));
//		list1.update(input);
//		when(input.getMouseX()).thenReturn(1);
//		when(input.getMouseY()).thenReturn(1);
//		assertTrue(list1.get(0).isMouseOver(input));
//		list1.update(input);
//		assertTrue(a.isSelected());
//	}
	
	@Test
	public void testFindIndex() {
		list1.add(a); list1.add(b);
		assertEquals(-1, list1.findIndex(c));
		assertEquals(1, list1.findIndex(b));
		assertEquals(0, list1.findIndex(a));
	}
	
	@Test
	public void testPopups() throws SlickException {
		list1.add(new Popup("dada", 1280, 720, true));
		//Input input = mock(Input.class);
		//when(input.getMouseX()).thenReturn(800);
		//when(input.getMouseY()).thenReturn(800);
		list1.throwPopup("BOO");
		//list1.update(input);
		assertFalse(list1.isSelectable());
	}
	
	
	
}
