package com.stackberry.blackberry.controller;

import net.rim.device.api.ui.UiApplication;

import com.stackberry.blackberry.model.Question;
import com.stackberry.blackberry.screen.QuestionScreen;
import com.stackberry.blackberry.services.QuestionService;
import com.stackberry.blackberry.services.callback.QuestionCallback;

public class QuestionController {
	
	private int page = 1;
	private Question question;
	private QuestionScreen screen;
	
	public QuestionController() {
		question = new Question();
		screen =  new QuestionScreen("Question", this);
		question.addObserver(screen);

	}
	
	public void getNextPage() {
		page++;
		QuestionCallback callback = new QuestionCallback(question);
		QuestionService service = new QuestionService(callback, question.getId(), page);
		service.start();
	}
	
	public void getFirstPage() {
		page = 1;
		QuestionCallback callback = new QuestionCallback(question);
		QuestionService service = new QuestionService(callback, question.getId(), page);
		service.start();
	}
	
	public void getQuestion(int id, int page) {
		UiApplication.getUiApplication().pushScreen(screen);
		QuestionCallback callback = new QuestionCallback(question);
		QuestionService service = new QuestionService(callback, id, page);
		service.start();
	}
}
