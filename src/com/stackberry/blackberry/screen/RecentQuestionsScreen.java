package com.stackberry.blackberry.screen;

import com.stackberry.blackberry.controller.QuestionController;
import com.stackberry.blackberry.controller.QuestionListController;
import com.stackberry.blackberry.model.list.QuestionList;
import com.stackberry.blackberry.ui.ButtonField;
import com.stackberry.observer.Observable;
import com.stackberry.observer.Observer;

import net.rim.device.api.system.Characters;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.VerticalFieldManager;

public class RecentQuestionsScreen extends ScreenTemplate implements Observer{
	
	private QuestionList questions;
	private QuestionListController controller;
	
	public RecentQuestionsScreen() {
		super("Recent questions");
		
		questions = new QuestionList();
		questions.addObserver(this);
		controller = new QuestionListController(questions);

		LabelField label = new LabelField("Loading recent question ...");
		this.add(label);
		
		HorizontalFieldManager hfm = new HorizontalFieldManager();
		
		ButtonField button;
		button = new ButtonField("First page");
		button.setChangeListener(new FirstButtonListener());
		hfm.add(button);
		
		button = new ButtonField("Next page");
		button.setChangeListener(new NextButtonListener());
		hfm.add(button);
		
		this.setStatus(hfm);
		
		controller.getFirstPage();
	}

	public void update(Observable observable, Object object) {
		synchronized (UiApplication.getEventLock()) {
			super.deleteAll();
			HorizontalFieldManager hfm;
			VerticalFieldManager vfm;
			LabelField lbl;
			for (int i = 0 ; i < questions.size() ; i ++) {
				hfm = new HorizontalFieldManager();
				vfm = new VerticalFieldManager();
				
				lbl = new ClickableLabelField(questions.get(i).getTitle());
				lbl.setChangeListener(new LabelClickListener());
				vfm.add(lbl);
				
				lbl = new LabelField(
						String.valueOf(questions.get(i).getAnswerCount()));
				lbl.setPadding(0, 5, 0, 5);
				hfm.add(lbl);
				
				hfm.add(vfm);
				
				this.add(hfm);
			}
		}
	}
	
	public void refresh() {
		controller.getFirstPage();
	}
	
	private class NextButtonListener implements FieldChangeListener {
		public void fieldChanged(Field field, int context) {
			controller.getNextPage();
		}
	}
	
	private class FirstButtonListener implements FieldChangeListener {
		public void fieldChanged(Field field, int context) {
			controller.getFirstPage();
		}
	}
	
	private class LabelClickListener implements FieldChangeListener {
		public void fieldChanged(Field field, int context) {
			if (field instanceof LabelField){
				LabelField lbl = (LabelField)field;
				int id = -1;
				if ((id = questions.title(lbl.getText())) != -1) {
					new QuestionController().getQuestion(id, 0);
				}
			}
		}
	}
	
	private class ClickableLabelField extends LabelField {

		public ClickableLabelField(String title) {
			super(title, LabelField.FOCUSABLE);
		}
		
		protected boolean keyChar(char character, int status, int time) {
			if (Characters.ENTER == character) {
				fieldChangeNotify(LabelField.ACTION_INVOKE);
				return true;
			}
			return super.keyChar(character, status, time);
		}

		protected boolean navigationClick(int status, int time) {
			fieldChangeNotify(LabelField.ACTION_INVOKE);
			return super.navigationClick(status, time);
		}
	}
}
