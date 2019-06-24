// Name: Shen, Yulin
// NetID: ys2542

import Fsm.Event;

public class Event_SDATA extends Event {
	
	private int count;
	
	public Event_SDATA(String name) {
		super(name);
	}
	
	public Event_SDATA(String name, Object obj) {
		super(name, obj);
	}
	
	public void setCount(int value) {
		this.count = value;
	}
	
	public int getCount() {
		return this.count;
	}

}
