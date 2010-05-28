package com.stackoverflow.blackberry.services.callback;

import com.stackoverflow.blackberry.model.User;
import com.stackoverflow.json.JSONArray;
import com.stackoverflow.json.JSONException;
import com.stackoverflow.json.JSONObject;

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
