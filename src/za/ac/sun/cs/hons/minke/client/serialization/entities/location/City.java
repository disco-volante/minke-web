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
		if( getProvince() == null){
			return getName();
		}
		return getName() + ", " + getProvince().toString();
	}

	public void setProvinceID(long provinceID) {
		this.provinceID = provinceID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((province == null) ? 0 : province.hashCode());
		result = prime * result + (int) (provinceID ^ (provinceID >>> 32));
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
		City other = (City) obj;
		if (province == null) {
			if (other.province != null)
				return false;
		} else if (!province.equals(other.province)){
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
		if (provinceID != other.provinceID)
			return false;
		return true;
	}

}
