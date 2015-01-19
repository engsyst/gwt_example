package org.test.client;

import org.test.client.panel.event.ErrorLabelChangeEvent;
import org.test.client.panel.event.ErrorLabelChangeHandler;
import org.test.client.panel.main.MainPanel;
import org.test.client.panel.main.ViewModule;
import org.test.client.panel.module.ModulePanel;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GWT_UI implements EntryPoint, ErrorLabelChangeHandler {
	/**
	 * fields contained UI parts
	 */
	private InlineLabel errorLabel;
	private ModulePanel modulePanel;
	private MainPanel mainPanel;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		AppState.eventBus.addHandler(ErrorLabelChangeEvent.TYPE, this);
		errorLabel = new InlineLabel();
		modulePanel = new ModulePanel();
		mainPanel = new ViewModule();

		// Use RootPanel.get() to get the entire body element
		RootPanel.get("moduleItemsContainer").add(modulePanel.getPanel());
		RootPanel.get("moduleContentContainer").add(mainPanel.getPanel());
		RootPanel.get("errorLabelContainer").add(errorLabel);
	}

	@Override
	public void onChange(ErrorLabelChangeEvent event) {
		errorLabel.setText(event.getText());
	}	
}
