package olbrich.csce315.birdbuddy.models;


import java.util.Date;

public class Point implements Comparable<Point> {

    private Date time;

	private Double latitude;
	
	private Double longitude;

    public Point(Double latitude, Double longitude) {};

    /**
     * Returns the time at which this point was taken.
     * @return
     */
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public int compareTo(Point point) {
        int comparison = (this.time != null) ? 1 : 0;
        Date pointTime = point.getTime();

        if (pointTime != null) {
            if (this.time != null) {
                comparison = this.time.compareTo(pointTime);
            } else {
                comparison = -1;
            }
        }

        return comparison;
    }
}
