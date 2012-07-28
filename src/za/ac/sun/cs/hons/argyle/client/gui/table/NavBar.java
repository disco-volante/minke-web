package za.ac.sun.cs.hons.argyle.client.gui.table;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * Provides a navigation bar for the {@link TableView} class.
 * 
 * @author godfried
 * 
 */
public class NavBar extends Composite {
    /**
     * {@link UiBinder} for this class.
     * 
     * @author godfried
     * 
     */
    interface Binder extends UiBinder<Widget, NavBar> {
    }

    private static final Binder binder = GWT.create(Binder.class);

    @UiField
    Element		     countLabel;
    @UiField
    Anchor		      newerButton;
    @UiField
    Anchor		      olderButton;

    private final TableView     outer;

    /**
     * Constructs a new NavBar.
     * 
     * @param tableView
     *            the parent {@link TableView} this object.
     */
    public NavBar(TableView tableView) {
	initWidget(binder.createAndBindUi(this));
	this.outer = tableView;
    }

    /**
     * Updates the NavBar with the new indices.
     * 
     * @param startIndex
     *            the {@link TableView}'s current start index.
     * @param count
     *            the number of items available in the {@link TableView}.
     * @param max
     *            the maximum index of the current displayed view.
     */
    public void update(int startIndex, int count, int max) {
	setVisibility(newerButton, startIndex != 0);
	setVisibility(olderButton, startIndex + TableView.VISIBLE_COUNT < count);
	countLabel.setInnerText("" + (startIndex + 1) + " - " + max + " of "
		+ count);
    }

    /**
     * Moves the {@link TableView} to a newer page.
     * 
     * @param event
     */
    @UiHandler("newerButton")
    void onNewerClicked(ClickEvent event) {
	outer.newer();
    }

    /**
     * Moves the {@link TableView} to an older page.
     * 
     * @param event
     */
    @UiHandler("olderButton")
    void onOlderClicked(ClickEvent event) {
	outer.older();
    }

    /**
     * Shows or hides a widget.
     * 
     * @param widget
     *            the widget which will have its visibility set.
     * @param visible
     *            Hides the widget if {@code false}; shows the widget if
     *            {@code true}.
     */
    private void setVisibility(Widget widget, boolean visible) {
	widget.getElement()
		.getStyle()
		.setVisibility(visible ? Visibility.VISIBLE : Visibility.HIDDEN);
    }
}
