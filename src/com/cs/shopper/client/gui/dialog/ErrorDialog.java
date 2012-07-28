package com.cs.shopper.client.gui.dialog;

import com.cs.shopper.client.Shopper;

public class ErrorDialog extends ConfirmDialog {

	public ErrorDialog(Shopper shopper,String title, String msg) {
		super(shopper,title,msg);
	}
	public ErrorDialog(Shopper shopper,String title, String[] msgs) {
		super(shopper,title);
		setDefaultBtnPos(9, 0);
		for(int i = 0; i < msgs.length; i ++){
			addLabel(msgs[i],i,0);
		}
	}

}
