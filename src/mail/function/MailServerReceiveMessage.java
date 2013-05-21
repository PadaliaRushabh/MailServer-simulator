package mail.function;

public class MailServerReceiveMessage extends MailServer implements Runnable{
	
public MailServerReceiveMessage(String threadName){
		
		runner = new Thread(this, threadName); // (1) Create a new thread.
		runner.start(); // (2) Start the thread.
		
	}

	public void receive(){
		
		for(int j=0 ; j< minutes ; j++){
			for(int i=0; i< processing_message_limit_per_minute ; i++){
				average_arrival_rate++;
				String msg = Server.NewMessage(Integer.toString(++total_message) + " "+ runner.getName());
				System.out.println( total_message +" - Message Received by Server: message_body(" + msg+")" );
				try {
					//delay for some time
					Thread.currentThread().sleep((int)(Math.random() * 1000));
				} catch (InterruptedException e) {}
			}
		}
		average_arrival_rate = average_arrival_rate/minutes;
	}
	public void run(){
		receive();
	}
}
