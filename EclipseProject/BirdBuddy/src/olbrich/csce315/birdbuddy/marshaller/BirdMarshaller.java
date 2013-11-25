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
	
	
	private List<Bird> birds = new ArrayList<Bird>();
	private List<Season> migration = new ArrayList<Season>();
	
	private Season season = null;
	private List<Point> points = new ArrayList<Point>();
	
	boolean sType = false;
	boolean pType = false;

	@Override
	    public void startElement(String uri, String localName, String qName, Attributes attributes)
	            throws SAXException {
	 
	        if (qName.equalsIgnoreCase(BIRD_NAME)) {
	            
	        	String idString = attributes.getValue("id");
	        	Long identifier = new Long(idString);
	        	
	        	String name = attributes.getValue("name");
	        	String description = attributes.getValue("description");
	            
	            Bird bird = new Bird(identifier);
	            bird.setName(name);
	            bird.setDescription(description);
	            
	            birds.add
	            
	        } else if (qName.equalsIgnoreCase("name")) {
	            //set boolean values for fields, will be used in setting Employee variables
	            bName = true;
	        } else if (qName.equalsIgnoreCase("age")) {
	            bAge = true;
	        } else if (qName.equalsIgnoreCase("gender")) {
	            bGender = true;
	        } else if (qName.equalsIgnoreCase("role")) {
	            bRole = true;
	        }
	    }

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (qName.equalsIgnoreCase("Employee")) {
			// add Employee object to list
			empList.add(emp);
		} else if (qName.equalsIgnoreCase(""))
	}

	@Override
	public void characters(char ch[], int start, int length)
			throws SAXException {

		if (bAge) {
			// age element, set Employee age
			emp.setAge(Integer.parseInt(new String(ch, start, length)));
			bAge = false;
		} else if (bName) {
			emp.setName(new String(ch, start, length));
			bName = false;
		} else if (bRole) {
			emp.setRole(new String(ch, start, length));
			bRole = false;
		} else if (bGender) {
			emp.setGender(new String(ch, start, length));
			bGender = false;
		}
	}
}
