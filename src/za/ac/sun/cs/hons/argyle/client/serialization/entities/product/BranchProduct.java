package za.ac.sun.cs.hons.argyle.client.serialization.entities.product;

import javax.persistence.Embedded;
import javax.persistence.Id;

import za.ac.sun.cs.hons.argyle.client.serialization.entities.IsEntity;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.store.Branch;

import com.googlecode.objectify.annotation.Entity;

@Entity
public class BranchProduct extends IsEntity implements
	Comparable<BranchProduct> {
    @Id
    private Long id;
    @Embedded
    private Product product;
    private long productID;
    @Embedded
    private Branch branch;
    private long branchID;
    @Embedded
    private DatePrice datePrice;
    private long datePriceID;

    public BranchProduct() {
    }

    public BranchProduct(Product product, Branch branch, DatePrice datePrice) {
	super();
	setProduct(product);
	setBranch(branch);
	setDatePrice(datePrice);
    }

    public long getID() {
	return id;
    }

    public void setID(long id) {
	this.id = id;
    }

    public Product getProduct() {
	return product;
    }

    public void setProduct(Product product) {
	this.product = product;
	this.productID = product.getID();

    }

    public Branch getBranch() {
	return branch;
    }

    public void setBranch(Branch branch) {
	this.branch = branch;
	this.branchID = branch.getID();
    }

    public DatePrice getDatePrice() {
	return datePrice;
    }

    public void setDatePrice(DatePrice datePrice) {
	if (datePrice != null) {
	    this.datePrice = datePrice;
	    this.datePriceID = datePrice.getID();
	}
    }

    public long getProductID() {
	return productID;
    }

    public long getBranchID() {
	return branchID;
    }

    public long getDatePriceID() {
	return datePriceID;
    }

    public String toString() {
	return product.toString();
    }

    @Override
    public int compareTo(BranchProduct o) {
	if (o == null || o.getDatePrice() == null) {
	    return 1;
	} else if (datePrice == null) {
	    return -1;
	}
	int cp;
	if ((cp = ((Double) datePrice.getPrice()).compareTo((Double) o
		.getDatePrice().getPrice())) != 0) {
	    return cp;
	}
	return datePrice.compareTo(o.getDatePrice());
    }
}
