package za.ac.sun.cs.hons.minke.client.gui.table;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import za.ac.sun.cs.hons.minke.client.gui.WebPage;
import za.ac.sun.cs.hons.minke.client.gui.button.ImageButton;
import za.ac.sun.cs.hons.minke.client.gui.popup.CreatePopup;
import za.ac.sun.cs.hons.minke.client.gui.popup.EditPopup;
import za.ac.sun.cs.hons.minke.client.gui.popup.EntityPopup;
import za.ac.sun.cs.hons.minke.client.serialization.entities.IsEntity;
import za.ac.sun.cs.hons.minke.client.serialization.entities.location.City;
import za.ac.sun.cs.hons.minke.client.serialization.entities.location.CityLocation;
import za.ac.sun.cs.hons.minke.client.serialization.entities.location.Country;
import za.ac.sun.cs.hons.minke.client.serialization.entities.location.Province;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.Brand;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.Category;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.Product;
import za.ac.sun.cs.hons.minke.client.serialization.entities.store.Branch;
import za.ac.sun.cs.hons.minke.client.serialization.entities.store.Store;
import za.ac.sun.cs.hons.minke.client.util.Constants;
import za.ac.sun.cs.hons.minke.client.util.FIELDS;
import za.ac.sun.cs.hons.minke.client.util.GuiUtils;
import za.ac.sun.cs.hons.minke.client.util.ImageUtils;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.SimplePanel;

public class DataViewer extends TableView {

	private String[] headings;

	public HashMap<String, IsEntity> brands = new HashMap<String, IsEntity>(),
			branches = new HashMap<String, IsEntity>(),
			products = new HashMap<String, IsEntity>(),
			stores = new HashMap<String, IsEntity>(),
			locations = new HashMap<String, IsEntity>(),
			cities = new HashMap<String, IsEntity>(),
			provinces = new HashMap<String, IsEntity>(),
			countries = new HashMap<String, IsEntity>();
	private String entity;
	private Button createButton;

	private int loading = 0;

	private Collection<String> measures = Arrays.asList("ml","l","mg","g","kg","ea");

	public DataViewer(WebPage webPage) {
		super(webPage);
		viewButton.setText("Choose Entity");
		createButton = new Button("New Entity");
		createButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				if (entity == null) {
					onButtonClicked(null);
				} else {
					new CreatePopup(DataViewer.this, entity).center();
				}

			}
		});
		addToFooter(createButton);
	}

	@Override
	protected void addItem(final Object obj, int pos) {
		ImageButton editButton = new ImageButton(ImageUtils.getImages().admin()) {
			@Override
			protected void clickAction() {
				editItem((IsEntity) obj);
			}

		};
		SimplePanel holder = new SimplePanel();
		holder.add(editButton);
		if (obj instanceof Category) {
			table.setText(pos, 0, ((Category) obj).getName());
		} else if (obj instanceof Product) {
			table.setText(pos, 0, ((Product) obj).getName());
			table.setText(pos, 1, ((Product) obj).getBrand().toString());
			table.setText(pos, 2, String.valueOf(((Product) obj).getSize()));
			table.setText(pos, 3, ((Product) obj).getMeasurement());
		} else if (obj instanceof Brand) {
			table.setText(pos, 0, ((Brand) obj).getName());
		} else if (obj instanceof BranchProduct) {
			table.setText(pos, 0, ((BranchProduct) obj).getProduct().toString());
			table.setText(pos, 1, ((BranchProduct) obj).getBranch().toString());
			table.setText(pos, 2, ((BranchProduct) obj).getDatePrice()
					.getDate().toString());
			table.setText(pos, 3, String.valueOf(((BranchProduct) obj)
					.getDatePrice().getActualPrice()));
		} else if (obj instanceof Branch) {
			table.setText(pos, 0, ((Branch) obj).getName());
			table.setText(pos, 1, ((Branch) obj).getStore().toString());
			table.setText(pos, 2, ((Branch) obj).getLocation().toString());
		} else if (obj instanceof Store) {
			table.setText(pos, 0, ((Store) obj).getName());
		} else if (obj instanceof CityLocation) {
			CityLocation cl = (CityLocation) obj;
			table.setText(pos, 0, cl.getName());
			table.setText(pos, 1, cl.getCity().toString());
			table.setText(pos, 2, String.valueOf(cl.getLat()));
			table.setText(pos, 3, String.valueOf(cl.getLon()));
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
		}
		table.setWidget(pos, getTableCols(), holder);
	}

	protected void editItem(IsEntity item) {
		if (loading > 0) {
			GuiUtils.showError("Loading", "Loading data please wait...");
		} else {
			new EditPopup(this, item).center();
		}
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
		loading  = 1;
		if (entity.equals(Constants.PRODUCT)) {
			requestSupportEntities(Constants.BRAND);
		} else if (entity.equals(Constants.BRANCH)) {
			requestSupportEntities(Constants.STORE);
			requestSupportEntities(Constants.CITYLOCATION);
		} else if (entity.equals(Constants.BRANCHPRODUCT)) {
			requestSupportEntities(Constants.BRANCH);
			requestSupportEntities(Constants.PRODUCT);
		} else if (entity.equals(Constants.CITYLOCATION)
				|| entity.equals(Constants.LOCATION)) {
			requestSupportEntities(Constants.CITY);
		} else if (entity.equals(Constants.CITY)) {
			requestSupportEntities(Constants.PROVINCE);
		} else if (entity.equals(Constants.PROVINCE)) {
			requestSupportEntities(Constants.COUNTRY);
		}
	}

	public Collection<String> getSupportEntities(String entity) {
		if (entity.equals(Constants.PRODUCT)) {
			return products.keySet();
		} else if (entity.equals(Constants.BRAND)) {
			return brands.keySet();
		} else if (entity.equals(Constants.BRANCH)) {
			return branches.keySet();
		} else if (entity.equals(Constants.STORE)) {
			return stores.keySet();
		} else if (entity.equals(Constants.CITYLOCATION)
				|| entity.equals(Constants.LOCATION)) {
			return locations.keySet();
		} else if (entity.equals(Constants.CITY)) {
			return cities.keySet();
		} else if (entity.equals(Constants.PROVINCE)) {
			return provinces.keySet();
		}else if (entity.equals(Constants.MEASURE)) {
			return measures ;
		}
		return null;
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
		} else {
			return;
		}
		this.entity = entity;
		initTable(getTableCols(), getHeadings());
	}

	public void setEntities(List<? extends IsEntity> result) {
		if (result != null) {
			setItemSet(new HashSet<Object>(result));
		}
		loading --;
	}

	private void requestSupportEntities(String entity) {
		loading ++;
		webPage.requestSupportEntities(entity);
	}

	public void delete(IsEntity item) {
		entity = item.getClass().getName()
				.substring(item.getClass().getName().lastIndexOf('.') + 1);
		webPage.delete(item);
	}

	public void update(IsEntity item) {
		entity = item.getClass().getName()
				.substring(item.getClass().getName().lastIndexOf('.') + 1);
		webPage.update(item);
	}

	public void notifySuccess() {
		getEntities(entity);
	}

	public void create(IsEntity item) {
		entity = item.getClass().getName()
				.substring(item.getClass().getName().lastIndexOf('.') + 1);
		webPage.update(item);
	}

	public void setSupportEntities(List<? extends IsEntity> result) {
		if (result != null && result.size() > 0) {
			if (result.get(0) instanceof Product) {
				createSupportEntity(result, products);
			} else if (result.get(0) instanceof Brand) {
				createSupportEntity(result, brands);
			} else if (result.get(0) instanceof Branch) {
				createSupportEntity(result, branches);
			} else if (result.get(0) instanceof Store) {
				createSupportEntity(result, stores);
			} else if (result.get(0) instanceof CityLocation) {
				createSupportEntity(result, locations);
			} else if (result.get(0) instanceof City) {
				createSupportEntity(result, cities);
			} else if (result.get(0) instanceof Province) {
				createSupportEntity(result, provinces);
			} else if (result.get(0) instanceof Country) {
				createSupportEntity(result, countries);
			}
		}
		loading --;
	}

	private void createSupportEntity(List<? extends IsEntity> entities,
			HashMap<String, IsEntity> map) {
		map.clear();
		for (IsEntity e : entities) {
			map.put(e.toString(), e);
		}
	}

}
