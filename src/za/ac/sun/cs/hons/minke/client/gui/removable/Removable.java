package za.ac.sun.cs.hons.minke.client.gui.removable;

import za.ac.sun.cs.hons.minke.client.gui.button.RemoveButton;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.Widget;

public class Removable extends ResizeComposite {

	interface Binder extends UiBinder<Widget, Removable> {
	}

	private static final Binder binder = GWT.create(Binder.class);

	@UiField(provided = true)
	Label lbl;
	@UiField(provided = true)
	RemoveButton removeBtn;
	@UiField(provided = true)
	Widget extra;
	protected DynamicList list;
	private String name;

	public Removable(DynamicList list, String name, Widget widget) {
		setName(name);
		this.list = list;
		lbl = new Label(name);
		removeBtn = new RemoveButton(this);
		extra = widget;
		initWidget(binder.createAndBindUi(this));
	}

	public Widget getExtraWidget() {
		return extra;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void remove() {
		list.removeItem(this);
	}

}
