package com.cs.shopper.client.gui;

import com.google.gwt.user.client.ui.ListBox;

public class MyListBox extends ListBox {
	public MyListBox(boolean dropdown){
		super();
		if(!dropdown) setVisibleItemCount(3);
	}
	public String curItem(){
		if(this.getItemCount()==0) return "";
		int i = getSelectedIndex();
		if(i == -1) {
			setSelectedIndex(0);
			i = 0;
		}
		return getItemText(i);
	}

}