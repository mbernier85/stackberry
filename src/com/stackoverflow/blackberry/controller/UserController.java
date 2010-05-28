package com.stackoverflow.blackberry.controller;

import net.rim.device.api.ui.UiApplication;
import com.stackoverflow.blackberry.model.User;
import com.stackoverflow.blackberry.screen.UserScreen;
import com.stackoverflow.blackberry.services.UserService;
import com.stackoverflow.blackberry.services.callback.UserCallback;

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
