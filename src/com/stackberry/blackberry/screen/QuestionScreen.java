package com.stackberry.blackberry.screen;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.EditField;
import net.rim.device.api.ui.container.VerticalFieldManager;

import com.stackberry.blackberry.controller.UserController;
import com.stackberry.blackberry.model.Question;
import com.stackberry.blackberry.ui.LabelField;
import com.stackberry.observer.Observable;
import com.stackberry.observer.Observer;

public class QuestionScreen extends ScreenTemplate implements Observer{

	private Question question;
	
	public QuestionScreen(String title) {
		super(title);
		LabelField lbl = new LabelField("Loading question...");
		super.add(lbl);
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
			
			for (int i = 0 ; i < question.getAnswers().size() ; i++) {
				field = new EditField("",question.getAnswer(i).getBody());
				field.setEditable(false);
				field.setPadding(5,5,5,5);
				vfm.add(field);
				lbl = new LabelField(question.getAnswer(i).getOwnerDisplayName(),
						LabelField.FIELD_RIGHT | LabelField.FOCUSABLE);
				lbl.setPadding(0,5,0,0);
				lbl.setChangeListener(new AnswerUserClickListener());
				vfm.add(lbl);
			}
			
			super.add(vfm);
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
