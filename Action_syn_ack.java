// Name: Shen, Yulin
// NetID: ys2542

import Fsm.Action;
import Fsm.Event;
import Fsm.FSM;

public class Action_syn_ack extends Action {
	
	@Override
	public void execute(FSM f, Event e) {
		System.out.println("Event " + e.getName() + " received, current State is " + f.currentState().getName());
	}
	
	

}
