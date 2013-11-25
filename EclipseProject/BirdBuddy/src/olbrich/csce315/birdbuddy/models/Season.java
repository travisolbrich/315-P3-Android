package olbrich.csce315.birdbuddy.models;

import java.util.ArrayList;
import java.util.List;

public class Season {

	private final String name;
	private List<Point> points = new ArrayList<Point>();

	public Season(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public List<Point> getPoints() {
		return points;
	}

	public void setPoints(List<Point> points) {
		this.points = points;
	}
}
