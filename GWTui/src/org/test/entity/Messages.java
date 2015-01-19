package org.test.entity;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages implements com.google.gwt.user.client.rpc.IsSerializable {
	private static final String BUNDLE_NAME = "org.test.entity.messages"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	private Messages() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
