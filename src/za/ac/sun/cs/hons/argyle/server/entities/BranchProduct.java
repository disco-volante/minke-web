package za.ac.sun.cs.hons.argyle.server.entities;

import javax.persistence.Id;

import za.ac.sun.cs.hons.argyle.client.serialization.entities.IsEntity;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;

@Entity
public class BranchProduct extends IsEntity {
    @Id
    private Long	   id;
    private String	 name;
    private Key<Product>   productKey;
    private Key<Branch>    branchKey;
    private Key<DatePrice> datePrice;

    public BranchProduct() {
    }

    public BranchProduct(Long id, String name, Key<Product> productKey,
	    Key<Branch> branchKey, Key<DatePrice> datePrice) {
	super();
	this.id = id;
	this.name = name;
	this.productKey = productKey;
	this.branchKey = branchKey;
	this.datePrice = datePrice;
    }

    public long getID() {
	return id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Key<Product> getProductKey() {
	return productKey;
    }

    public void setProductKey(Key<Product> productKey) {
	this.productKey = productKey;
    }

    public Key<Branch> getBranchKey() {
	return branchKey;
    }

    public void setBranchKey(Key<Branch> branchKey) {
	this.branchKey = branchKey;
    }

    public Key<DatePrice> getDatePrice() {
	return datePrice;
    }

    public void setDatePrice(Key<DatePrice> datePrice) {
	this.datePrice = datePrice;
    }

    public void setID(long id) {
	this.id = id;
    }

}
