package com.stackberry.blackberry.services;

import java.io.IOException;

import com.stackberry.blackberry.services.callback.Callback;
import com.stackberry.config.Config;
import com.stackberry.json.JSONException;

public class QuestionListServices extends WebServices{

	private static final String METHOD = "questions";
	
	private static final String KEY_SORT = "sort";
	private static final String VALUE_SORT = "newest";
	private static final String KEY_PAGE_SIZE = "pagesize";
	private static final String PAGE_SIZE = "5";
	private static final String KEY_PAGE = "page";
	
	private int page = 1;
	
	StringBuffer fullURL = new StringBuffer();

	public QuestionListServices(Callback callback, int page) {
		super(callback);
		if (page > 0) {
			this.page = page;
		}
	}
	
	public void run() {
		try {
			setUrl(URL[Config.getSite()]);
			setMethod(VERSION + "/" + METHOD);
			addGetParameter(KEY_SECURITY, KEY);
			addGetParameter(KEY_SORT, VALUE_SORT);
			addGetParameter(KEY_PAGE_SIZE, PAGE_SIZE);
			addGetParameter(KEY_PAGE, String.valueOf(page));
			String url = null;
			if ((url = getUrl() ) != null) {
				super.invoke(url);
			}
			
		} catch (IOException e) {
			System.out.println(e.toString());
		} catch (JSONException e) {
			System.out.println(e.toString());
		}
	}
}
