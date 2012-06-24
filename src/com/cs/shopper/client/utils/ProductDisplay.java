package com.cs.shopper.client.utils;

import java.util.Set;

import com.cs.shopper.client.entities.product.ProductCategory;
import com.cs.shopper.client.entities.product.ProductData;


public interface ProductDisplay {

	void showProductCategories(Set<ProductCategory> set);

	void showProducts(Set<ProductData> set);

}
