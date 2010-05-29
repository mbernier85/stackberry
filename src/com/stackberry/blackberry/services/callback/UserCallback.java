package com.stackberry.blackberry.services.callback;

import com.stackberry.blackberry.model.User;
import com.stackberry.json.JSONArray;
import com.stackberry.json.JSONException;
import com.stackberry.json.JSONObject;

public class UserCallback implements Callback{

	private User user;
	
	public UserCallback(User user) {
		this.user = user;
	}
	
	public synchronized void parseJSONObject(JSONObject json) throws JSONException {
		JSONArray object = json.getJSONArray("users");
		user.parseJson(object.getJSONObject(0));
		user.notifyObservers(user, true);
	}
}
