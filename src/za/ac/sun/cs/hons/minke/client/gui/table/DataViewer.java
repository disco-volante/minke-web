package za.ac.sun.cs.hons.minke.client.gui.table;

import java.util.HashSet;
import java.util.List;

import za.ac.sun.cs.hons.minke.client.gui.WebPage;
import za.ac.sun.cs.hons.minke.client.gui.button.ImageButton;
import za.ac.sun.cs.hons.minke.client.gui.popup.EntityPopup;
import za.ac.sun.cs.hons.minke.client.serialization.entities.location.City;
import za.ac.sun.cs.hons.minke.client.serialization.entities.location.CityLocation;
import za.ac.sun.cs.hons.minke.client.serialization.entities.location.Country;
import za.ac.sun.cs.hons.minke.client.serialization.entities.location.Province;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.Category;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.DatePrice;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.Product;
import za.ac.sun.cs.hons.minke.client.serialization.entities.store.Branch;
import za.ac.sun.cs.hons.minke.client.serialization.entities.store.Store;
import za.ac.sun.cs.hons.minke.client.util.Constants;
import za.ac.sun.cs.hons.minke.client.util.FIELDS;
import za.ac.sun.cs.hons.minke.client.util.ImageUtils;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class DataViewer extends TableView {

	private String[] headings;

	public DataViewer(WebPage webPage) {
		super(webPage);
		viewButton.setText("Choose Entity");
	}

	@Override
	protected void addItem(final Object obj, int pos) {
		ImageButton editButton = new ImageButton(ImageUtils.getImages().admin()) {
			@Override
			protected void clickAction() {
				editItem(obj);
			}

		};
		HorizontalPanel holder = new HorizontalPanel();
		holder.add(editButton);
		if (obj instanceof Category) {
			table.setText(pos, 0, ((Category) obj).getName());
		} else if (obj instanceof Product) {
			table.setText(pos, 0, ((Product) obj).getName());
			table.setText(pos, 1, ((Product) obj).getBrand().toString());
			table.setText(pos, 2, String.valueOf(((Product) obj).getSize()));
			table.setText(pos, 3, ((Product) obj).getMeasurement());
		} else if (obj instanceof BranchProduct) {
			table.setText(pos, 0, ((BranchProduct) obj).getProduct().toString());
			table.setText(pos, 1, ((BranchProduct) obj).getBranch().toString());
			table.setText(pos, 2, ((BranchProduct) obj).getDatePrice().toString());
		} else if (obj instanceof Branch) {
			table.setText(pos, 0, ((Branch) obj).getName());
			table.setText(pos, 1, ((Branch) obj).getStore().toString());
			table.setText(pos, 2, ((Branch) obj).getLocation().toString());
		} else if (obj instanceof Store) {
			table.setText(pos, 0, ((Store) obj).getName());
		} else if (obj instanceof CityLocation) {
			table.setText(pos, 0, ((CityLocation) obj).getName());
			table.setText(pos, 1, ((CityLocation) obj).getCity().toString());
			table.setText(pos, 2, String.valueOf(((CityLocation) obj).getLat()));
			table.setText(pos, 3, String.valueOf(((CityLocation) obj).getLon()));

		} else if (obj instanceof City) {
			table.setText(pos, 0, ((City) obj).getName());
			table.setText(pos, 1, ((City) obj).getProvince().toString());
			table.setText(pos, 2, String.valueOf(((City) obj).getLat()));
			table.setText(pos, 3, String.valueOf(((City) obj).getLon()));
		} else if (obj instanceof Province) {
			table.setText(pos, 0, ((Province) obj).getName());
			table.setText(pos, 1, ((Province) obj).getCountry().toString());
		} else if (obj instanceof Country) {
			table.setText(pos, 0, ((Country) obj).getName());
		} else if (obj instanceof DatePrice) {
			table.setText(pos, 0, ((DatePrice) obj).getDate().toString());
			table.setText(pos, 1, String.valueOf(((DatePrice) obj).getActualPrice()));
		}
		table.setWidget(pos, getTableCols(), holder);
	}

	protected void editItem(Object item) {
		// TODO Auto-generated method stub

	}

	@Override
	protected int getTableCols() {
		if (headings == null) {
			return 0;
		}
		return headings.length + 1;
	}

	@Override
	protected TABLE getTableType() {
		return TABLE.ADMIN;
	}

	@Override
	protected String[] getHeadings() {
		return headings;
	}

	@Override
	@UiHandler("viewButton")
	void onButtonClicked(ClickEvent event) {
		EntityPopup entity = new EntityPopup(this);
		entity.center();
	}

	public void getEntities(String entity) {
		setEntity(entity);
		webPage.requestEntities(entity);
	}

	private void setEntity(String entity) {
		if (entity.equals(Constants.CATEGORY)) {
			headings = FIELDS.CATEGORY;
		} else if (entity.equals(Constants.PRODUCT)) {
			headings = FIELDS.PRODUCT;
		} else if (entity.equals(Constants.BRAND)) {
			headings = FIELDS.BRAND;
		} else if (entity.equals(Constants.BRANCHPRODUCT)) {
			headings = FIELDS.BRANCHPRODUCT;
		} else if (entity.equals(Constants.BRANCH)) {
			headings = FIELDS.BRANCH;
		} else if (entity.equals(Constants.STORE)) {
			headings = FIELDS.STORE;
		} else if (entity.equals(Constants.CITYLOCATION)) {
			headings = FIELDS.CITYLOCATION;
		} else if (entity.equals(Constants.CITY)) {
			headings = FIELDS.CITY;
		} else if (entity.equals(Constants.PROVINCE)) {
			headings = FIELDS.PROVINCE;
		} else if (entity.equals(Constants.COUNTRY)) {
			headings = FIELDS.COUNTRY;
		} else if (entity.equals(Constants.DATEPRICE)) {
			headings = FIELDS.DATEPRICE;
		}
	}

	public void setEntities(List<?> result) {
		setItemSet(new HashSet<Object>(result));
	}
}
