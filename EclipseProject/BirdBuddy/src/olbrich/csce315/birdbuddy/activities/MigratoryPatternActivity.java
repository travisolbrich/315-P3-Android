package olbrich.csce315.birdbuddy.activities;

import java.io.InputStream;
import java.util.List;
import olbrich.csce315.birdbuddy.R;
import olbrich.csce315.birdbuddy.marshaller.BirdMarshaller;
import olbrich.csce315.birdbuddy.models.Bird;
import olbrich.csce315.birdbuddy.models.Point;
import olbrich.csce315.birdbuddy.models.Season;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MigratoryPatternActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_migratory_pattern);
        
        Button resetMap = (Button) findViewById(R.id.map_reset);
        
		final GoogleMap map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.the_map)).getMap();
        
        final SeekBar seeker = (SeekBar) findViewById(R.id.seekBar1);
        final TextView location = (TextView) findViewById(R.id.cur_pos);
        
        // Polygon
        PolygonOptions birdPolygonOptions = new PolygonOptions();
        birdPolygonOptions.strokeColor(0x7F00FF00);
        
        // Get the birds
        
        
        
        
        /*
        Scanner scanner = new Scanner(file);
        
        while(scanner.hasNext()) {
        	System.out.println(scanner.nextLine());
        }
        */
        InputStream file = getResources().openRawResource(R.raw.birds);
        List<Bird> birds = BirdMarshaller.parseBirdsFromInputStream(file);
        
        Intent intent = getIntent();		
		final int birdID = intent.getIntExtra("birdID", 0);
		
		Bird bird = birds.get(birdID);
                
    	Season season = bird.getMigrations().get(0);
    	
    	for(Point point : season.getPoints())
    	{
    		birdPolygonOptions.add(new LatLng(point.getLatitude(), point.getLongitude()));
    	}
                
        map.addPolygon(birdPolygonOptions);
        
	    seeker.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				location.setText(" "  + (progress + 500) / 1000  );
				map.animateCamera(CameraUpdateFactory.zoomTo(map(progress, 0, 4000, 2, 21)));
				
			}
		});
        		
        map.setOnCameraChangeListener(new OnCameraChangeListener() {
			
			@Override
			public void onCameraChange(CameraPosition arg0) {
				
				
				CameraPosition position = map.getCameraPosition();
				
				double lat = position.target.latitude;
				double longitude = position.target.longitude;
				
				location.setText("You are looking at " + lat + " " + longitude);
				
			}
		});
        
        resetMap.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				map.setMapType((int) Math.floor(Math.random()*3) + 1);
				map.setMyLocationEnabled(true);
				
				double newLat = Math.random()*180 - 90;
				double newLong = Math.random()*180 - 90;
				
				map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(newLat, newLong), (float) (Math.random()*3)));
			}
			
			
			
		});
    }
    
    long map(long x, long in_min, long in_max, long out_min, long out_max)
    {
      return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }
    
    
}