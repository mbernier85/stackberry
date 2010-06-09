package com.stackberry.blackberry.services;

import java.io.IOException;

import com.stackberry.blackberry.services.callback.Callback;
import com.stackberry.config.Config;
import com.stackberry.json.JSONException;

public class QuestionService extends WebServices {

	private static final String METHOD = "questions";
	private static final String PAGE_SIZE = "5";
	private static final String KEY_PAGE_SIZE = "pagesize";
	private static final String KEY_BODY = "body";
	private static final String BODY = "true";
	private static final String KEY_PAGE = "page";
	
	private int answerPage = 0;
	private int questionId;
	
	public QuestionService(Callback callback, int questionId, int answerPage) {
		super(callback);
		this.questionId = questionId;
		this.answerPage = answerPage;
	}

	public void run() {
		try {
			setUrl(URL[Config.getSite()]);
			setMethod(VERSION + "/" + METHOD + "/" + questionId);
			addGetParameter(KEY_SECURITY, KEY);
			addGetParameter(KEY_PAGE_SIZE, PAGE_SIZE);
			addGetParameter(KEY_BODY, BODY);
			addGetParameter(KEY_PAGE, answerPage);
			
			String url = null;
			if ((url = getUrl() ) != null) {
				super.invoke(url);
			}
			
		} catch (IOException e) {
			System.out.println(e.toString());
		} catch (JSONException e) {
			System.out.println(e.toString());
			System.out.println();
		}
	}



}
