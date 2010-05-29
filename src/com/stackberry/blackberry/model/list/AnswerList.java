package com.stackberry.blackberry.model.list;

import java.util.Vector;

import com.stackberry.blackberry.model.Answer;
import com.stackberry.observer.Observable;

public class AnswerList extends Observable{
	
	private Vector answers ;
	
	public AnswerList() {
		answers = new Vector();
	}
	
	public void add(Answer answer) {
		answers.addElement(answer);
	}
	
	public Answer get(int index) {
		return (Answer)answers.elementAt(index);
	}

	public int size() {
		return answers.size();
	}

	public void clear() {
		answers.removeAllElements();
	}
}
