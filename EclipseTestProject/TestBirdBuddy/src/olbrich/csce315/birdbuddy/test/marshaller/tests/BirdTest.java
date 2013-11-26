package olbrich.csce315.birdbuddy.test.marshaller.tests;

import olbrich.csce315.birdbuddy.models.Bird;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Assert;
import org.junit.Test;


@RunWith(JUnit4.class)
public class BirdTest {

	@Test
	public void testModel() {
		
		Bird bird = new Bird(1L);
		
		Assert.assertTrue(bird.getIdentifier() == 1);
		
		String name = "name";
		bird.setName(name);
		Assert.assertTrue(bird.getName().equals(name));
		
		String summary = "summary";
		bird.setDescription(summary);
		Assert.assertTrue(bird.getDescription().equals(summary));
		
	}
	
}
