package com.stackoverflow.blackberry.services;

import java.io.IOException;

import com.stackoverflow.blackberry.services.callback.Callback;

public class QuestionService extends WebServices {

	private static final String METHOD = "questions";
	private static final String PAGE_SIZE = "5";
	private static final String KEY_PAGE_SIZE = "pagesize";
	
	private int questionId;
	
	public QuestionService(Callback callback, int questionId) {
		super(callback);
		this.questionId = questionId;
	}

	public void run() {
		try {
			setUrl(URL);
			setMethod(VERSION + "/" + METHOD + "/" + questionId);
			addGetParameter(KEY_SECURITY, KEY);
			addGetParameter(KEY_PAGE_SIZE, PAGE_SIZE);
			
			String url = null;
			if ((url = getUrl() ) != null) {
				super.invoke(url);
			}
			
		} catch (IOException e) {
			System.out.println(e.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}



}
