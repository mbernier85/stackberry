package com.stackberry.blackberry.screen;

import com.stackberry.blackberry.menu.Menus;
import com.stackberry.config.Config;

import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.MainScreen;

public class ScreenTemplate extends  MainScreen{
	
	private final static String[] TITLE = {"Stack Overflow","Super User", "Server Fault"};	
	private String title;
	
	public ScreenTemplate(String title) {
		super(Screen.VERTICAL_SCROLL | Screen.VERTICAL_SCROLLBAR);
		this.title = title;
		paintTitle();
		
		this.addMenuItem(new NewQuestionsMenu("Questions", 10010, 10));
		this.addMenuItem(new RecentQuestionsMenu("Recent questions", 10000, 10));
		this.addMenuItem(new Menus.MenuStackoverflow("Stackoverflow", 10011, 10));
		this.addMenuItem(new Menus.MenuSuperuser("Superuser", 10012, 10));
		this.addMenuItem(new Menus.MenuServerfault("Serverfault", 10013, 10));
	}
	
	
	public void paintTitle() {
		LabelField lbl = new LabelField(TITLE[Config.getSite()] + " :: " + title);
		lbl.setPadding(4, 0, 4, 4);
		this.setTitle(lbl);
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
