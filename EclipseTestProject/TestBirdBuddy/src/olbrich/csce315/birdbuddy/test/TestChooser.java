package olbrich.csce315.birdbuddy.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.ListView;
import android.widget.TextView;
import olbrich.csce315.birdbuddy.*;
import olbrich.csce315.birdbuddy.R;
import com.jayway.android.robotium.solo.Solo;

public class TestChooser extends ActivityInstrumentationTestCase2<Chooser> {
	
	private Solo solo;
	public TestChooser() {
		super(Chooser.class);
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
	
	public void testTappingItemLoadsCorrectInfoScreen() {
		ListView birdList = (ListView) solo.getView(R.id.birdList);
		
		TextView birdItem = (TextView) birdList.getChildAt(0);
		
		String birdName = birdItem.getText().toString();
		
		solo.clickInList(0);
		
		solo.assertCurrentActivity("test", BirdInfoViewActivity.class);
		
		assertTrue(solo.waitForText(birdName));
	}
}