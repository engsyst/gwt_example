package org.test.client.panel.main;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Widget;

public interface MainPanel {
	
	public Widget getPanel();
	
	public void addHandler(ClickHandler handler);
	
	public void addHandler(KeyUpHandler handler);
}
