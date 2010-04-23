package com.stackoverflow.blackberry.screen;

import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.MainScreen;

public class ScreenTemplate extends  MainScreen{
	
	public ScreenTemplate(String title) {
		super(Screen.VERTICAL_SCROLL | Screen.VERTICAL_SCROLLBAR);
		LabelField lbl = new LabelField("StackOverflow :: " + title);
		lbl.setPadding(4, 0, 4, 4);
		this.setTitle(lbl);
		
		this.addMenuItem(new NewQuestionsMenu("Questions", 10010, 10));
		this.addMenuItem(new RecentQuestionsMenu("Recent questions", 10000, 10));
		
	}
	
	protected class RecentQuestionsMenu extends MenuItem {

		public RecentQuestionsMenu(String text,int ordinal,int priority){
			super(text, ordinal, priority);
		}

		public void run() {
			// TODO Auto-generated method stub
		}
	}
	
	protected class NewQuestionsMenu extends MenuItem {

		public NewQuestionsMenu(String text,int ordinal,int priority){
			super(text, ordinal, priority);
		}

		public void run() {
			// TODO Auto-generated method stub
		}
	}

	protected boolean onSavePrompt() {
		return true;
	}
	
}
