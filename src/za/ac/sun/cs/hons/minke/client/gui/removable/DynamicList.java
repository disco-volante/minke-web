package za.ac.sun.cs.hons.minke.client.gui.removable;


import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class DynamicList extends ResizeComposite {
	interface Binder extends UiBinder<ScrollPanel, DynamicList> {
	}

	private static final Binder binder = GWT.create(Binder.class);
	@UiField
	protected VerticalPanel itemList;
	public DynamicList(){
		initWidget(binder.createAndBindUi(this));
	}
	
	public void removeItem(Removable item) {
		itemList.remove(item);
	}

	public void addItem(Removable item) {
		itemList.add(item);
	}

	public void clear() {
		itemList.clear();		
	}

}
