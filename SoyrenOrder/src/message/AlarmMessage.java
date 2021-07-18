package message;

import java.io.Serializable;

public class AlarmMessage implements Serializable, Message  {
	// state - insert, update, delete, select
	private int state;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	
}
