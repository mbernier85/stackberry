package com.stackoverflow.blackberry.model.list;

import java.util.Vector;

import com.stackoverflow.blackberry.model.Question;
import com.stackoverflow.observer.Observable;

public class QuestionList extends Observable{
	private Vector questions;
	
	public QuestionList() {
		questions = new Vector();
	}
	
	public void add(Question question) {
		questions.addElement(question);
	}
	
	public Question get(int index) {
		return (Question)questions.elementAt(index);
	}

	public int size() {
		return questions.size();
	}

	public void clear() {
		questions.removeAllElements();
	}
	
}	
