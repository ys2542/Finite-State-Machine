// Name: Shen, Yulin
// NetID: ys2542

import Fsm.Event;

public class Parser {
	
	public Event getEvent(String input) {
		
		if (input.equals("PASSIVE")) {
			return new Event_PASSIVE(input);
			
		} else if (input.equals("ACTIVE")) {
			return new Event_ACTIVE(input);
			
		} else if (input.equals("SYN")) {
			return new Event_SYN(input);
			
		} else if (input.equals("SYNACK")) {
			return new Event_SYNACK(input);
			
		} else if (input.equals("ACK")) {
			return new Event_ACK(input);
			
		} else if (input.equals("RDATA")) {
			return new Event_RDATA(input);
			
		} else if (input.equals("SDATA")) {
			return new Event_SDATA(input);
			
		} else if (input.equals("FIN")) {
			return new Event_FIN(input);
			
		} else if (input.equals("CLOSE")) {
			return new Event_CLOSE(input);
			
		} else if (input.equals("TIMEOUT")) {
			return new Event_TIMEOUT(input);
			
		} else {
			System.out.println("Error: unexpected Event: " + input);
			return new Event_INVALID("INVALID");
		}
		
	}


}
