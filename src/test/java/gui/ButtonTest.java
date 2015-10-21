package gui;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import guiobjects.Button;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.Input;

public class ButtonTest {
	
	Button a;
	Button b;
	Button c;
	Input input;

	@Before
	public void setUp() throws Exception {
		a = new Button(0, 0, "dada", true);
		b = new Button(1, 1, "dede", true);
		c = new Button(1, 1, "dede", true);
		input = mock(Input.class);
		when(input.getMouseX()).thenReturn(2);
		when(input.getMouseY()).thenReturn(2);
		a.update(input);
	}
	
	@Test
	public void testEquals() {
		assertTrue(b.equals(c));
		assertEquals(0, b.hashCode());
	}
	
	@Test
	public void testEqualsFalse() {
		assertFalse(a.equals(c));
	}
	
	
}
