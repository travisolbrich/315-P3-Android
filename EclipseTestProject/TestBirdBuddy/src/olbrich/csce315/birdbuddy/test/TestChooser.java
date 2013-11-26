package olbrich.csce315.birdbuddy.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import olbrich.csce315.birdbuddy.*;
import olbrich.csce315.birdbuddy.R;
import olbrich.csce315.birdbuddy.activities.ChooserActivity;
import olbrich.csce315.birdbuddy.activities.InfoActivity;
import olbrich.csce315.birdbuddy.activities.MigratoryPatternActivity;

import com.jayway.android.robotium.solo.Solo;

public class TestChooser extends ActivityInstrumentationTestCase2<ChooserActivity> {
	
	private Solo solo;
	public TestChooser() {
		super(ChooserActivity.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void testListViewHasBirds() {
		ListView birdList = (ListView) solo.getView(R.id.birdList);
		
		assertTrue(birdList.getCount() >= 1);
	}
	
	public void testTappingItemLoadsCorrectInfoScreenAndMap() {
		ListView birdList = (ListView) solo.getView(R.id.birdList);		
		TextView birdItem = (TextView) birdList.getChildAt(0);		
		String birdName = birdItem.getText().toString();
		
		// Click the first element in the list
		solo.clickInList(0);
		
		// Make sure that we're passed to the BirdInfoViewActivity
		solo.assertCurrentActivity("test", InfoActivity.class);
		
		// Make sure the bird info is on the page
		// TODO: Use something more robust to make sure we're actually on the right page
		assertTrue(solo.waitForText(birdName));
		
		// Make sure we get to the map page
		solo.clickOnButton("View Migration Patterns");
		solo.assertCurrentActivity("Failed", MigratoryPatternActivity.class);
	}
}