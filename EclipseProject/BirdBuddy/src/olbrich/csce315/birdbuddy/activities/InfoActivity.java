package olbrich.csce315.birdbuddy.activities;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import olbrich.csce315.birdbuddy.R;
import olbrich.csce315.birdbuddy.marshaller.BirdMarshaller;
import olbrich.csce315.birdbuddy.models.Bird;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class InfoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info);
		setupActionBar();
		
		TextView textInfo = (TextView) findViewById(R.id.bird_label);
		TextView textDescription = (TextView) findViewById(R.id.description);
		
		Intent intent = getIntent();
		final int birdID = intent.getExtras().getInt("birdID");

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Peregrine Falcon", R.drawable.peregine);
		map.put("Snowy Egret", R.drawable.egret);
		map.put("Canadian Goose", R.drawable.goose);
		map.put("Hooded Warbler", R.drawable.hooded);
		
		InputStream file = getResources().openRawResource(R.raw.birds);
	    List<Bird> birds = BirdMarshaller.parseBirdsFromInputStream(file);
		
	    Bird bird = birds.get(birdID);
	    
	    ImageView image = (ImageView) findViewById(R.id.imageView1);
		image.setImageResource(map.get(bird.getName()));
				
		textInfo.setText(bird.getName());
		textDescription.setText(bird.getDescription());
		
		Button viewMigrations = (Button) findViewById(R.id.viewMigration);
		
		viewMigrations.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// Launch activity
				Intent intent = new Intent(getApplicationContext(), MigratoryPatternActivity.class);
				intent.putExtra("birdID", (int)birdID);
				System.out.println("birdID set as " + birdID);
				startActivity(intent);				
			}
		});
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bird_info_view, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
