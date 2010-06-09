package com.stackberry.blackberry.controller;

import net.rim.device.api.ui.UiApplication;

import com.stackberry.blackberry.model.User;
import com.stackberry.blackberry.screen.UserScreen;
import com.stackberry.blackberry.services.UserService;
import com.stackberry.blackberry.services.callback.UserCallback;

public class UserController {
	
	private UserScreen screen;
	private User user;
	
	public UserController() {
		user = new User();
		screen =  new UserScreen("User", this, user);
		user.addObserver(screen);
	}
	
	public void refreshUserScreen() {
		UserCallback callback = new UserCallback(user);
		UserService service = new UserService(callback, user.getId());
		service.start();
	}
	
	public void getUser(int userId) {
		UiApplication.getUiApplication().pushScreen(screen);
		refreshUserScreen();
	}
}
