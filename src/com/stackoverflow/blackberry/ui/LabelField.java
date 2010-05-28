package com.stackoverflow.blackberry.ui;

import net.rim.device.api.i18n.ResourceBundleFamily;
import net.rim.device.api.system.Characters;

public class LabelField extends net.rim.device.api.ui.component.LabelField{

	private int color = 0x000000;
	
	public LabelField() {
		super();
	}

	public LabelField(Object text, int offset, int length, long style) {
		super(text, offset, length, style);
	}

	public LabelField(Object text, long style) {
		super(text, style);
	}

	public LabelField(Object text) {
		super(text);
	}

	public LabelField(ResourceBundleFamily rb, int key) {
		super(rb, key);
	}
	
	protected boolean keyChar(char character, int status, int time) {
		if (Characters.ENTER == character) {
			fieldChangeNotify(ACTION_INVOKE);
			return true;
		}
		return super.keyControl(character, status, time);
	}

	protected boolean navigationClick(int status, int time) {
		fieldChangeNotify(ACTION_INVOKE);
		return super.navigationClick(status, time);
	}
	
	public void setColor(int color) {
		this.color = color;
	}
	
	public int getColor(){
		return color;
	}
}
