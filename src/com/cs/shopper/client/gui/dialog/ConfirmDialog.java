package com.cs.shopper.client.gui.dialog;

import com.cs.shopper.client.Shopper;

public class ConfirmDialog extends MyDialog {
	public ConfirmDialog(Shopper shopper,String title) {
		super(shopper,title);
		setDefaultBtnText("Ok");

	}
	public ConfirmDialog(Shopper shopper,String title, String msg) {
		super(shopper,title);
		addLabel(msg,0,1);
		setDefaultBtnText("Ok");
	}

}
