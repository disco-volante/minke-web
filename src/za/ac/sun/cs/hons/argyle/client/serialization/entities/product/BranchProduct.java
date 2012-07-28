package za.ac.sun.cs.hons.argyle.client.serialization.entities.product;

import javax.persistence.Embedded;
import javax.persistence.Id;

import za.ac.sun.cs.hons.argyle.client.serialization.entities.IsEntity;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.store.Branch;

import com.googlecode.objectify.annotation.Entity;

@Entity
public class BranchProduct extends IsEntity {
    @Id
    private Long      ID;
    @Embedded
    private Product   product;
    @Embedded
    private Branch    branch;
    @Embedded
    private DatePrice datePrice;

    public BranchProduct() {
    }

    public BranchProduct(long ID, Product product, Branch branch,
	    DatePrice datePrice) {
	this.ID = ID;
	this.product = product;
	this.branch = branch;
	setDatePrice(datePrice);
    }

    public long getID() {
	return ID;
    }

    public Product getProduct() {
	return product;
    }

    public Branch getBranch() {
	return branch;
    }

    public DatePrice getDatePrice() {
	return datePrice;
    }

    public void setDatePrice(DatePrice datePrice) {
	if(datePrice == null){
	    return;
	}
	if (getDatePrice() == null
		|| datePrice.getDate().after(getDatePrice().getDate())) {
	    this.datePrice = datePrice;
	    if (datePrice.getBranchProductID() == null) {
		datePrice.setBranchProductID(getID());
	    }
	}
    }

    public String toString() {
	return product.toString() + " " + branch.toString();
    }

}
