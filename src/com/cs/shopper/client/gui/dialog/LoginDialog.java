package com.cs.shopper.client.gui.dialog;

import com.cs.shopper.client.Shopper;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

public class LoginDialog extends MyDialog{
	private TextBox username;
	private PasswordTextBox password;
	private boolean login;

	public LoginDialog(Shopper shopper) {
		super(shopper,"Login");
		setLogin(false);
		
		Button loginButton = new Button("Login");
		loginButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				login();

			}
		});
		addItem(loginButton,2,0);
	
		username = new TextBox();
		username.setText("Username");
		addLabel("Username",0,0);
		addItem(username,0,1);
		
		password = new PasswordTextBox();
		addLabel("Password",1,0);
		addItem(password,1,1);
	}


	protected void login(){
		setLogin(true);
		hide();
	}
	public boolean isLogin() {
		return login;
	}
	protected void setLogin(boolean login) {
		this.login = login;
	}


	public String getUsername() {
		// TODO Auto-generated method stub
		return username.getText();
	}
	public String getPassword() {
		// TODO Auto-generated method stub
		return password.getText();
	}
}
