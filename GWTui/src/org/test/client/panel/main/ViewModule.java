package org.test.client.panel.main;

import java.util.ArrayList;
import java.util.ListIterator;

import org.test.client.AppState;
import org.test.client.panel.CW;
import org.test.client.panel.StyleConstants;
import org.test.client.panel.main.widget.EditUserDialog;
import org.test.entity.User;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.InlineLabel;


public class ViewModule implements MainPanel {
	private Grid panel;

	public void addHandler(ClickHandler handler) {
//		sendButton.addClickHandler(handler);
	}
	
	public void addHandler(KeyUpHandler handler) {
//		userName.addKeyUpHandler(handler);
//		password.addKeyUpHandler(handler);
	}
	
	private void updatePanel(ArrayList<User> u) {
		assert u != null : "users can not be a null"; 
		panel.clear();
		panel.resize(1, 3);
		panel.addStyleName(StyleConstants.STYLE_USER_TABLE);
		panel.setText(0, 0, "No");
		panel.getCellFormatter().addStyleName(0, 0, StyleConstants.STYLE_USER_TABLE_HEAD);
		panel.setText(0, 1, "Name");
		panel.getCellFormatter().addStyleName(0, 1, StyleConstants.STYLE_USER_TABLE_HEAD);
		panel.setText(0, 2, "Password");
		panel.getCellFormatter().addStyleName(0, 2, StyleConstants.STYLE_USER_TABLE_HEAD);
		panel.resize(u.size() + 1, 3);
		ListIterator<User> it = u.listIterator();
		int row = 1;
		while (it.hasNext()) {
			final User user = it.next();
			panel.setText(row, 0, String.valueOf(row));
			panel.setText(row, 1, user.getLogin());
			InlineLabel userName = new InlineLabel();
			userName.getElement().setId(Long.toString(user.getUserId()));
			userName.setText(user.getLogin());
			userName.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					Element e = event.getRelativeElement();
					getUser(Long.parseLong(e.getId()));
				}
			});
			
			panel.setWidget(row, 1, userName);
			panel.getCellFormatter().getElement(row, 1).setTitle(user.getPassword());
			panel.setText(row, 2, user.getPassword());
			row++;
		}
	}
	
	private void getUser(long id) {
		AppState.greetingService.getUser(id, new AsyncCallback<User>() {
			
			@Override
			public void onSuccess(User result) {
				new EditUserDialog(new CreateModule(result).getPanel(), "Edit").center();
			}
			
			@Override
			public void onFailure(Throwable caught) {
				CW.alert("Error", caught.getMessage());
			}
		});
	}
	
	public Grid getPanel() {
//		AppState.users = null;
		getUsersFromServer();
		
		if (panel == null)
			panel = new Grid();
		return panel;
	}
	
	private ArrayList<User> getUsersFromServer() {
		final ArrayList<User> u = new  ArrayList<User>();
		AppState.greetingService.getUsers(new AsyncCallback<ArrayList<User>>() {
			
			@Override
			public void onSuccess(ArrayList<User> result) {
				if (result != null)
					u.addAll(result);
				updatePanel(u);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				CW.alert("Error", caught.getMessage());
			}
		});
		return u;
	}
}
