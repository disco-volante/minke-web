package com.cs.shopper.client.gui.dialog;

import com.cs.shopper.client.Shopper;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

public class MyDialog extends DialogBox {

	private FlexTable table;
	private Button cancelButton;
	private Shopper shopper;
	public MyDialog(Shopper shopper, String title) {
		super();
		this.setShopper(shopper);
		setText(title);
		table = new FlexTable();
		cancelButton = new Button("Cancel");
		cancelButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		});
		addItem(cancelButton,2,1);
		setWidget(table);
	}
	protected void addItem(Widget widget,int row, int col){
		table.setWidget(row, col, widget);
	}
	protected void addLabel(String txt,int row, int col){
		table.setText(row, col, txt);
	}
	protected void setDefaultBtnText(String txt){
		cancelButton.setText(txt);
	}
	protected void setDefaultBtnPos(int row, int col){
		table.removeCell(2, 1);
		addItem(cancelButton,row,col);
	}
	public Shopper getShopper() {
		return shopper;
	}
	public void setShopper(Shopper shopper) {
		this.shopper = shopper;
	}
}