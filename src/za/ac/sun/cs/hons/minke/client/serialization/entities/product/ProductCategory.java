package za.ac.sun.cs.hons.minke.client.serialization.entities.product;

import javax.persistence.Embedded;

import za.ac.sun.cs.hons.minke.client.serialization.entities.IsEntity;

import com.googlecode.objectify.annotation.Subclass;

/**
 * DB Entity used to store data about a {@link Product}'s categories, e.g. Milk,
 * Dairy, Consumables.
 * 
 * @author godfried
 * 
 */
@Subclass
public class ProductCategory extends IsEntity {
    private long categoryID;
    private long productID;
    @Embedded
    private Category category;
    @Embedded
    private Product product;

    public ProductCategory() {
    }

    public ProductCategory(Category category, Product product) {
	super();
	setCategory(category);
	setProduct(product);
    }

    public Category getCategory() {
	return category;
    }

    public long getCategoryID() {
	return categoryID;
    }

    public void setCategory(Category category) {
	if (category != null) {
	    this.category = category;
	    this.categoryID = category.getID();
	}
    }

    public Product getProduct() {
	return product;
    }

    public long getProductID() {
	return productID;
    }

    public void setProduct(Product product) {
	if (product != null) {
	    this.product = product;
	    this.productID = product.getID();
	}
    }

    @Override
    public String toString() {
	return product.toString() + " : " + category.toString(); 

    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
		result = prime * result + (int) (categoryID ^ (categoryID >>> 32));
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
		ProductCategory other = (ProductCategory) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (categoryID != other.categoryID)
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
