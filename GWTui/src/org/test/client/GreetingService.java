package org.test.client;

import java.util.ArrayList;

import org.test.entity.User;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	public User addUser(User user) throws IllegalArgumentException;
	public ArrayList<User> getUsers() throws IllegalArgumentException;
	public User getUser(long id);
}
