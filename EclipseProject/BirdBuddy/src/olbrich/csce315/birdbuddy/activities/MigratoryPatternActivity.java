package olbrich.csce315.birdbuddy.activities;

import java.io.InputStream;
import java.util.ArrayList;
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
import com.google.android.gms.maps.model.Polygon;
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
        
        final Button resetMap = (Button) findViewById(R.id.map_reset);
		final GoogleMap map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.the_map)).getMap();        
        final SeekBar seeker = (SeekBar) findViewById(R.id.seekBar1);
        final TextView location = (TextView) findViewById(R.id.cur_pos);
        
        // Set map view
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        
        // Get the birds
        InputStream file = getResources().openRawResource(R.raw.birds);
        List<Bird> birds = BirdMarshaller.parseBirdsFromInputStream(file);
        		
		final int birdID = getIntent().getIntExtra("birdID", 0);		
		Bird bird = birds.get(birdID);
		
		// Get the seasons
		final List<Season> seasons = bird.getMigrations();
		
		// colors
		final int transparentGreen = 0x7F00FF00;
		final int transparentRed   = 0x7FFF0000;	
		
		// Plot all of the seasons		
		final List<Polygon> polygons = new ArrayList<Polygon>();
		
    	for(Season season : seasons)
    	{
    		// Polygon Setup
            PolygonOptions birdPolygonOptions = new PolygonOptions();
            
            if(season.getName().compareTo("All Year") == 0)
            {
            	birdPolygonOptions.strokeColor(transparentGreen);
            	birdPolygonOptions.fillColor(transparentGreen);
            }
            else
            {
            	birdPolygonOptions.strokeColor(transparentRed);
            	birdPolygonOptions.fillColor(transparentRed);
            }           
            
            birdPolygonOptions.visible(false);
    		
    		for(Point point : season.getPoints())
	    	{
	    		birdPolygonOptions.add(new LatLng(point.getLatitude(), point.getLongitude()));
	    	}
    		
    		polygons.add(map.addPolygon(birdPolygonOptions));
    	}
    	
    	// Determine if we have a "all year" to plot
    	boolean hasAllYear = (seasons.get(0).getName().compareTo("All Year") == 0);              
    	
    	if(hasAllYear) polygons.get(0).setVisible(true);
    	
    	final int startingSeason = hasAllYear ? 1 : 0;
    	
    	String notice = "";
        
        if(hasAllYear) notice += "Areas the bird is in all year is shown in green. ";
        notice += "Use the slider to change seasons. The current season is shown in red.";
        
        resetMap.setText(notice);
        
        polygons.get(startingSeason).setVisible(true);
    	
    	// Listen for season changes        
	    seeker.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {			
				
				// Divide 4000 into the right number of seasons
				int numChoosableSeasons = seasons.size() - startingSeason;
				int divisionSize = 4000 / (numChoosableSeasons);
				
				int seasonID =(int) Math.ceil((progress) / divisionSize) + startingSeason;
				
				//location.setText(" " + divisionSize + " " + progress + " " + seasonID);
				
				location.setText(" " + seasons.get(seasonID).getName());
				
				// Blank out the other seasons
				for (int i=startingSeason; i < seasons.size(); i++)
				{
					polygons.get(i).setVisible(false);
				}
				
				// Make this season visible
				polygons.get(seasonID).setVisible(true);
			}
		});
    }
   
    
    long map(long x, long in_min, long in_max, long out_min, long out_max)
    {
      return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }
    
    
}