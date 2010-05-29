package com.stackberry.blackberry.services.callback;

import com.stackberry.blackberry.model.Question;
import com.stackberry.blackberry.model.list.QuestionList;
import com.stackberry.json.JSONArray;
import com.stackberry.json.JSONException;
import com.stackberry.json.JSONObject;

public class QuestionsCallback implements Callback {
	
	private QuestionList questions;
	
	public QuestionsCallback(QuestionList questions) {
		this.questions = questions;
	}

	public synchronized void parseJSONObject(JSONObject json) throws JSONException {
		JSONArray jsonArray = json.getJSONArray("questions");
		for (int i = 0 ; i < jsonArray.size() ; i ++) {
			Question question = new Question();
			question.parseJson((JSONObject)jsonArray.elementAt(i));
			questions.add(question);
		}
		questions.notifyObservers(null, true);
	}
}
