package com.stackoverflow.blackberry.services;

import java.io.InputStream;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import com.stackoverflow.blackberry.services.callback.Callback;
import com.stackoverflow.json.JSONObject;

import net.rim.device.api.system.DeviceInfo;

public abstract class WebServices extends Thread{

	private Callback callback;
	
	private String method = "";
	private String url = "";
	private Vector parameters = new Vector();
	
	protected static final String URL = "http://api.stackoverflow.com/";
	protected static final String VERSION = "0.7";
	
	protected static final String KEY_SECURITY = "key";
	protected static final String KEY = "";
	
	public WebServices(Callback callback) {
		this.callback = callback;
	}

	protected void setMethod(String value) {
		this.method = value;
	}
	

	
	protected String buildGet(String fullUrl) {
		for (int i = 0 ; i < parameters.size() ; i++) {
			if (i == 0) {
				fullUrl += "?";
			} else {
				fullUrl += "&";
			}
			fullUrl += getParameter(i).key;
			fullUrl += "=";
			fullUrl += getParameter(i).value;
		}
		return fullUrl;
	}
	
	protected void setUrl(String value) {
		this.url = value;
	}
	
	protected void addGetParameter(String key, String value) {
		parameters.addElement(new Parameter(key, value));	
	}
	
	protected void addGetParameter(String key, int value) {
		parameters.addElement(new Parameter(key, String.valueOf(value)));
	}
	
	private Parameter getParameter(int index) {
		return (Parameter)parameters.elementAt(index);
	}
	
	/**
	 * Retourne l'url complet pour la connexion
	 * @return null si l'url est vide, sinon un String 
	 */
	protected String getUrl() {
		if (url.equals("")) {
			return null;
		} else {
			String fullUrl = url;
			fullUrl += method;
			fullUrl = buildGet(fullUrl);
			if (DeviceInfo.isSimulator()) {
				fullUrl += ";deviceside=true";
			}
			return fullUrl;
		}
		
	}
	
	public abstract void run();
	
	public void invoke(String url) throws Exception {
		HttpConnection con = (HttpConnection)Connector.open(url, Connector.READ_WRITE);
		InputStream is = con.openInputStream();
		
		if (con.getResponseCode() != HttpConnection.HTTP_OK) {
			System.out.println("Response Code error : " + con.getResponseCode());
		}

		byte[] data = new byte[1024];
		String datas = "";
		int len;
		while ((len = is.read(data)) != -1) {
			datas += new String(data, 0, len);
		}
		JSONObject json = new JSONObject(datas.getBytes());
		callback.parseJSONObject(json);
	}
	
	protected class Parameter {
		private String key;
		private String value;
		
		public Parameter(String key, String value) {
			this.key = key;
			this.value = value;
		}
	}
}
