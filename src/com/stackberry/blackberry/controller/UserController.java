package com.stackberry.blackberry.controller;

import net.rim.device.api.ui.UiApplication;

import com.stackberry.blackberry.model.User;
import com.stackberry.blackberry.screen.UserScreen;
import com.stackberry.blackberry.services.UserService;
import com.stackberry.blackberry.services.callback.UserCallback;

public class UserController {
	public void getUser(int userId) {
		User user = new User();
		UserScreen screen =  new UserScreen("User");
		user.addObserver(screen);
		UiApplication.getUiApplication().pushScreen(screen);
		UserCallback callback = new UserCallback(user);
		UserService service = new UserService(callback, userId);
		service.start();
	}
}
