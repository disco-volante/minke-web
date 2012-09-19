package za.ac.sun.cs.hons.minke.client.serialization.entities;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum EntityID implements IsSerializable{
	CITY(1), CITYLOCATION(2), COUNTRY(3), PROVINCE(4), BRANCHPRODUCT(5), BRAND(
			6), CATEGORY(7), PRODUCT(8), BRANCH(9), STORE(10);
	private int id;

	EntityID(int id) {
		this.id = id;
	}

	public int getID() {
		return id;
	}
}
