package org.test.client;

import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;

public class AppState {
	private static AppState state;
	
	public static final HandlerManager eventBus = new HandlerManager(null);
	
	public static volatile Map<String, String> users;
	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	public static final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);
	
	private AppState() {
	}

	public static synchronized AppState getInstance() {
		if (state == null) 
			state = new AppState();
		return state;
	}
	
	public Map<String, String> getUsers() {
//		users = null;
//		getUsersFromServer();
		return users;
	}
	

}
