package com.stackberry.blackberry.menu;

import com.stackberry.config.Config;

import net.rim.device.api.ui.MenuItem;

public class Menus {	
	public static class MenuStackoverflow extends MenuItem {
		public MenuStackoverflow(String title,int ordinal,int priority){
			super(title, ordinal, priority);
		}

		public void run() {
			Config.setSite(Config.STACKOVERFLOW);
		}
	}
	
	public static class MenuSuperuser extends MenuItem {
		public MenuSuperuser(String title,int ordinal,int priority){
			super(title, ordinal, priority);
		}

		public void run() {
			Config.setSite(Config.SUPERUSER);
		}
	}
	
	public static class MenuServerfault extends MenuItem {
		public MenuServerfault(String title,int ordinal,int priority){
			super(title, ordinal, priority);
		}

		public void run() {
			Config.setSite(Config.SERVERFAULT);
		}
	}
}
