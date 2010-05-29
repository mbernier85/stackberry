package com.stackberry.blackberry.model.list;

import java.util.Vector;

import com.stackberry.blackberry.model.Question;
import com.stackberry.observer.Observable;

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

	public int title(String text) {
		for (int i = 0 ; i < questions.size() ; i++) {
			if (get(i).getTitle().equals(text)) {
				return get(i).getId();
			}
		}
		return -1;
	}
	
}	
