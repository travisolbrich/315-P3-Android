package olbrich.csce315.birdbuddy.marshaller;

import java.util.ArrayList;
import java.util.List;

import olbrich.csce315.birdbuddy.models.Bird;
import olbrich.csce315.birdbuddy.models.Point;
import olbrich.csce315.birdbuddy.models.Season;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class BirdMarshaller extends DefaultHandler {

	private static final String SEASON_NAME = "season";
	private static final String MIGRATION_NAME = "migration";
	private static final String POINT_NAME = "point";
	private static final String BIRD_NAME = "bird";

	private Bird bird = null;
	private List<Bird> birds = new ArrayList<Bird>();

	private Season season = null;
	private List<Season> migration = new ArrayList<Season>();
	
	private Point point = null;
	private List<Point> points = new ArrayList<Point>();

	boolean mType = false;
	boolean sType = false;
	boolean pType = false;

	@Override
	    public void startElement(String uri, String localName, String qName, Attributes attributes)
	            throws SAXException {
	 
	        if (qName.equalsIgnoreCase(BIRD_NAME)) {
	            
	        	String idString = attributes.getValue("id");
	        	Long identifier = Long.valueOf(idString);
	        	
	        	String name = attributes.getValue("name");
	        	String description = attributes.getValue("description");
	            
	            Bird bird = new Bird(identifier);
	            bird.setName(name);
	            bird.setDescription(description);
	            
	            this.bird = bird;
	        } else if (qName.equalsIgnoreCase(SEASON_NAME)) {
	        	String seasonName = attributes.getValue("name");
				this.season = new Season(seasonName);
				this.points = new ArrayList<Point>();
	        } else if (qName.equalsIgnoreCase(MIGRATION_NAME)) {
	        	this.migration = new ArrayList<Season>();
	        } else if (qName.equalsIgnoreCase(POINT_NAME)) {

	        	String latString = attributes.getValue("lat");
	        	String longString = attributes.getValue("long");

	        	Double latitude = Double.valueOf(latString);
	        	Double longitude = Double.valueOf(longString);
	        	
	            this.point = new Point(latitude, longitude);
	        }
	    }

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (qName.equalsIgnoreCase(BIRD_NAME)) {
            birds.add(this.bird);
            this.bird = null;
		} else if (qName.equalsIgnoreCase(SEASON_NAME)) {
			
			this.season.setPoints(this.points);
			migration.add(this.season);
			
			this.season = null;
			this.points = null;
		} else if (qName.equals(MIGRATION_NAME)) {
			this.bird.setMigrations(this.migration);
			this.migration = null;
		} else if (qName.equals(POINT_NAME)) {
			this.points.add(this.point);
			this.point = null;
		}
	}

	@Override
	public void characters(char ch[], int start, int length)
			throws SAXException {

	}
}
