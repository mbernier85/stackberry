package com.stackberry.config;

public class Config {
	private static int site = 0;
	
	public final static int STACKOVERFLOW = 0;
	public final static int SUPERUSER = 1;
	public final static int SERVERFAULT = 2;
	
	public static int getSite() {
		return site;
	}
	
	public static void setSite(int site) {
		Config.site = site;
	}

}
