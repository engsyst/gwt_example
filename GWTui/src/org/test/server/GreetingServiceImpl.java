package org.test.server;

import java.util.ArrayList;
import java.util.Arrays;

import org.test.client.GreetingService;
import org.test.entity.Right;
import org.test.entity.Role;
import org.test.entity.User;
import org.test.shared.FieldVerifier;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {
	
	private static ArrayList<User> users = new ArrayList<User>();
	private static int lastId;
	
	public GreetingServiceImpl() {
		users.add(new User(0, 1L, Arrays.asList(
				new Role(1, Right.RESPONSIBLE_PERSON, "Ответственный"), 
				new Role(3, Right.SUBSCRIBER, "Рассылка")), 
				"Иванов", "dsrfg"));
		users.add(new User(1, 2L, Arrays.asList(
				new Role(2, Right.ADMIN, "Администратор"), 
				new Role(3, Right.SUBSCRIBER, "Рассылка")), 
				"Петров", "sdlkfj"));
		users.add(new User(2, 2L, Arrays.asList(
				new Role(2, Right.ADMIN, "Администратор"), 
				new Role(3, Right.SUBSCRIBER, "Рассылка")), 
				"Сидоров", "dsrfg"));
		users.add(new User(3, 2L, Arrays.asList(
				new Role(2, Right.ADMIN, "Администратор"), 
				new Role(3, Right.SUBSCRIBER, "Рассылка")), 
				"Федоров", "hjhjl"));
	}

	public User addUser(User user) throws IllegalArgumentException {
		user.setLogin(escapeHtml(user.getLogin()));
		// Verify that the input is valid. 
		if (FieldVerifier.validateUser(user).size() > 0) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException(
					"Name must be at least 4 characters long");
		}
		
		user.setUserId(lastId);

		// Escape data from the client to avoid cross-site script vulnerabilities.
		users.add(lastId, user);
		lastId++;
		return user;
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}

	@Override
	public ArrayList<User> getUsers() throws IllegalArgumentException {
		return users;
	}

	@Override
	public User getUser(long id) {
		for (User user : users) {
			if (id == user.getUserId()) {
				return user;
			}
		}
		throw new IllegalArgumentException("User not found");
	}
}
