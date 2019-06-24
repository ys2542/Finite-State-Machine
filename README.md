# Finite-State-Machine


Demo:

start State is CLOSED
Please enter a valid event: 
ACTIVE
Event ACTIVE received, current State is SYN_SENT
Please enter a valid event: 
CLOSE
Event CLOSE received, current State is CLOSED
Please enter a valid event: 
SYNACK
Fsm.FsmException: Event: SYNACK not defined for State: State(CLOSED)
Please enter a valid event: 
PASSIVE
Event PASSIVE received, current State is LISTEN
Please enter a valid event: 
jfiwefji
Error: unexpected Event: jfiwefji
Fsm.FsmException: Event: INVALID not defined for State: State(LISTEN)
Please enter a valid event: 
SYN
Event SYN received, current State is SYN_RCVD
Please enter a valid event: 
ACK
Event ACK received, current State is ESTABLISHED
Please enter a valid event: 
RDATA
DATA recieved 0
Please enter a valid event: 
SDATA
DATA sent 1
Please enter a valid event: 
RDATA
DATA recieved 1
Please enter a valid event: 
FIN
Event FIN received, current State is CLOSING_WAIT
Please enter a valid event: 
CLOSE
Event CLOSE received, current State is LAST_ACK
Please enter a valid event: 
ACK
Event ACK received, current State is CLOSED
Please enter a valid event: 
terminate
End of input stream!

