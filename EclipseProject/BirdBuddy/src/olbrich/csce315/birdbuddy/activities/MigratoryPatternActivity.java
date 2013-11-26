package olbrich.csce315.birdbuddy.activities;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import olbrich.csce315.birdbuddy.R;
import olbrich.csce315.birdbuddy.R.id;
import olbrich.csce315.birdbuddy.R.layout;
import olbrich.csce315.birdbuddy.marshaller.BirdMarshaller;
import olbrich.csce315.birdbuddy.models.Bird;
import olbrich.csce315.birdbuddy.models.Point;
import olbrich.csce315.birdbuddy.models.Season;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CameraPositionCreator;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngCreator;
import com.google.android.gms.maps.model.PolygonOptions;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
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
        
        
        InputStream file = getResources().openRawResource(R.raw.birds);
        
        /*
        Scanner scanner = new Scanner(file);
        
        while(scanner.hasNext()) {
        	System.out.println(scanner.nextLine());
        }
        */
        
        List<Bird> birds = BirdMarshaller.parseBirdsFromInputStream(file);
        
        for(Bird bird : birds)
        {
        	Season season = bird.getMigrations().get(0);
        	
        	for(Point point : season.getPoints())
        	{
        		birdPolygonOptions.add(new LatLng(point.getLatitude(), point.getLongitude()));
        	}
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