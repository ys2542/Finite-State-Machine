// Name: Shen, Yulin
// NetID: ys2542

import Fsm.FSM;
import Fsm.FsmException;
import Fsm.Transition;
import java.util.Scanner;

public class TCP_FSM {
	
	public static void main(String[] args) throws FsmException {
		
		// define all states
		State_CLOSED closed = new State_CLOSED("CLOSED");
		State_CLOSING_WAIT closing_wait = new State_CLOSING_WAIT("CLOSING_WAIT");
		State_CLOSING closing = new State_CLOSING("CLOSING");
		State_ESTABLISHED established = new State_ESTABLISHED("ESTABLISHED");
		State_FIN_WAIT_1 fin_wait_1 = new State_FIN_WAIT_1("FIN_WAIT_1");
		State_FIN_WAIT_2 fin_wait_2 = new State_FIN_WAIT_2("FIN_WAIT_2");
		State_LAST_ACK last_ack = new State_LAST_ACK("LAST_ACK");
		State_LISTEN listen = new State_LISTEN("LISTEN");
		State_SYN_RCVD syn_rcvd = new State_SYN_RCVD("SYN_RCVD");
		State_SYN_SENT syn_sent = new State_SYN_SENT("SYN_SENT");
		State_TIME_WAIT time_wait = new State_TIME_WAIT("TIME_WAIT");
		
		// define all events
		Event_ACK ack = new Event_ACK("ACK");
		Event_ACTIVE active = new Event_ACTIVE("ACTIVE");
		Event_CLOSE close = new Event_CLOSE("CLOSE");
		Event_FIN fin = new Event_FIN("FIN");
		Event_PASSIVE passive = new Event_PASSIVE("PASSIVE");
		Event_RDATA rdata = new Event_RDATA("RDATA");
		Event_SDATA sdata = new Event_SDATA("SDATA");
		Event_SYN syn = new Event_SYN("SYN");
		Event_SYNACK synack = new Event_SYNACK("SYNACK");
		Event_TIMEOUT timeout = new Event_TIMEOUT("TIMEOUT");
		
		// define all transitions
		Transition closed_syn_sent = new Transition(closed, active, syn_sent, new Action_syn());
		Transition closed_listen = new Transition(closed, passive, listen, new Action_no_action());
		Transition listen_closed = new Transition(listen, close, closed, new Action_no_action());
		Transition listen_syn_rcvd = new Transition(listen, syn, syn_rcvd, new Action_syn_ack());
		Transition syn_sent_closed = new Transition(syn_sent, close, closed, new Action_no_action());
		Transition syn_sent_syn_rcvd = new Transition(syn_sent, syn, syn_rcvd, new Action_syn_ack());
		Transition syn_sent_established = new Transition(syn_sent, synack, established, new Action_ack());
		Transition syn_rcvd_established = new Transition(syn_rcvd, ack, established, new Action_no_action());
		Transition syn_rcvd_fin_wait_1 = new Transition(syn_rcvd, close, fin_wait_1, new Action_fin());
		Transition established_fin_wait_1 = new Transition(established, close, fin_wait_1, new Action_fin());
		Transition established_closing_wait = new Transition(established, fin, closing_wait, new Action_ack());
		Transition established_rdata_loop = new Transition(established, rdata, established, new Action_n());
		Transition established_sdata_loop = new Transition(established, sdata, established, new Action_n());
		Transition fin_wait_1_fin_wait_2 = new Transition(fin_wait_1, ack, fin_wait_2, new Action_no_action());
		Transition fin_wait_1_closing = new Transition(fin_wait_1, fin, closing, new Action_ack());
		Transition fin_wait_2_time_wait = new Transition(fin_wait_2, fin, time_wait, new Action_ack());
		Transition closing_time_wait = new Transition(closing, ack, time_wait, new Action_no_action());
		Transition time_wait_closed = new Transition(time_wait, timeout, closed, new Action_no_action());
		Transition closing_wait_last_ack = new Transition(closing_wait, close, last_ack, new Action_fin());
		Transition last_ack_closed = new Transition(last_ack, ack, closed, new Action_no_action());
		
		// define a whole finite state machine
		FSM fsm = new FSM("TCP_FSM", closed);
		fsm.addTransition(closed_syn_sent);
		fsm.addTransition(closed_listen);
		fsm.addTransition(listen_closed);
		fsm.addTransition(listen_syn_rcvd);
		fsm.addTransition(syn_sent_closed);
		fsm.addTransition(syn_sent_syn_rcvd);
		fsm.addTransition(syn_sent_established);
		fsm.addTransition(syn_rcvd_established);
		fsm.addTransition(syn_rcvd_fin_wait_1);
		fsm.addTransition(established_fin_wait_1);
		fsm.addTransition(established_closing_wait);
		fsm.addTransition(established_rdata_loop);
		fsm.addTransition(established_sdata_loop);
		fsm.addTransition(fin_wait_1_fin_wait_2);
		fsm.addTransition(fin_wait_1_closing);
		fsm.addTransition(fin_wait_2_time_wait);
		fsm.addTransition(closing_time_wait);
		fsm.addTransition(time_wait_closed);
		fsm.addTransition(closing_wait_last_ack);
		fsm.addTransition(last_ack_closed);
		System.out.println("start State is " + fsm.currentState().getName());
		
		int data_count = 0; 
		Parser parser = new Parser();
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter a valid event: ");
		String command = input.next();

		while (!command.equals("terminate")) {
			String previous_state = fsm.currentState().getName();
			try {
				fsm.doEvent(parser.getEvent(command));
				String current_state = fsm.currentState().getName();
				if (!previous_state.equals("ESTABLISHED") && current_state.equals("ESTABLISHED")) { // a new loop
					data_count = 0; // reset 
					
				} else if (command.equals("RDATA")) {
					System.out.printf("DATA recieved %d\n", data_count);
					
				} else if (command.equals("SDATA")) {
					data_count++; // send new data
					System.out.printf("DATA sent %d\n", data_count);
				}
					
			} catch (FsmException fe) {
					System.out.println(fe.toString());
			}
			System.out.println("Please enter a valid event: ");
			command = input.next();
		}
		input.close();
		System.out.println("End of input stream!");
	}
}
