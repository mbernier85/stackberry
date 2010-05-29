package com.stackberry.blackberry.services.callback;

import com.stackberry.blackberry.model.Question;
import com.stackberry.json.JSONArray;
import com.stackberry.json.JSONException;
import com.stackberry.json.JSONObject;

public class QuestionCallback implements Callback {

	private Question question;
	
	public QuestionCallback(Question question) {
		this.question = question;
	}
	
	public synchronized void parseJSONObject(JSONObject json) throws JSONException {
		JSONArray object = json.getJSONArray("questions");
		question.parseJson(object.getJSONObject(0));
		question.notifyObservers(question, true);
	}

}
