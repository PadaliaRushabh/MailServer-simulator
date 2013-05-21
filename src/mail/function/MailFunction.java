package mail.function;

public class MailFunction {
	/* This is the max fail limit for a message,if a message crosses this limit then the 
 	message will be removed from the queue*/
	public static final int MAX_FAIL_LIMIT = 3;
	
	private String message;/* This is the email message to be send */
	private int failed_counter = 0;/* This var keeps track of how many times a message has failed */
	public static float total_failed_counter = 0;
	private MailFunction next_message = null;/*Contains the address of next message*/
	private  MailFunction first_message = null;/*Contains the address of first message*/
	private  MailFunction last_message = null;/*Contains the address of last message*/
	public static int failed_count[] = new int[MAX_FAIL_LIMIT + 1];

	public String NewMessage(String msg){ /*Inserts new message at the end of the email queue*/
		MailFunction node = new MailFunction();
		
		if(first_message == null){
			node.message = msg;
			node.next_message = null;
			first_message = node;
			last_message = node;
			
			return node.message;
		}
		else{
			node.message = msg;
			last_message.next_message = node;
			last_message = node;
			last_message.next_message = null;
			
			return node.message;
		}
	}
	
	public String CheckMessageStatusAndDrop(){
		MailFunction node = new MailFunction();
		node = first_message;
			node.failed_counter = node.failed_counter + 1;
			total_failed_counter++;
			if (node.failed_counter >= MAX_FAIL_LIMIT)
				return "Message Droped:" + SendMessage();
			else
				return QueueMessageAgain(node);
			
		
	}

	public String QueueMessageAgain(MailFunction node){
		
		if(first_message == null){
			return "Nothing to Queue";
		}
		else if (first_message == last_message){
			return "Message sending failed, trying to send message again...." + " Message: message_body(" + node.message +")"; 
		}
		else{
			node = first_message;
			first_message = first_message.next_message;
			last_message.next_message = node;
			last_message = node;
			last_message.next_message = null;
			return "Message sending failed(message will be requeued):" + node.message;  
			
		}
	}
	public String SendMessage(){ /*sends message from the queue*/
		if(first_message==null){
			
			return "Nothing to delete";
		}
		
		else{
			MailFunction node = new MailFunction();
			node = first_message;
			first_message = first_message.next_message;
			failed_count[node.failed_counter]++;
			return node.message ;
		}
	}
	public boolean MailQueueEmpty(){
		if(first_message == null){
			return true;
		}
		else{
			return false;
		}
	}
	public void PrintAllPendingMessage(){ /*This functions prints all the pending message*/
		MailFunction node = first_message;
		int counter = 0;
		while(node!=null){
			counter++;
			System.out.println(counter + " Message: " + node.message);
			node = node.next_message;
			
		}
		
	}
	
	public int  CountMessagesInQuene(){
		MailFunction node = first_message;
		int counter = 0;
		while(node!=null){
			counter++;
			node = node.next_message;
			
		}
		return counter;
	}
	public boolean CheckFailCounter(){
		MailFunction node = first_message;
		if(node.failed_counter > 0){
			return false;
		}
		else
			return true;
	}
}
