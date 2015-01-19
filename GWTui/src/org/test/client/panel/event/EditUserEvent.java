package org.test.client.panel.event;

import org.test.entity.User;

import com.google.gwt.event.shared.GwtEvent;

public class EditUserEvent extends GwtEvent<EditUserHandler> {
	public static Type<EditUserHandler> TYPE = new Type<EditUserHandler>();
	
	private long id;
	private User user;

	public EditUserEvent(long id) {
		this.id = id;
	}
	
	public EditUserEvent(User user) {
		this.user = user;
	}
	
	@Override
	public Type<EditUserHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(EditUserHandler handler) {
		handler.onEdit(this);
	}
	
	public User getUser() {
		if (user == null) {
			user = new User();
		}
		return user;
	}
	
	public long getId() {
		return id;
	}
}
