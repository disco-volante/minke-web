package za.ac.sun.cs.hons.minke.client.gui.button;

import za.ac.sun.cs.hons.minke.client.gui.spinedit.SpinEdit;
import za.ac.sun.cs.hons.minke.client.util.ImageUtils;

public class DownButton extends ImageButton {
    private final static int DOWN = -1;
    private SpinEdit spinEdit;
    public DownButton(SpinEdit spinEdit) {
	super(ImageUtils.getImages().down());
	this.spinEdit = spinEdit;
    }

    @Override
    protected void clickAction() {
	spinEdit.changeValue(DOWN);
    }

}
