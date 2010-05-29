package com.stackberry.blackberry.controller;

import com.stackberry.blackberry.model.list.QuestionList;
import com.stackberry.blackberry.services.QuestionListServices;
import com.stackberry.blackberry.services.callback.QuestionsCallback;

public class QuestionListController {
	
	private int page = 1;
	private QuestionList questions;
	
	public QuestionListController(QuestionList questions) {
		this.questions = questions;
	}
	
	public void getNextPage() {
		questions.clear();
		new QuestionListServices(new QuestionsCallback(questions), page).start();
		page++;
	}
	
	public void getFirstPage() {
		questions.clear();
		page = 1;
		new QuestionListServices(new QuestionsCallback(questions), page).start();
		page++;
	}
}
