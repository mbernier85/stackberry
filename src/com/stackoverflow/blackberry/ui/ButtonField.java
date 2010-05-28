package com.stackoverflow.blackberry.ui;

public class ButtonField extends net.rim.device.api.ui.component.ButtonField{

	private int color = 0x000000;
	
	public ButtonField() {
		super();
	}

	public ButtonField(long style) {
		super(style);
	}

	public ButtonField(String label, long style) {
		super(label, style);
	}

	public ButtonField(String label) {
		super(label);
	}
	
	protected boolean navigationClick(int status, int time) {
		fieldChangeNotify(ACTION_INVOKE);
		return true;
	}
	
	public void setColor(int color) {
		this.color = color;
	}
	
	public int getColor(){
		return color;
	}
}
