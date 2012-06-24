package com.cs.shopper.client.gui.dialog;

import java.util.Set;

import com.cs.shopper.client.Shopper;
import com.cs.shopper.client.entities.location.City;
import com.cs.shopper.client.entities.user.User;
import com.cs.shopper.client.utils.LocationDisplay;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;

public class RegisterDialog extends MyDialog implements LocationDisplay {
	private SuggestBox city;
	private TextBox username;
	private PasswordTextBox password;
	private TextBox email;
	private User user;
	private boolean registered;
	private MultiWordSuggestOracle cityOracle;

	public RegisterDialog(Shopper shopper) {
		super(shopper, "Register");
		setVisible(false);
		user = new User();
		registered = false;
		setDefaultBtnPos(6, 1);
		Button registerButton = new Button("Register");
		registerButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				checkFields();
			}
		});
		addItem(registerButton, 6, 0);

		username = new TextBox();
		addLabel("Username", 0, 0);
		addItem(username, 0, 1);

		password = new PasswordTextBox();
		addLabel("Password", 1, 0);
		addItem(password, 1, 1);
		cityOracle = new MultiWordSuggestOracle();
		city = new SuggestBox(cityOracle);
		addLabel("City", 4, 0);
		addItem(city, 4, 1);
		requestCities();

		email = new TextBox();
		addLabel("Email Address", 5, 0);
		addItem(email, 5, 1);


	}

	protected void requestCities() {
		getShopper().getData().setLd(this);
		getShopper().addCities(true);

	}

	protected void checkFields() {
		boolean[] errs = new boolean[9];
		if (username.getText().length() < 8 || username.getText().length() > 25)
			errs[0] = true;
		if (!username.getText().matches("^\\w+$"))
			errs[1] = true;
		if (password.getText().length() < 8 || password.getText().length() > 25)
			errs[2] = true;
		if (!password.getText().matches("^.*\\d+.*"))
			errs[3] = true;
		if (!password.getText().matches("^.*[a-z]+.*"))
			errs[4] = true;
		if (!password.getText().matches("^.*[A-Z]+.*"))
			errs[5] = true;
		if (password.getText().matches("^.*\\s+.*$"))
			errs[6] = true;
		if (!password.getText().matches("^\\w+$"))
			errs[7] = true;
		if (!email
				.getText()
				.matches(
						"^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))
			errs[8] = true;
		if (errs[0] || errs[1] || errs[2] || errs[3] || errs[4] || errs[5]
				|| errs[6] || errs[7] || errs[8]) {
			showErrors(errs);
			clearFields(errs);
		} else {
			user = new User(username.getText(), password.getText(), City.toCity(city.getText()), email.getText());
			ConfirmDialog successDlg = new ConfirmDialog(getShopper(),
					"Registration Sent",
					"Your registration is being processed.");
			successDlg.center();
			registered = true;
			hide();
			getShopper().addRegister(getUser());
		}

	}

	private void clearFields(boolean[] errs) {
		if (errs[0] || errs[1])
			username.setText("");
		if (errs[2] || errs[3] || errs[4] || errs[5] || errs[6] || errs[7])
			password.setText("");
		if (errs[8])
			email.setText("");

	}

	private void showErrors(boolean[] errs) {
		String[] errors = { "", "", "", "", "", "", "", "", "" };
		if (errs[0])
			errors[0] = "Username length shorter than 8 or larger than 25.";
		if (errs[1])
			errors[1] = "Username contains invalid characters.";
		if (errs[2])
			errors[2] = "Password length shorter than 8 or larger than 25.";
		if (errs[3])
			errors[3] = "Password lacks digits.";
		if (errs[4])
			errors[4] = "Password lacks lowercase characters.";
		if (errs[5])
			errors[5] = "Password lacks uppercase characters.";
		if (errs[6])
			errors[6] = "Password contains whitespace.";
		if (errs[7])
			errors[7] = "Password contains invalid characters.";
		if (errs[8])
			errors[8] = "Email address invalid.";
		ErrorDialog errDlg = new ErrorDialog(getShopper(),
				"Registration Error", errors);
		errDlg.center();

	}

	public User getUser() {
		return user;
	}

	public boolean isRegistered() {
		return registered;
	}

	@Override
	public void showCities(Set<City> cities) {
		for (City c : cities)
			cityOracle.add(c.toString());
		center();

	}

	



}
