package olbrich.csce315.birdbuddy.activities;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import olbrich.csce315.birdbuddy.R;
import olbrich.csce315.birdbuddy.marshaller.BirdMarshaller;
import olbrich.csce315.birdbuddy.models.Bird;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ChooserActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chooser);
		
	InputStream file = getResources().openRawResource(R.raw.birds);
    List<Bird> birds = BirdMarshaller.parseBirdsFromInputStream(file);
	
    List<String> birdNames = new ArrayList<String>();
    
    for(Bird bird : birds)
    {
    	birdNames.add(bird.getName());
    }
    
	ListView listView = (ListView) findViewById(R.id.birdList);
	
	listView.setAdapter(new ArrayAdapter<String>(this, R.layout.bird_list_item, R.id.label, birdNames));
	
	listView.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			
			// Launch activity
			Intent intent = new Intent(getApplicationContext(), InfoActivity.class);
			intent.putExtra("birdID", (int)id);
			startActivity(intent);
		}
		
	});
	}
	
	


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.chooser, menu);
		return true;
	}

}
