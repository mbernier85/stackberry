package com.stackberry.blackberry.screen;

import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.EditField;
import net.rim.device.api.ui.container.GridFieldManager;

import com.stackberry.blackberry.model.User;
import com.stackberry.blackberry.ui.LabelField;
import com.stackberry.observer.Observable;
import com.stackberry.observer.Observer;

public class UserScreen extends ScreenTemplate implements Observer{

	public UserScreen(String title) {
		super(title);
		LabelField lbl = new LabelField("Loading user information...");
		this.add(lbl);
	}

	public void update(Observable observable, Object object) {
		User user = (User)object;
		synchronized (UiApplication.getEventLock()) {
			this.deleteAll();
			
			GridFieldManager grid = new GridFieldManager(6, 2, GridFieldManager.PREFERRED_SIZE);
			
			LabelField lbl = new LabelField("Display name : ");
			grid.add(lbl);
			lbl = new LabelField(user.getDisplayName());
			grid.add(lbl);
			
			lbl = new LabelField("Creation date : ");
			grid.add(lbl);
			lbl = new LabelField(user.getCreationDateString());
			grid.add(lbl);
			
			lbl = new LabelField("Last access date : ");
			grid.add(lbl);
			lbl = new LabelField(user.getLastAccessDateString());
			grid.add(lbl);
			
			lbl = new LabelField("Website : ");
			grid.add(lbl);
			lbl = new LabelField(user.getWebsiteUrl());
			grid.add(lbl);
			
			lbl = new LabelField("Location : ");
			grid.add(lbl);
			lbl = new LabelField(user.getLocation());
			grid.add(lbl);
			
			lbl = new LabelField("Age : ");
			grid.add(lbl);
			lbl = new LabelField(String.valueOf(user.getAge()));
			grid.add(lbl);
			
			this.add(grid);
			
			EditField about = new EditField("",user.getAboutMe(),user.getAboutMe().length()+1, EditField.FOCUSABLE);
			about.setEditable(false);
			this.add(about);
			
		}
	}
}
