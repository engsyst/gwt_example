package org.test.client.panel.main.widget;

import org.test.client.AppConstants;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class EditUserDialog extends DialogBox implements ClickHandler {
	private static final AppConstants CONSTANTS = GWT
			.create(AppConstants.class);

	private static Button closeButton;

	/**
	 * @wbp.parser.constructor
	 */
	public EditUserDialog(Widget content, String caption) {
		closeButton = new Button(CONSTANTS.closeButton_text(), this);
		setText(caption);
		
		addCloseHandler(new CloseHandler<PopupPanel>() {
			
			@Override
			public void onClose(CloseEvent<PopupPanel> event) {
				EditUserDialog.this.hide();
			}
		});
		VerticalPanel vp = new VerticalPanel();
		vp.add(content);
		vp.add(closeButton);
		setWidget(vp);
//		add(closeButton);
	}

	@Override
	public void onClick(ClickEvent event) {
		EditUserDialog.this.hide();
	}
}
