package za.ac.sun.cs.hons.argyle.server.entities;

import java.util.Date;

import javax.persistence.Id;

import za.ac.sun.cs.hons.argyle.client.serialization.entities.IsEntity;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;

@Entity
public class DatePrice extends IsEntity {
    @Id
    private Long	       id;
    private Date	       date;
    private double	     price;
    private Key<BranchProduct> branchProductKey;

    public DatePrice() {
    }

    public DatePrice(Long id, Date date, double price,
	    Key<BranchProduct> branchProductKey) {
	super();
	this.id = id;
	this.date = date;
	this.price = price;
	this.branchProductKey = branchProductKey;
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

    public Key<BranchProduct> getBranchProductKey() {
	return branchProductKey;
    }

    public void setBranchProductKey(Key<BranchProduct> branchProductKey) {
	this.branchProductKey = branchProductKey;
    }
}
