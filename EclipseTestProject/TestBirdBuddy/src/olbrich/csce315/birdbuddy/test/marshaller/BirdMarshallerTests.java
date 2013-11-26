package olbrich.csce315.birdbuddy.test.marshaller;

import static org.junit.Assert.*;

import java.util.List;

import olbrich.csce315.birdbuddy.marshaller.BirdMarshaller;
import olbrich.csce315.birdbuddy.models.Bird;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class BirdMarshallerTests {
	
	@Test
	public void testMarshalling() {
		
		final String filename = "birds.xml";
		List<Bird> birds = BirdMarshaller.parseBirdsFromFile(filename);
		
		assertNotNull(birds);
		assertTrue(birds.size() > 0);
		
	}
	
}
