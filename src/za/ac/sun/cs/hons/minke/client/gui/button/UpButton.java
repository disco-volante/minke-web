package za.ac.sun.cs.hons.minke.client.gui.button;

import za.ac.sun.cs.hons.minke.client.gui.spinedit.SpinEdit;
import za.ac.sun.cs.hons.minke.client.util.ImageUtils;

public class UpButton extends ImageButton {
    private final static int UP = 1;
    private SpinEdit spinEdit;
    public UpButton(SpinEdit spinEdit) {
	super(ImageUtils.getImages().up());
	this.spinEdit = spinEdit;
    }

    @Override
    protected void clickAction() {
	spinEdit.changeValue(UP);
    }

}
