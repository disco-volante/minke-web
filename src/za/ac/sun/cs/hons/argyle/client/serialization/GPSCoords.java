package za.ac.sun.cs.hons.argyle.client.serialization;


public class GPSCoords extends GWTSerializable {
    private double latitude, longitude;

    public GPSCoords() {
    }

    public GPSCoords(double latitude, double longitude) {
	this.latitude = latitude;
	this.longitude = longitude;
    }

    public double getLatitude() {
	return latitude;
    }

    public double getLongitude() {
	return longitude;
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null) {
	    return false;
	}
	if (!obj.getClass().equals(GPSCoords.class)) {
	    return false;
	}
	GPSCoords coords = (GPSCoords) obj;
	return getLatitude() == coords.getLatitude()
		&& getLongitude() == coords.getLongitude();
    }

}
