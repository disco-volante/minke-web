package za.ac.sun.cs.hons.minke.client.serialization.entities.location;

import javax.persistence.Embedded;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class Province extends Location {
	@Embedded
	private Country country;
	private long countryID;

	public Province() {
	}

	public Province(String name, Country country) {
		super(name, 0, 0);
		setCountry(country);
	}

	public void setCountry(Country country) {
		if (country != null) {
			this.country = country;
			this.countryID = country.getID();
		}
	}

	public Country getCountry() {
		return country;
	}

	public long getCountryID() {
		return countryID;
	}

	@Override
	public String toString() {
		if( getCountry() == null){
			return getName();
		}
		return getName() + ", " + getCountry().toString();
	}

	public void setCountryID(long countryID) {
		this.countryID = countryID;
	}

}
