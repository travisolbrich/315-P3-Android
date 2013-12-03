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
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
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
        final TextView seasonNameDisplay = (TextView) findViewById(R.id.cur_pos);
        
        // Set map view
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        
        // Get the birds
        InputStream file = getResources().openRawResource(R.raw.birds);
        List<Bird> birds = BirdMarshaller.parseBirdsFromInputStream(file);
        		
		final int birdID = getIntent().getExtras().getInt("birdID");		
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
    	
    	// Combine seasons into a map so we can show multiple  polygons per season (this is hacky)
    	final List<ArrayList<Integer>> seasonPolygons = new ArrayList<ArrayList<Integer>>();
    	final List<String> seasonNames = new ArrayList<String>();
    	
    	for(int i=0; i < seasons.size(); i++)
    	{
    		// Add the season if it doesn't exist
    		if( ! seasonNames.contains(seasons.get(i).getName()))
    		{
    			seasonNames.add(seasons.get(i).getName());
    			seasonPolygons.add(new ArrayList<Integer>());
    		}
    		
    		// Add the index
    		int seasonID = seasonNames.indexOf(seasons.get(i).getName());
    		seasonPolygons.get(seasonID).add(i);
    		
    		System.out.println("For " + seasonNames.get(seasonID) + ", we have " + seasonPolygons.get(seasonID).toString());
    	}
    	    	
    	String notice = "";        
        notice += "Areas the bird is in all year is shown in green. ";
        notice += "Use the slider to change seasons. The current season is shown in red.";
        
        resetMap.setText(notice);
        
        // Set the all year polygons visible
        for(int id : seasonPolygons.get(0))
        {
        	polygons.get(id).setVisible(true);
        }
        
        // Set the next season's polygon visible
        final int startingSeason = 1;
        
        // Make the next season's polygons visible
        for(int id : seasonPolygons.get(1))
        {
        	polygons.get(id).setVisible(true);
        }
        
        seasonNameDisplay.setText(seasonNames.get(1));
        
        // Set starting map position
        map.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(37, -100)));
    	
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
				int numChoosableSeasons = seasonNames.size() - startingSeason;
				int divisionSize = 4000 / (numChoosableSeasons);
				
				int seasonID =(int) Math.ceil((progress) / divisionSize) + startingSeason;
				
				//location.setText(" " + divisionSize + " " + progress + " " + seasonID);
				
				seasonNameDisplay.setText(" " + seasonNames.get(seasonID));
				
				// Blank out the other seasons
				for (int i=startingSeason; i < seasons.size(); i++)
				{
					polygons.get(i).setVisible(false);
				}
				
				// Make this season visible
				for(int id : seasonPolygons.get(seasonID))
		        {
		        	polygons.get(id).setVisible(true);
		        }
			}
		});
    }
   
    
    long map(long x, long in_min, long in_max, long out_min, long out_max)
    {
      return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }
    
    
}