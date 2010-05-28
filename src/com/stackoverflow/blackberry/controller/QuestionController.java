package com.stackoverflow.blackberry.controller;

import net.rim.device.api.ui.UiApplication;

import com.stackoverflow.blackberry.model.Question;
import com.stackoverflow.blackberry.screen.QuestionScreen;
import com.stackoverflow.blackberry.services.QuestionService;
import com.stackoverflow.blackberry.services.callback.QuestionCallback;

public class QuestionController {
	
	public QuestionController() {

	}
	
	public void getQuestion(int id) {
		Question question = new Question();
		QuestionScreen screen =  new QuestionScreen("Question");
		question.addObserver(screen);
		UiApplication.getUiApplication().pushScreen(screen);
		QuestionCallback callback = new QuestionCallback(question);
		QuestionService service = new QuestionService(callback, id);
		service.start();
	}
}
