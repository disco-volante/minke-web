package com.cs.shopper.client.gui;

import com.cs.shopper.client.Shopper;
import com.cs.shopper.client.gui.dialog.ConfirmDialog;
import com.cs.shopper.client.gui.dialog.ErrorDialog;
import com.cs.shopper.client.gui.dialog.LoginDialog;
import com.cs.shopper.client.gui.dialog.RegisterDialog;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;


public class HomePage extends ContentPanel {
	private Shopper shopper;
	private Button btnLogin;
	private Button btnRegister;
	private TabPanel pages;
	private ContentPanel rightSidebarPanel, bannerPanel, leftSidebarPanel,
			mainContentsPanel;
	private VerticalPanel footerPanel;
	private ShopListPanel sl;

	public HomePage(Shopper shopper) {
		this.shopper = shopper;
		setHeaderVisible(true);
		BorderLayout layout = new BorderLayout();
		setLayout(layout);
		BorderLayoutData leftSidebarLayoutData = new BorderLayoutData(
				LayoutRegion.WEST, 150);
		leftSidebarLayoutData.setSplit(true);
		leftSidebarLayoutData.setCollapsible(true);
		leftSidebarLayoutData.setMargins(new Margins(0, 5, 0, 5));
		BorderLayoutData mainContentsLayoutData = new BorderLayoutData(
				LayoutRegion.CENTER);
		mainContentsLayoutData.setMargins(new Margins(0));
		BorderLayoutData rightSidebarLayoutData = new BorderLayoutData(
				LayoutRegion.EAST, 150);
		rightSidebarLayoutData.setSplit(true);
		rightSidebarLayoutData.setCollapsible(true);
		rightSidebarLayoutData.setMargins(new Margins(0, 5, 0, 5));
		BorderLayoutData footerLayoutData = new BorderLayoutData(
				LayoutRegion.SOUTH, 20);
		footerLayoutData.setMargins(new Margins(5));
		setBanner();
		setLeftSideBar();
		setRightSideBar();
		setMainContents();
		setFooter();
		setTopComponent(getBanner());
		add(getLeftSideBar(), leftSidebarLayoutData);
		add(getRightSideBar(), rightSidebarLayoutData);
		add(getMainContents(), mainContentsLayoutData);
		add(getFooter(), footerLayoutData);
		setWidth(Window.getClientWidth());
		setHeight(Window.getClientHeight() * 2);
		
	}

	public void setBanner() {
		bannerPanel = new ContentPanel();
		bannerPanel.setHeaderVisible(false);
		bannerPanel.add(new Image("resources/images/banner.jpg"),
				new BorderLayoutData(LayoutRegion.CENTER));
	}

	public ContentPanel getBanner() {
		return bannerPanel;
	}

	public void setLeftSideBar() {
		leftSidebarPanel = new ContentPanel();
		leftSidebarPanel.setHeading("Registration");
		btnLogin = new Button("Login");
		btnRegister = new Button("Register");
		leftSidebarPanel.add(btnLogin);
		leftSidebarPanel.add(btnRegister);
		btnLogin.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				login();				
			}
		});
		btnRegister.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				register();
			}
		});
	}

	public ContentPanel getLeftSideBar() {
		return leftSidebarPanel;
	}

	public void setRightSideBar() {
		rightSidebarPanel = new ContentPanel();
		rightSidebarPanel.setHeading("Shopping List");
		rightSidebarPanel.setLayout(new FlowLayout());
		setShopListPanel(new ShopListPanel(shopper));
		getShopListPanel().setSize(50, 200);
		rightSidebarPanel.add(getShopListPanel());
	}

	public ContentPanel getRightSideBar() {
		return rightSidebarPanel;
	}

	public void setMainContents() {
		mainContentsPanel = new ContentPanel();
		mainContentsPanel.setHeading("Main Contents");
		setPages(new TabPanel());
		ProductTable productTable = new ProductTable(shopper);
		productTable.setSize(Window.getClientWidth(), Window.getClientHeight());
		getPages().add(productTable, "Products");
		mainContentsPanel.add(getPages());
	}

	public ContentPanel getMainContents() {
		return mainContentsPanel;
	}

	public void setFooter() {
		footerPanel = new VerticalPanel();
		footerPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		//footerPanel.setHorizontalAlign(Style.HorizontalAlignment.CENTER);
		Label label = new Label("Design by Pieter Jordaan.");
		footerPanel.add(label);
	}

	public VerticalPanel getFooter() {
		return footerPanel;
	}

	

	protected void register() {
		new RegisterDialog(shopper);
	}

	public void regMsg(boolean result) {
		if (result) {
			btnRegister.setVisible(false);
			ConfirmDialog successDlg = new ConfirmDialog(shopper,
					"Registration Success", "Successfully registered.");
			successDlg.center();
		} else {
			ErrorDialog errDlg = new ErrorDialog(shopper, "Registration Error",
					"Invalid registration credentials, are you already registered?");
			errDlg.center();
		}

	}

	protected void login() {
		final LoginDialog dlg = new LoginDialog(shopper);
		dlg.center();
		dlg.addCloseHandler(new CloseHandler<PopupPanel>() {
			@Override
			public void onClose(CloseEvent<PopupPanel> event) {
				if (dlg.isLogin())
					getShopper().checkLogin(dlg.getUsername(),dlg.getPassword());
			}

		});
	}

	protected Shopper getShopper() {
		return shopper;
	}

	public void loginMsg(boolean login) {
		if (login) {
			btnLogin.setVisible(false);
			btnRegister.setVisible(false);
			ConfirmDialog successDlg = new ConfirmDialog(shopper,
					"Login Success", "Successfully logged in.");
			successDlg.center();
		} else {
			ErrorDialog errDlg = new ErrorDialog(shopper, "Login Error",
					"Invalid login credentials.");
			errDlg.center();
		}

	}

	public TabPanel getPages() {
		return pages;
	}

	public void setPages(TabPanel pages) {
		this.pages = pages;
	}

	public ShopListPanel getShopListPanel() {
		return sl;
	}

	public void setShopListPanel(ShopListPanel sl) {
		this.sl = sl;
	}

}
