package org.test.client.panel.main.widget;

import java.util.ArrayList;
import java.util.List;

import org.test.client.AppConstants;
import org.test.entity.Role;
import org.test.entity.User;
import org.test.shared.UserFields;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;

public class EditUserForm extends Composite implements KeyUpHandler {
	private static final AppConstants CONSTANTS = GWT
			.create(AppConstants.class);

	private TextBox login;
	private PasswordTextBox password;
	private Label passwordLabel;
	private Label userNameLabel;
	private Button btnSave;
	private User user;
//	private Label adminLabel;
	private CheckBox adminCheckBox;
	private CheckBox responsibleCheckBox;
	private CheckBox subscriberCheckBox;

	/**
	 * @wbp.parser.constructor
	 */
	public EditUserForm() {
		SimplePanel panel = new SimplePanel();

		Grid grid = new Grid(6, 2);
		panel.setWidget(grid);
		panel.addStyleName("euf-panel");

		userNameLabel = new Label(CONSTANTS.userNameLabel_text());
		grid.setWidget(0, 0, userNameLabel);

		login = new TextBox();
		grid.setWidget(0, 1, login);
		login.setName("userName");

		passwordLabel = new Label(CONSTANTS.passwordLabel_text());
		grid.setWidget(1, 0, passwordLabel);

		password = new PasswordTextBox();
		password.setName("password");
		grid.setWidget(1, 1, password);
		
		adminCheckBox = new CheckBox(CONSTANTS.adminCheckBox_text());
		grid.setWidget(2, 1, adminCheckBox);

		responsibleCheckBox = new CheckBox(CONSTANTS.responsibleCheckBox_text());
		grid.setWidget(3, 1, responsibleCheckBox);

		subscriberCheckBox = new CheckBox(CONSTANTS.subscriberCheckBox_text());
		grid.setWidget(4, 1, subscriberCheckBox);
		
		btnSave = new Button(CONSTANTS.btnSave_html());
		grid.setWidget(5, 0, btnSave);

		Button btnReset = new Button(CONSTANTS.btnReset_html());
		btnReset.setText(CONSTANTS.btnReset_html());
		grid.setWidget(5, 1, btnReset);

		password.addKeyUpHandler(this);
		login.setFocus(true);
		
		user = new User();
		
		// All composites must call initWidget() in their constructors.
		initWidget(panel);
	}

	public EditUserForm(User user) {
		this();
		if (user != null)
			this.user = user;
		else 
			this.user = new User();
		login.setText(this.user.getLogin());
		login.selectAll();
		password.setText(this.user.getPassword());
		List<Role> roles = this.user.getRoles();
		if (roles != null && roles.size() > 0) {
			for (Role role : roles) {
				if (AppConstants.ROLE_ADMIN.equals(role.getTitle())) {
					adminCheckBox.setValue(true);
				} else if (AppConstants.ROLE_RESPONSIBLE.equals(role.getTitle())) {
					responsibleCheckBox.setValue(true);
				} else if (AppConstants.ROLE_SUBCRIBER.equals(role.getTitle())) {
					subscriberCheckBox.setValue(true);
				}
			}
		}
	}

	public User getUser() {
		if (user == null)
			user = new User();
		user.setLogin(login.getText());
		user.setPassword(password.getText());
		List<Role> roles = new ArrayList<Role>();
		if (adminCheckBox.getValue()) {
			Role role = new Role();
			role.setTitle(AppConstants.ROLE_ADMIN);
			roles.add(new Role());
		} else if (responsibleCheckBox.getValue()) {
			Role role = new Role();
			role.setTitle(AppConstants.ROLE_RESPONSIBLE);
			roles.add(new Role());
		} else if (subscriberCheckBox.getValue()) {
			Role role = new Role();
			role.setTitle(AppConstants.ROLE_SUBCRIBER);
			roles.add(new Role());
		}
		user.setRoles(roles);
		return user;
	}

	public HandlerRegistration addClickHandler(ClickHandler handler) {
	    assert handler != null : "handler must not be null";
		return btnSave.addClickHandler(handler);
	}

	@Override
	public void onKeyUp(KeyUpEvent event) {
		if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
			btnSave.click();
		}
	}

	public void setEnabled(boolean enabled) {
		btnSave.setEnabled(enabled);
	}

	public boolean isEnabled() {
		return btnSave.isEnabled();
	}

	public void clear() {
		login.setText("");
		password.setText("");
		adminCheckBox.setValue(true);
		responsibleCheckBox.setValue(false);
		subscriberCheckBox.setValue(true);
	}
	
	public void setErrors(List<String> errors) {
		if (errors == null || errors.size() == 0) {
			setAllErrors(false);
			return;
		}
		
		if (errors.contains(UserFields.USER_NAME)) {
			login.setStyleDependentName(AppConstants.STYLE_DEPENDENT_ERROR, true);
			userNameLabel.setStyleDependentName(AppConstants.STYLE_DEPENDENT_ERROR, true);
		} else {
			login.setStyleDependentName(AppConstants.STYLE_DEPENDENT_ERROR, false);
			userNameLabel.setStyleDependentName(AppConstants.STYLE_DEPENDENT_ERROR, false);
		}
		
		if (errors.contains(UserFields.PASSWORD)) {
			password.setStyleDependentName(AppConstants.STYLE_DEPENDENT_ERROR, true);
			passwordLabel.setStyleDependentName(AppConstants.STYLE_DEPENDENT_ERROR, true);
		} else {
			password.setStyleDependentName(AppConstants.STYLE_DEPENDENT_ERROR, false);
			passwordLabel.setStyleDependentName(AppConstants.STYLE_DEPENDENT_ERROR, false);
		}
	}

	/**
	 * @param clear if clear is true all errors will removed
	 */
	public void setAllErrors(boolean clear) {
		login.setStyleDependentName(AppConstants.STYLE_DEPENDENT_ERROR, clear);
		userNameLabel.setStyleDependentName(AppConstants.STYLE_DEPENDENT_ERROR, clear);
		password.setStyleDependentName(AppConstants.STYLE_DEPENDENT_ERROR, clear);
		passwordLabel.setStyleDependentName(AppConstants.STYLE_DEPENDENT_ERROR, clear);
	}
}
