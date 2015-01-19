package org.test.client.panel.main;

import java.util.List;

import org.test.client.AppState;
import org.test.client.panel.CW;
import org.test.client.panel.main.widget.EditUserForm;
import org.test.entity.User;
import org.test.shared.FieldVerifier;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;


public class CreateModule extends Composite {
	private EditUserForm userForm;
	private SimplePanel panel;
	private User user;
	
	public CreateModule(User user) {
		if (user != null) {
			this.user = user;
		}
	}

	public Widget getPanel() {
		if (panel == null)
			panel = new SimplePanel();
		panel.clear();
		userForm = new EditUserForm(user);
		panel.add(userForm);
		userForm.addClickHandler(new UserSaveHandler());
		return panel;
	}
	
	/**
	 * Send the name from the nameField to the server and wait for a response.
	 */
	private void addUserToServer(User user) {
		// Then, we send the input to the server.
		userForm.setEnabled(false);
		AppState.greetingService.addUser(user, callback);
	}

//	private void getUserFromServer(User user) {
//		// Then, we send the input to the server.
//		userForm.setEnabled(false);
//		AppState.greetingService.getUser(user.getUserId(), callback);
//	}
//	
	class UserSaveHandler implements ClickHandler {
		/**
		 * Fired when the user clicks on the sendButton.
		 */
		@Override
		public void onClick(ClickEvent event) {
			User user = userForm.getUser();

			// First, we validate the input.
			List<String> err = FieldVerifier.validateUser(user);
			userForm.setErrors(err);
			if (err.size() > 0) {
				return;
			}
			addUserToServer(user);
		}
	}
	
	AsyncCallback<User> callback = new AsyncCallback<User>() {
		@Override
		public void onFailure(Throwable caught) {
			userForm.setEnabled(true);
			CW.alert("Error", "Can not add user");
		}

		@Override
		public void onSuccess(User result) {
			userForm.clear();
			userForm.setEnabled(true);
		}
	};
}
