package org.test.client.panel.event;

import com.google.gwt.event.shared.GwtEvent;

public class ErrorLabelChangeEvent extends GwtEvent<ErrorLabelChangeHandler> {
	public static Type<ErrorLabelChangeHandler> TYPE = new Type<ErrorLabelChangeHandler>();
	private String text;

	public ErrorLabelChangeEvent(String text) {
		this.text = text;
	}
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<ErrorLabelChangeHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ErrorLabelChangeHandler handler) {
		handler.onChange(this);
	}
	
	public String getText() {
		return text;
	}
}
