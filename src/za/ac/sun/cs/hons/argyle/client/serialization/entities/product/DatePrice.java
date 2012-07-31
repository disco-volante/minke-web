package za.ac.sun.cs.hons.argyle.client.serialization.entities.product;

import java.util.Date;

import javax.persistence.Id;

import za.ac.sun.cs.hons.argyle.client.serialization.entities.IsEntity;

import com.googlecode.objectify.annotation.Entity;

@Entity
public class DatePrice extends IsEntity implements Comparable<DatePrice> {
    @Id
    private Long id;
    private Date date;
    private double price;
    private long branchProductID;

    public DatePrice() {
    }

    public DatePrice(Date date, double price, long branchProductID) {
	super();
	setDate(date);
	setPrice(price);
	setBranchProductID(branchProductID);
    }

    public void setID(long id) {
	this.id = id;
    }

    public void setDate(Date date) {
	this.date = date;
    }

    public void setPrice(double price) {
	this.price = price;
    }

    public long getID() {
	return id;
    }

    public Date getDate() {
	return date;
    }

    public double getPrice() {
	return price;
    }

    public void setBranchProductID(long branchProductID) {
	this.branchProductID = branchProductID;
    }

    public long getBranchProductID() {
	return branchProductID;
    }

    public int compareTo(DatePrice datePrice) {
	if (datePrice == null || datePrice.getDate() == null) {
	    return 1;
	} else if (date == null) {
	    return -1;
	}
	int cd;
	if ((cd = date.compareTo(datePrice.getDate())) != 0) {
	    return cd;
	}
	return ((Double) price).compareTo((Double) datePrice.getPrice());
    }

}
