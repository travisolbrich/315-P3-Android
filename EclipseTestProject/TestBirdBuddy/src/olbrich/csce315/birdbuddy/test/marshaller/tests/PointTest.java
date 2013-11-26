package olbrich.csce315.birdbuddy.test.marshaller.tests;

import olbrich.csce315.birdbuddy.models.Bird;
import olbrich.csce315.birdbuddy.models.Point;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class PointTest {

	@Test
	public void testModel() {
		
		Double lat = 1.0;
		Double lng = 1.0;
		
		Point point = new Point(lat, lng);

		Assert.assertTrue(point.getLatitude() == lat);
		Assert.assertTrue(point.getLongitude() == lng);
		
	}
	
}
