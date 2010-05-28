package com.stackoverflow.blackberry.model;

import com.stackoverflow.blackberry.model.list.AnswerList;
import com.stackoverflow.json.JSONArray;
import com.stackoverflow.json.JSONException;
import com.stackoverflow.json.JSONObject;
import com.stackoverflow.observer.Observable;

/**
 * 
 * @author michael
 *
 */
public class Question extends Observable{
	
	private int id;
	
	private String title;
	private String question;
	private int answerCount;
	private AnswerList answers;
	private String ownerDisplayName;


	private static final String KEY_ID = "question_id";
	private static final String KEY_TITLE = "title";
	private static final String KEY_QUESTION = "body";
	private static final String KEY_ANSWER_COUNT = "answer_count";
	private static final String KEY_OWNER_DISPLAY_NAME = "owner_display_name";
	
	/**
	 * 
	 */
	public Question() {
		answers = new AnswerList();
	}

	public void parseJson(JSONObject json) throws JSONException {
		this.id = Integer.parseInt((String)json.get(KEY_ID));
		this.title = (String)json.get(KEY_TITLE);
		this.question = (String)json.get(KEY_QUESTION);
		this.answerCount = Integer.parseInt((String)json.get(KEY_ANSWER_COUNT));
		this.ownerDisplayName = (String)json.get(KEY_OWNER_DISPLAY_NAME);
		
		Answer answer;
		JSONArray array = json.getJSONArray("answers");
		for (int i = 0 ; i < array.size() ; i++) {
			answer = new Answer();
			answer.parseJSON(array.getJSONObject(i));
			answers.add(answer);
		}
	}

	public int getAnswerUserId(String userName) {
		for (int i = 0 ; i < answers.size() ; i++) {
			if (getAnswer(i).getOwnerDisplayName().equals(userName)) {
				return getAnswer(i).getOwnerUserId();
			}
		}
		return -1;
	}
	
	public Answer getAnswer(int index) {
		return answers.get(index);
	}
	
	public String getOwnerDisplayName() {
		return ownerDisplayName;
	}
	
	public AnswerList getAnswers() {
		return answers;
	}
	
	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getQuestion() {
		return question;
	}

	public int getAnswerCount() {
		return answerCount;
	}

}
