package za.ac.sun.cs.hons.minke.client.serialization.entities.location;

import javax.persistence.Embedded;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class CityLocation extends Location {
	@Embedded
	private City city;
	private long cityID;

	public CityLocation() {
	}

	public CityLocation(String name, City city, double lat, double lon) {
		super(name, lat, lon);
		setCity(city);
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		if (city != null) {
			this.city = city;
			this.cityID = city.getID();
		}

	}

	public long getCityID() {
		return cityID;
	}

	@Override
	public String toString() {
		if( getCity() == null){
			return getName();
		}
		return getName() + ", " + getCity().toString();
	}

	public void setCityID(long cityID) {
		this.cityID = cityID;		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + (int) (cityID ^ (cityID >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CityLocation other = (CityLocation) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (cityID != other.cityID){
			return false;
		}else if(getLat() != other.getLat()){
			return false;
		}else if(getLon() != other.getLon()){
			return false;
		}else if(!getName().equals(other.getName())){
			return false;
		}else if(getID() != other.getID()){
			return false;
		}
		return true;
	}

}
