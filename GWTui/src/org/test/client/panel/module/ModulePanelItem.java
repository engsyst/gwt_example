package org.test.client.panel.module;

import org.test.client.panel.StyleConstants;

import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineLabel;

public class ModulePanelItem {
	private HorizontalPanel panel;
	private Image img;
	private InlineLabel label;
	
	public HorizontalPanel getPanel() {
		return panel;
	}

	public Image getImg() {
		return img;
	}

	public InlineLabel getLabel() {
		return label;
	}

	public ModulePanelItem(String text, String icon) {
		panel = new HorizontalPanel();
		panel.addStyleName(StyleConstants.STYLE_MODULE_ITEM_PANEL);
		img = new Image(icon);
		panel.add(img);
		label = new InlineLabel(text);
		panel.add(label);
		img.addStyleName(StyleConstants.STYLE_MODULE_ITEM_ICON);
		img.setTitle(text);
		label.addStyleName(StyleConstants.STYLE_MODULE_ITEM_LABEL);
		panel.setCellVerticalAlignment(label, HasVerticalAlignment.ALIGN_MIDDLE);

	}
}
