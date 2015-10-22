package iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import logic.HighScores;
import logic.Score;

public class HighScoresIteratorTest {
	
	HighScoresIterator iterator;
	HighScores list;
	Score s1;
	Score s2;
	Score s3;

	@Before
	public void setUp() throws Exception {
		s1 = new Score(5, "A");
		s2 = new Score(6, "A");
		s3 = new Score(7, "A");
		list = new HighScores();
		list.add(s1);
		list.add(s2);
		list.add(s3);
		iterator = (HighScoresIterator) list.createIterator();
	}


	@Test
	public void testHasNextTrue() {
		assertTrue(iterator.hasNext());
	}
	
	@Test
	public void testHasNextFalse() {
		iterator.next();
		iterator.next();
		iterator.next();
		assertFalse(iterator.hasNext());
	}

	@Test
	public void testNext() {
		iterator.next();
		assertEquals(s2, iterator.next());
	}

	@Test
	public void testRemove() {
		iterator.next();
		iterator.remove();
		assertEquals(2, list.getScoreList().size());
	}

}
