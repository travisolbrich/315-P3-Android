package olbrich.csce315.birdbuddy;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ChooserActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chooser);
		
	String [] birds = getResources().getStringArray(R.array.birds);
	
	ListView listView = (ListView) findViewById(R.id.birdList);
	
	listView.setAdapter(new ArrayAdapter<String>(this, R.layout.bird_list_item, R.id.label, birds));
	
	listView.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			
			// Selected bird
			String bird = ((TextView) view).getText().toString();
			
			// Launch activity
			Intent intent = new Intent(getApplicationContext(), InfoActivity.class);
			intent.putExtra("bird", bird);
			
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
