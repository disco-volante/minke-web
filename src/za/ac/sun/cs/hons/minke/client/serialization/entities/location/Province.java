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
		if (getCountry() == null) {
			return getName();
		}
		return getName() + ", " + getCountry().toString();
	}

	public void setCountryID(long countryID) {
		this.countryID = countryID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + (int) (countryID ^ (countryID >>> 32));
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
		Province other = (Province) obj;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (countryID != other.countryID) {
			return false;
		} else if (!getName().equals(other.getName())) {
			return false;
		} else if (getID() != other.getID()) {
			return false;
		}
		return true;
	}

}
