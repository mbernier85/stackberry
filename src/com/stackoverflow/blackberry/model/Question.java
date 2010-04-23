package com.stackoverflow.blackberry.model;

import java.util.Vector;

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
	private Vector answers;
	
	private static final String KEY_ID = "question_id";
	private static final String KEY_TITLE = "title";
	private static final String KEY_QUESTION = "body";
	private static final String KEY_ANSWER_COUNT = "answer_count";
	
	/**
	 * 
	 */
	public Question() {
		answers = new Vector();
	}
	
	public Question(JSONObject json) {
		this.id = Integer.parseInt((String)json.get(KEY_ID));
		this.title = (String)json.get(KEY_TITLE);
		this.question = (String)json.get(KEY_QUESTION);
		this.answerCount = Integer.parseInt((String)json.get(KEY_ANSWER_COUNT));
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
