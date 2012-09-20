package za.ac.sun.cs.hons.minke.client.serialization;


public class GPSCoords extends GWTSerializable {
	private double lat, lon;

	public GPSCoords() {
	}

	public GPSCoords(double latitude, double longitude) {
		this.lat = latitude;
		this.lon = longitude;
	}


	public double getLatitude() {
		return lat;
	}

	public double getLongitude() {
		return lon;
	}


	@Override
	public String toString(){
		return "Latitude: "+lat+"; Longitude: "+lon+";";

	}
	public double distanceTo(double latitude, double longitude) {
		return Math.abs(lat - latitude) + Math.abs(lon - longitude);
	}
}
