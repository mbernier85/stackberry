package com.stackoverflow.blackberry.main;

import com.stackoverflow.blackberry.screen.RecentQuestionsScreen;

import net.rim.device.api.ui.UiApplication;

public class Main extends UiApplication {
	
	public Main() {
		RecentQuestionsScreen screen = new RecentQuestionsScreen();
		this.pushScreen(screen);
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		main.enterEventDispatcher();
	}
}
