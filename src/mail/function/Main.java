package mail.function;

import java.util.Scanner;

public class Main extends MailServer {
	public static void main(String[] args) {
		try{
			
			Scanner scan = new Scanner(System.in);
			System.out.println("How many minutes you want to run simulator (1 minute = 1 loop):");
			minutes = Integer.parseInt(scan.nextLine());
			System.out.println("Input how many messages you want simulator to process per minute:");
			processing_message_limit_per_minute = Integer.parseInt(scan.nextLine());
			
			MailServerSendMessage ServerThread = new MailServerSendMessage("Server");
			MailServerReceiveMessage ClientThread = new MailServerReceiveMessage("Client");
			
			ClientThread.runner.join();
			ServerThread.runner.join();
			

			GenerateReport();
		}catch (Exception e) {}
	}
}
