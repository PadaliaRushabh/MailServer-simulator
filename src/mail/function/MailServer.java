package mail.function;

import java.util.Random;
import java.util.Scanner;

public class MailServer {
	
	public Thread runner;
	public static MailFunction Server = new MailFunction();
	public static int total_message=0;
	public static int processed_message=0;
	public static int average_arrival_rate=0;
	public static int messages_in_queue = 0;
	public static int minutes = 0;
	public static int processing_message_limit_per_minute = 0;
	
	
	
	public static void GenerateReport(){
		System.out.println("------------------------------REPORT---------------------------------------");
		System.out.println("-------------------------------------------------------------------------- ");
		System.out.println("Total Number of Messages: " + total_message );
		System.out.println("----******************************************************************---- ");
		System.out.println("Total Number of Messages Processed: " + processed_message );
		System.out.println("----******************************************************************---- ");
		System.out.println("Average Arrival Rate Of Message Sent Per Minute: " + average_arrival_rate );
		System.out.println("----******************************************************************---- ");
		System.out.println("Average Number Of Messages In a Queue In A Minute: " + messages_in_queue/processing_message_limit_per_minute );
		System.out.println("----******************************************************************---- ");
		for(int i = 0 ; i<MailFunction.MAX_FAIL_LIMIT ; i++){
			System.out.println("Number of Messages Send on try " + (i + 1) + " are "  + MailFunction.failed_count[i]);
		}
		System.out.println("Number of Messages dropped" + " are "  + MailFunction.failed_count[MailFunction.MAX_FAIL_LIMIT]);
		System.out.println("----******************************************************************---- ");
		System.out.println("Messages remaining in the queue are " + Server.CountMessagesInQuene());
		System.out.println("----******************************************************************---- ");
		System.out.println("Pending Messages in the Queue are:");
		Server.PrintAllPendingMessage();
		System.out.println("----******************************************************************---- ");
		
		System.out.println("Number of times messages had to be requeued are (in percentage): "  + (MailFunction.total_failed_counter *100)/(processing_message_limit_per_minute * minutes) + "%");
		System.out.println("----******************************************************************---- ");
		
		System.out.println("-------------------------------------------------------------------------- ");
		System.out.println("---------------------------END OF REPORT-----------------------------------");
		
	}
	
}

