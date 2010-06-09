package com.stackberry.blackberry.screen;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.EditField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.VerticalFieldManager;

import com.stackberry.blackberry.controller.QuestionController;
import com.stackberry.blackberry.controller.UserController;
import com.stackberry.blackberry.model.Question;
import com.stackberry.blackberry.ui.ButtonField;
import com.stackberry.blackberry.ui.LabelField;
import com.stackberry.observer.Observable;
import com.stackberry.observer.Observer;

public class QuestionScreen extends ScreenTemplate implements Observer{

	private Question question;
	private QuestionController controller;
	private VerticalFieldManager vfmAnswers;
	
	public QuestionScreen(String title, QuestionController controller) {
		super(title);
		LabelField lbl = new LabelField("Loading question...");
		super.add(lbl);
		this.controller = controller;
	}

	public void update(Observable observable, Object object) {
		this.question = (Question)observable;
		synchronized (UiApplication.getEventLock()) {
			this.deleteAll();
			VerticalFieldManager vfm = new VerticalFieldManager(Manager.VERTICAL_SCROLL | Manager.VERTICAL_SCROLLBAR);
			LabelField lbl = new LabelField(question.getTitle());
			lbl.setFont(Font.getDefault().derive(Font.BOLD));
			lbl.setPadding(5, 5, 5, 5);
			vfm.add(lbl);
			
			EditField field = new EditField("",question.getQuestion());
			field.setEditable(false);
			field.setPadding(5,5,5,5);
			vfm.add(field);
			
			lbl  = new LabelField(question.getOwnerDisplayName(), LabelField.RIGHT | LabelField.FOCUSABLE);
			lbl.setPadding(0,5,0,0);
			lbl.setChangeListener(new QuestionUserClickListener());
			vfm.add(lbl);
			
			lbl = new LabelField("Answers");
			lbl.setFont(Font.getDefault().derive(Font.BOLD));
			lbl.setPadding(5, 5, 5, 5);
			vfm.add(lbl);
			super.add(vfm);
			
			showAnswer();
			
			HorizontalFieldManager hfm = new HorizontalFieldManager();
			ButtonField button = new ButtonField("First page");
			button.setChangeListener(new FirstAnswerPageClickListener());
			hfm.add(button);
			button = new ButtonField("Next page");
			button.setChangeListener(new NextAnswerPageClickListener());
			hfm.add(button);
			super.add(hfm);
			
		}
	}
	
	private void showAnswer() {
		
		vfmAnswers = new VerticalFieldManager();
		
		EditField field = new EditField();
		LabelField lbl = new LabelField();
		for (int i = 0 ; i < question.getAnswers().size() ; i++) {
			field = new EditField("",question.getAnswer(i).getBody());
			field.setEditable(false);
			field.setPadding(5,5,5,5);
			vfmAnswers.add(field);
			lbl = new LabelField(question.getAnswer(i).getOwnerDisplayName(),
					LabelField.FIELD_RIGHT | LabelField.FOCUSABLE);
			lbl.setPadding(0,5,0,0);
			lbl.setChangeListener(new AnswerUserClickListener());
			vfmAnswers.add(lbl);
		}
		super.add(vfmAnswers);
	}
	
	private class FirstAnswerPageClickListener implements FieldChangeListener {
		public void fieldChanged(Field field, int context) {
			controller.getFirstPage();
		}
	}
	
	public void refresh() {
		controller.getFirstPage();
	}
	
	private class NextAnswerPageClickListener implements FieldChangeListener {
		public void fieldChanged(Field arg0, int arg1) {
			controller.getNextPage();
		}
	}
	
	private class QuestionUserClickListener implements FieldChangeListener {
		public void fieldChanged(Field field, int context) {
			if (context == ACTION_INVOKE) {
				int id = -1;
				if ((id = question.getUserId()) != -1) {
					new UserController().getUser(id);
				}
			}
		}
	}
	
	private class AnswerUserClickListener implements FieldChangeListener {
		public void fieldChanged(Field field, int context) {
			if (context == ACTION_INVOKE) {
				LabelField lbl = (LabelField)field;
				int id = -1;
				if ((id = question.getAnswerUserId(lbl.getText())) != -1) {
					new UserController().getUser(id);
				}
			}
		}
	}
}
