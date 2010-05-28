package com.stackoverflow.blackberry.services.callback;

import com.stackoverflow.blackberry.model.Question;
import com.stackoverflow.json.JSONArray;
import com.stackoverflow.json.JSONException;
import com.stackoverflow.json.JSONObject;

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
