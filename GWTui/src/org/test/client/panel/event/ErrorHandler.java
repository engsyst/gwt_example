package org.test.client.panel.event;

import com.google.gwt.event.shared.EventHandler;

public interface ErrorHandler extends EventHandler {
	void onError(ErrorEvent event);
}
