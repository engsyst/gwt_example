package org.test.client;

import java.util.ArrayList;

import org.test.entity.User;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void getUsers(AsyncCallback<ArrayList<User>> callback) throws IllegalArgumentException;
	void addUser(User user, AsyncCallback<User> asyncCallback)
			throws IllegalArgumentException;
	void getUser(long id, AsyncCallback<User> asyncCallback);
}
