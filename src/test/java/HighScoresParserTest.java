import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.rules.ExpectedException;


public class HighScoresParserTest {

	private static final String readFileLocation = "resources/testfile.txt";
	private static final String writeFileLocation = "resources/testfile_empty.txt";

	@Test
	public void readHighScoresTest() {
		HighScores hs = new HighScores();
		hs.add(new Score(300, "jopie krekel"));
		hs.add(new Score(0, "freek"));
		
		HighScores hs2 = HighScoresParser.readHighScores(readFileLocation);
		
		ScoresComparator comp = new ScoresComparator();
		ArrayList<Score> hsl = hs.getScoreList();
		ArrayList<Score> hsl2 = hs2.getScoreList();
		int j = 0;
		
		assertEquals(hsl.size(), hsl2.size());
		
		for(int i = 0; i < hsl.size(); i++) {
			if(comp.compare(hsl.get(i), hsl2.get(i)) == 0) {
				j++;
			}
		}
		
		assertTrue(j == hsl.size());
	}

	
	@Test
	public void writeHighScoresTest() {
		
		HighScores hs = new HighScores();
		hs.add(new Score(300, "jopie krekel"));
		hs.add(new Score(0, "freek"));
		

		HighScoresParser.writeHighScores(writeFileLocation, hs);
		HighScores hs2 = HighScoresParser.readHighScores(writeFileLocation);

		ScoresComparator comp = new ScoresComparator();
		ArrayList<Score> hsl = hs.getScoreList();
		ArrayList<Score> hsl2 = hs2.getScoreList();
		int j = 0;
		
		assertEquals(hsl.size(), hsl2.size());
		
		for(int i = 0; i < hsl.size(); i++) {
			if(comp.compare(hsl.get(i), hsl2.get(i)) == 0) {
				j++;
			}
		}
		
		assertTrue(j == hsl.size());
	}
	
	@Test
	public void testPrivateConstructor() {
		assertEquals(1,HighScoresParser.checkPrivateConstructor(true));
		assertEquals(0,HighScoresParser.checkPrivateConstructor(false));
	}
	
	@Test
	public void testReadHighScoresWrongFilename() {
		ExpectedException exception = ExpectedException.none();
		exception.expect(FileNotFoundException.class);
		HighScoresParser.readHighScores("not a valid filename");
	}
}
