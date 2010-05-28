package com.stackoverflow.blackberry.model;

import java.util.Date;

import com.stackoverflow.json.JSONArray;
import com.stackoverflow.json.JSONException;
import com.stackoverflow.json.JSONObject;
import com.stackoverflow.observer.Observable;

public class Answer extends Observable{
	
	private int id;
	private String body = "";
	private String ownerDisplayName = "";
	private Date creationDate;
	private int ownerUserId;
	
	private static final String KEY_BODY = "body";
	private static final String KEY_ID = "answer_id";
	private static final String KEY_OWNER = "owner";
	private static final String KEY_OWNER_DISPLAY_NAME = "display_name";
	private static final String KEY_CREATION_DATE = "creation_date";
	private static final String KEY_OWNER_USER_ID = "user_id";
	
	public Answer() {
		
	}
	
	public void parseJSON(JSONObject json) throws JSONException {
		body = (String)json.get(KEY_BODY);
		id = Integer.parseInt((String)json.get(KEY_ID));
		creationDate = new Date(Long.parseLong((String)json.get(KEY_CREATION_DATE)));
		
		JSONObject owner = json.getJSONObject(KEY_OWNER);
		ownerUserId = Integer.parseInt((String)owner.get(KEY_OWNER_USER_ID));
		ownerDisplayName = (String)owner.get(KEY_OWNER_DISPLAY_NAME);
	}
	
	public int getOwnerUserId() {
		return ownerUserId;
	}
	
	public String getBody(){
		return body;
	}

	public int getId() {
		return id;
	}

	public String getOwnerDisplayName() {
		return ownerDisplayName;
	}

	public Date getCreationDate() {
		return creationDate;
	}
	
}
