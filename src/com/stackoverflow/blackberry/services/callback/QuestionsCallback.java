package com.stackoverflow.blackberry.services.callback;

import com.stackoverflow.blackberry.model.Question;
import com.stackoverflow.blackberry.model.list.QuestionList;
import com.stackoverflow.json.JSONArray;
import com.stackoverflow.json.JSONObject;

public class QuestionsCallback implements Callback {
	
	private QuestionList questions;
	
	public QuestionsCallback(QuestionList questions) {
		this.questions = questions;
	}

	public void parseJSONObject(JSONObject json) throws Exception {
		JSONArray jsonArray = json.getJSONArray("questions");
		for (int i = 0 ; i < jsonArray.size() ; i ++) {
			questions.add(new Question((JSONObject)jsonArray.elementAt(i)));
		}
		questions.notifyObservers(null, true);
	}
}
