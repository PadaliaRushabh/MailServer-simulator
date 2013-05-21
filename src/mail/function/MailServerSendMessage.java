package mail.function;
import java.util.Random;

public class MailServerSendMessage extends MailServer implements Runnable{
	
public MailServerSendMessage(String threadName){
		
		runner = new Thread(this, threadName); // (1) Create a new thread.
		runner.start(); // (2) Start the thread.
		
	}


public void send(){
	
	Random randomGenerator = new Random();
	int randomInt;
	String str="";
	String fCounter;
	for(int j=0;j< minutes;j++){
		for(int i=0;i< processing_message_limit_per_minute;i++){
			
			if(Server.MailQueueEmpty() == false){
				if(Server.CheckFailCounter() == true){
					processed_message++;
					
				}
				
				randomInt = randomGenerator.nextInt(10);
				if(randomInt<=6){
					str = Server.SendMessage();
					System.out.println("Message sent by " + runner.getName() + ": message_body(" +str + ")");
				}
				else{
					fCounter =  Server.CheckMessageStatusAndDrop();
					System.out.println(fCounter);
				// str = Server.SendMessage();
				// Server.NewMessage(str);
				 //System.out.println("Message " +str +  " send failed....");
				}
				
				}
			else{
				--i;
			}
				try {
				//delay for some time
					Thread.currentThread().sleep((int)(Math.random() * 1000));
				} 	catch (InterruptedException e) {}
		}
		messages_in_queue = Server.CountMessagesInQuene() + messages_in_queue ;
	}
	
	}
	public void run(){
		send();
	}
}
