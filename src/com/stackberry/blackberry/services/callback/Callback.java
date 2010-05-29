package com.stackberry.blackberry.services.callback;

import com.stackberry.json.JSONException;
import com.stackberry.json.JSONObject;

public interface Callback {

	public abstract void parseJSONObject(JSONObject json) throws JSONException;
}
