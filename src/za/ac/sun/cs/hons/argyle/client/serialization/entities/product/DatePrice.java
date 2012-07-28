package za.ac.sun.cs.hons.argyle.client.serialization.entities.product;

import java.util.Date;

import javax.persistence.Id;

import za.ac.sun.cs.hons.argyle.client.serialization.entities.IsEntity;

import com.googlecode.objectify.annotation.Entity;

@Entity
public class DatePrice extends IsEntity implements Comparable<DatePrice> {
    @Id
    private Long   ID;
    private Date   date;
    private double price;
    private Long   branchProductID;

    public DatePrice() {
    }

    public DatePrice(long ID, Date date, double price, Long branchProductID) {
	super();
	this.ID = ID;
	this.date = date;
	this.price = price;
	setBranchProductID(branchProductID);
    }

    public long getID() {
	return ID;
    }

    public Date getDate() {
	return date;
    }

    public double getPrice() {
	return price;
    }

    public Long getBranchProductID() {
	return branchProductID;
    }

    public void setBranchProductID(Long branchProductID) {
	this.branchProductID = branchProductID;
    }

    public String toString() {
	return date.toString() + " " + price + " " + branchProductID;
    }

    @Override
    public int compareTo(DatePrice anotherDatePrice) {
	int compare = compareTo(anotherDatePrice.getDate());
	if (compare != 0) {
	    return compare;
	}
	return compareTo((Double) anotherDatePrice.getPrice());
    }

    private int compareTo(Date date) {
	return getDate().compareTo(date);
    }

    private int compareTo(Double price) {
	return ((Double) getPrice()).compareTo(price);
    }

    @Override
    public boolean equals(Object obj) {
	return obj instanceof DatePrice && getID() == ((DatePrice) obj).getID()
		&& getDate().equals(((DatePrice) obj).getDate())
		&& getPrice() == ((DatePrice) obj).getPrice();
    }

}
