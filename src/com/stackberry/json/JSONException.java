package com.stackberry.json;

public class JSONException extends Exception{

	private String data;
	
	public JSONException() {
		super();
	}

	public JSONException(String exception, String data) {
		super(exception);
		this.data = data;
	}

	public String toString() {
		return super.toString() + " Data:" + data;
	}
	
	
}
