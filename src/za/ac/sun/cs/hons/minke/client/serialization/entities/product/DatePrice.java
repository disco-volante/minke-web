package za.ac.sun.cs.hons.minke.client.serialization.entities.product;

import java.util.Date;

import za.ac.sun.cs.hons.minke.client.serialization.entities.IsEntity;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class DatePrice extends IsEntity implements Comparable<DatePrice> {
	

	private Date date;
	private int price;
	private long branchProductID;

	public DatePrice() {
	}

	public DatePrice(Date date, int price, long branchProductID) {
		super();
		setDate(date);
		setPrice(price);
		setBranchProductID(branchProductID);
	}

	public void setDate(Date date) {
		this.date = (Date) date.clone();
	}

	public void setDate(long time) {
		this.date = new Date(time);
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getDate() {
		return (Date) date.clone();
	}

	public double getActualPrice() {
		return (double) price / 100;
	}

	public int getPrice() {
		return price;
	}

	public void setBranchProductID(long branchProductID) {
		this.branchProductID = branchProductID;
	}

	public long getBranchProductID() {
		return branchProductID;
	}

	@Override
	public int compareTo(DatePrice datePrice) {
		if (datePrice == null) {
			return 1;
		} else if (date == null) {
			return -1;
		}
		int cd;
		if ((cd = date.compareTo(datePrice.getDate())) != 0) {
			return cd;
		}
		return ((Integer) price).compareTo(datePrice.getPrice());
	}
	
	@Override
	public String toString() {
		return "R " + price+" on "+ date.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ (int) (branchProductID ^ (branchProductID >>> 32));
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + price;
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
		DatePrice other = (DatePrice) obj;
		if (branchProductID != other.branchProductID)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (price != other.price){
			return false;
		}else if(getID() != other.getID()){
			return false;
		}
		return true;
	}

}
