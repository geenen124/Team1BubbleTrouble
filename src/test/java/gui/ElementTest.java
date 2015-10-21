package gui;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import guiobjects.Button;
import guiobjects.ElementList;
import logic.MyRectangle;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Input;

public class ElementTest {

	Button a;
	Button b;
	Button c;
	Input input;
	
	@Before
	public void setup() {
		a = new Button(0, 0, "dada", true);
		b = new Button(1, 1, "dede", true);
		c = new Button(1, 1, "dede", true);
		input = mock(Input.class);
		when(input.getMouseX()).thenReturn(2);
		when(input.getMouseY()).thenReturn(2);
	}
	
	@Test
	public void testIsMouseOver() {
		assertTrue(a.isMouseOver(input));
	}
	
	@Test
	public void testGetRectangle() {
		MyRectangle r = a.getRectangle();
		boolean one = r.getWidth() == 50 && r.getHeight() == 53
					&& r.getX() == 0 && r.getY() == 0;
		assertTrue(one);
	}
	
	@Test
	public void testReset() {
		a.setEnabled(false);
		a.setSelected(true);
		a.reset();
		assertTrue(a.isEnabled() && !a.isSelected());
	}
	
	@Test
	public void testGettersSettersOne() {
		a.setEnabled(false);
		a.setSelected(true);
		a.setX(10);
		a.setY(20);
		a.setWidth(200);
		a.setHeight(300);
		boolean one = !a.isEnabled() && a.isSelected()
				&& a.getX() == 10 && a.getY() == 20
				&& a.getWidth() == 200 && a.getHeight() == 300;
		boolean two = a.getCenterX() == 110 && a.getCenterY() == 170
				&& a.getMaxX() == 210 && a.getMaxY() == 320;
		assertTrue(one && two);
	}
	
	@Test
	public void testLinking() {
		a.setRight(b);
		a.setLeft(c);
		a.setTop(b);
		a.setBottom(c);
		assertTrue(a.getRight() == b && a.getLeft() == c
				&& a.getTop() == b && a.getBottom() == c);
	}
	
	@Test
	public void testLinkRemoving() {
		a.setRight(b);
		a.setLeft(c);
		a.setTop(b);
		a.setBottom(c);
		a.removeBottom();
		a.removeLeft();
		a.removeRight();
		a.removeTop();
		assertTrue(a.getRight() == null && a.getLeft() == null
				&& a.getTop() == null && a.getBottom() == null);
	}
	
	@Test
	public void testTesting() {
		assertTrue(!a.getTesting());
		a.setTesting(true);
		assertTrue(a.getTesting());
	}
	
	@Test
	public void testLists() {
		ElementList list = mock(ElementList.class);
		a.setList(list);
		assertEquals(list, a.getList());
	}
	
	@Test
	public void testText() {
		a.setText("Woopah");
		assertEquals("Woopah", a.getText());
	}
	
}
