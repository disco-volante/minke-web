package za.ac.sun.cs.hons.minke.client.serialization.entities.product;

import javax.persistence.Embedded;

import za.ac.sun.cs.hons.minke.client.serialization.entities.IsEntity;
import za.ac.sun.cs.hons.minke.client.serialization.entities.store.Branch;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class BranchProduct extends IsEntity implements
		Comparable<BranchProduct> {
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		if (product != null) {
			this.product = product;
			this.productID = product.getID();
		}

	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		if (branch != null) {
			this.branch = branch;
			this.branchID = branch.getID();
		}
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

	@Override
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
		if ((cp = ((Integer) datePrice.getPrice()).compareTo(o.getDatePrice()
				.getPrice())) != 0) {
			return cp;
		}
		return datePrice.compareTo(o.getDatePrice());
	}
}
