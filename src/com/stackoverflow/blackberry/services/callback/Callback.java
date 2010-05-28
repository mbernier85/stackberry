package com.stackoverflow.blackberry.services.callback;

import com.stackoverflow.json.JSONException;
import com.stackoverflow.json.JSONObject;

public interface Callback {

	public abstract void parseJSONObject(JSONObject json) throws JSONException;
}
