package za.ac.sun.cs.hons.argyle.client.gui.table;

import java.util.HashMap;
import java.util.Set;

import za.ac.sun.cs.hons.argyle.client.gui.WebPage;
import za.ac.sun.cs.hons.argyle.client.gui.button.InfoButton;
import za.ac.sun.cs.hons.argyle.client.util.CSSUtils.SelectionStyle;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.Widget;

/**
 * Abstract class for interfaces requiring a tabular view.
 * 
 * @author godfried
 * 
 */
public abstract class TableView extends ResizeComposite {

    /**
     * The type of {@link TableView}
     * 
     * @author godfried
     * 
     */
    public enum TABLE {
	DEFAULT, BROWSE, STORE, SHOPPING
    };

    /**
     * {@link UiBinder} for this interface.
     * 
     * @author godfried
     * 
     */
    interface Binder extends UiBinder<Widget, TableView> {
    }

    private static final Binder	binder	= GWT.create(Binder.class);
    public static final int	    VISIBLE_COUNT = 20;

    @UiField(provided = true)
    HTML			       tableHeader;
    @UiField
    FlexTable			  header;
    @UiField
    FlexTable			  table;
    @UiField
    HorizontalPanel		    footer;
    @UiField
    SelectionStyle		     selectionStyle;
    @UiField
    Button			     viewButton;
    private int			startIndex, selectedRow = -1;
    private NavBar		     navBar;
    protected WebPage		  webPage;
    protected Set<?>		   itemSet;
    protected HashMap<Integer, Object> indexMap;
    protected Object		   selected;

    /**
     * Constructs a new {@link TableView} object.
     */
    public TableView(WebPage webPage) {
	setTableHeader(getTableType());
	initWidget(binder.createAndBindUi(this));
	this.webPage = webPage;
	indexMap = new HashMap<Integer, Object>();
	navBar = new NavBar(this);
	initTable(getTableCols(), getHeadings());
	update();
    }

    /**
     * Getter for the class's {@link WebPage}.
     * 
     * @return the class's {@link WebPage}.
     */
    public WebPage getWebPage() {
	return webPage;
    }

    /**
     * Setter for the class's {@link WebPage}.
     * 
     * @param webPage
     *            the new class's {@link WebPage}.
     */
    public void setWebPage(WebPage webPage) {
	this.webPage = webPage;
    }

    /**
     * Adds an item to the table.
     * 
     * @param item
     *            the item to be added.
     * @param pos
     *            the position in the table where the item must be added.
     */
    protected abstract void addItem(Object item, int pos);

    /**
     * Gets the number of columns in the {@link TableView}.
     * 
     * @return the number of columns in the {@link TableView}.
     */
    protected abstract int getTableCols();

    /**
     * Gets the heading of each column in the {@link TableView}.
     * 
     * @return A string array containing the headings.
     */
    protected abstract String[] getHeadings();

    /**
     * Gets the type of {@link TableView}.
     * 
     * @return the type of {@link TableView}.
     */
    protected abstract TABLE getTableType();

    protected InfoButton createInfoButton(Object item) {
	return new InfoButton(getTableType(), item, webPage);
    }

    /**
     * Gets the maximum index that can be used in the table.
     * 
     * @return the size of the {@link #itemSet}
     */
    protected int getMaxIndex() {
	if (itemSet == null) {
	    return 0;
	}
	return itemSet.size();
    }

    /**
     * Whether the table can be used or not.
     * 
     * @return {@code true} if the {@link #itemSet} is not {@code null};
     *         {@code false} if it is {@code null}.
     */
    protected boolean available() {
	return itemSet != null;
    }

    /**
     * Sets the {@link TableView}'s {@link #itemSet} and updates the
     * {@link TableView}.
     * 
     * @param itemSet
     *            the {@link TableView}'s new {@link #itemSet}.
     */
    public void setItemSet(Set<?> itemSet) {
	this.itemSet = itemSet;
	update();
    }

    /**
     * Moves the table to a newer page.
     */
    protected void newer() {
	if (available()) {
	    startIndex -= VISIBLE_COUNT;
	    if (startIndex < 0) {
		startIndex = 0;
	    } else {
		styleRow(selectedRow, false);
		selectedRow = -1;
	    }
	}
    }

    /**
     * Moves the table to an older page.
     */
    protected void older() {
	if (available()) {
	    startIndex += VISIBLE_COUNT;
	    if (startIndex >= getMaxIndex()) {
		startIndex -= VISIBLE_COUNT;
	    } else {
		styleRow(selectedRow, false);
		selectedRow = -1;
		update();
	    }
	}
    }

    /**
     * Processes the {@link #viewButton}'s ClickEvent.
     * 
     * @param event
     */
    @UiHandler("viewButton")
    void onButtonClicked(ClickEvent event) {
	webPage.process(getTableType());
    }

    /**
     * Processes the {@link #table}'s ClickEvent.
     * 
     * @param event
     */
    @UiHandler("table")
    void onTableClicked(ClickEvent event) {
	if (available()) {
	    Cell cell = table.getCellForEvent(event);
	    if (cell != null) {
		int row = cell.getRowIndex();
		selectRow(row);
	    }
	}
    }

    /**
     * Initialises the table's columns and their headings.
     * 
     * @param cols
     *            the number of columns the table has.
     * @param headings
     *            the columns's headings.
     */
    private void initTable(int cols, String[] headings) {
	for (int i = 0; i < cols; i++) {
	    header.getColumnFormatter().setWidth(i, "100px");
	    table.getColumnFormatter().setWidth(i, "100px");
	    if (i < headings.length) {
		header.setText(0, i, headings[i]);
	    }
	}
	header.setWidget(0, cols - 1, navBar);
	header.getCellFormatter().setHorizontalAlignment(0, cols - 1,
		HasHorizontalAlignment.ALIGN_RIGHT);
    }

    /**
     * Displays a row as selected by restyling it.
     * 
     * @param row
     *            the row to be selected.
     */
    private void selectRow(int row) {
	styleRow(selectedRow, false);
	styleRow(row, true);
	selectedRow = row;
	selected = indexMap.get(row);
    }

    /**
     * Styles a row as selected or unselected.
     * 
     * @param row
     *            the row to be styled.
     * @param selected
     *            Whether to be styled as selected or not.
     */
    private void styleRow(int row, boolean selected) {
	if (row != -1) {
	    String style = selectionStyle.selectedRow();
	    if (selected) {
		table.getRowFormatter().addStyleName(row, style);
	    } else {
		table.getRowFormatter().removeStyleName(row, style);
	    }
	}
    }

    /**
     * Updates the data in the table.
     */
    private void update() {
	if (!available()) {
	    return;
	}
	int count = getMaxIndex();
	int max = startIndex + VISIBLE_COUNT;
	if (max > count) {
	    max = count;
	}
	navBar.update(startIndex, count, max);
	int i = 0;
	indexMap.clear();
	for (Object item : itemSet) {
	    if (startIndex + i >= getMaxIndex() || i == VISIBLE_COUNT) {
		break;
	    }
	    indexMap.put(i, item);
	    addItem(item, startIndex + i);
	    i++;
	}
	if (table.getRowCount() > i) {
	    for (; i < VISIBLE_COUNT; ++i) {
		if (table.getRowCount() > 0) {
		    table.removeRow(table.getRowCount() - 1);
		    indexMap.remove(table.getRowCount() - 1);
		}
	    }
	}
    }

    public void addToFooter(Widget w) {
	footer.add(w);
    }

    private void setTableHeader(TABLE tableType) {
	tableHeader = new HTML();
	switch (tableType) {
	    case DEFAULT:
		break;
	    case BROWSE:
		tableHeader.setHTML("<h2>Product Browser</h2>");
		break;
	    case STORE:
		tableHeader.setHTML("<h2>Store Browser</h2>");
		break;
	    case SHOPPING:
		tableHeader.setHTML("<h2>Shopping List Browser</h2>");
		break;
	}
	;
    }
}
