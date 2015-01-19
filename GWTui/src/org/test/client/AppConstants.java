package org.test.client;

import com.google.gwt.i18n.client.Constants;

public interface AppConstants extends Constants {
	// styles
	public static final String STYLE_DEPENDENT_ERROR = "error";
	
	// user roles
	public static final String ROLE_ADMIN = "Администратор";
	public static final String ROLE_SUBCRIBER = "Ответственный";
	public static final String ROLE_RESPONSIBLE = "Рассылка";
	
	String adminCheckBox_text();
	String btnSave_html();
	String btnReset_html();
	String passwordLabel_text();
	String responsibleCheckBox_text();
	String subscriberCheckBox_text();
	String userNameLabel_text();
	String closeButton_text();
}
