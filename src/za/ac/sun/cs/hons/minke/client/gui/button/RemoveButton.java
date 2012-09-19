package za.ac.sun.cs.hons.minke.client.gui.button;

import za.ac.sun.cs.hons.minke.client.gui.removable.Removable;
import za.ac.sun.cs.hons.minke.client.util.ImageUtils;

public class RemoveButton extends ImageButton {

	private Removable removable;

	public RemoveButton(Removable removable) {
		super(ImageUtils.getImages().remove());
		this.removable = removable;
	}

	@Override
	protected void clickAction() {
		removable.remove();
	}

}
