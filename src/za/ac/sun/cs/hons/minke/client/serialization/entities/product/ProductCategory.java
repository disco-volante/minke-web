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

}
