package za.ac.sun.cs.hons.minke.client.serialization.entities.location;

import javax.persistence.Embedded;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class City extends Location {
	@Embedded
	private Province province;
	private long provinceID;

	public City() {
	}

	public City(String name, Province province, double lat, double lon) {
		super(name, lat, lon);
		setProvince(province);
	}

	public void setProvince(Province province) {
		if (province != null) {
			this.province = province;
			this.provinceID = province.getID();
		}
	}

	public Province getProvince() {
		return province;
	}

	public long getProvinceID() {
		return provinceID;
	}

	@Override
	public String toString() {
		return getName() + ", " + getProvince().toString();
	}

	public void setProvinceID(long provinceID) {
		this.provinceID = provinceID;
	}

}
