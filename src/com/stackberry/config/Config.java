package com.stackberry.config;

import com.stackberry.blackberry.screen.ScreenTemplate;

import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.UiApplication;

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
		Screen screen = UiApplication.getUiApplication().getActiveScreen();
		if (screen instanceof ScreenTemplate) {
			((ScreenTemplate) screen).paintTitle();
			((ScreenTemplate) screen).refresh();
		}
	}
}
