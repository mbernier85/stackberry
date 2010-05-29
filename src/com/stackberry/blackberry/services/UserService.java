package com.stackberry.blackberry.services;

import java.io.IOException;

import com.stackberry.blackberry.services.callback.Callback;
import com.stackberry.config.Config;
import com.stackberry.json.JSONException;

public class UserService extends WebServices{

	private static final String METHOD = "users";
	private int userId;
	
	public UserService(Callback callback, int userId) {
		super(callback);
		this.userId = userId;
	}

	public void run() {
		try {
			setUrl(URL[Config.getSite()]);
			setMethod(VERSION + "/" + METHOD + "/" + userId);
			addGetParameter(KEY_SECURITY, KEY);
			
			String url = null;
			if ((url = getUrl() ) != null) {
				super.invoke(url);
			}
			
		} catch (IOException e) {
			System.out.println(e.toString());
		} catch (JSONException e) {
			System.out.println(e.toString());
		}
	}
}
