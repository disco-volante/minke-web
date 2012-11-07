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
		if (product != null) {
			return product.toString();
		}
		return super.toString();
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

	public void setBranchID(long branchID) {
		this.branchID = branchID;
	}

	public void setDatePriceID(long datePriceID) {
		this.datePriceID = datePriceID;
	}

	public void setProductID(long productID) {
		this.productID = productID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((branch == null) ? 0 : branch.hashCode());
		result = prime * result + (int) (branchID ^ (branchID >>> 32));
		result = prime * result
				+ ((datePrice == null) ? 0 : datePrice.hashCode());
		result = prime * result + (int) (datePriceID ^ (datePriceID >>> 32));
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + (int) (productID ^ (productID >>> 32));
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
		BranchProduct other = (BranchProduct) obj;
		if (branch == null) {
			if (other.branch != null)
				return false;
		} else if (!branch.equals(other.branch))
			return false;
		if (branchID != other.branchID)
			return false;
		if (datePrice == null) {
			if (other.datePrice != null)
				return false;
		} else if (!datePrice.equals(other.datePrice))
			return false;
		if (datePriceID != other.datePriceID)
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (productID != other.productID){
			return false;
		}else if(getID() != other.getID()){
			return false;
		}
		return true;
	}
}
