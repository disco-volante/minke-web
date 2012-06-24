package com.cs.shopper.client.gui;

import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Label;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.PushButton;

public class ShopListItem extends HorizontalPanel {
	private ShopListPanel sp;
	private Label lbl;
	private SpinEdit quantity;
	public ShopListItem(ShopListPanel sp,String name){
		this.sp = sp;
		lbl = new Label(name);
		add(lbl);
		quantity = new SpinEdit();
		quantity.setValue(1);
		add(quantity);
		PushButton pb = new PushButton();
		pb.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				remove();
			}
			
		});
		pb.setText("x");
		add(pb);
		
	}

	protected void remove() {
		sp.remove(this);
		
	}
	public String getText(){
		return lbl.getText();
	}

}
