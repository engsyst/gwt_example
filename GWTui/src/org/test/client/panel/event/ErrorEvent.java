package org.test.client.panel.event;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.shared.GwtEvent;

public class ErrorEvent extends GwtEvent<ErrorHandler> {
	public static Type<ErrorHandler> TYPE = new Type<ErrorHandler>();
	
	private ArrayList<String> fields;

	public ErrorEvent(List<String> errors) {
		if (errors == null)
			return;
		this.fields = new ArrayList<String>(errors);
	}
	
	@Override
	public Type<ErrorHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ErrorHandler handler) {
		handler.onError(this);
	}
	
	public List<String> getErrors() {
		return fields;
	}
}
