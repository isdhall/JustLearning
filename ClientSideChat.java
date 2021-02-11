import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientSideChat {

	private static final String server_ip = "127.0.0.1"; //local host
	private static final int server_port = 1998;
	
	public static void main(String[] args) throws IOException{
		Socket clientSocket = new Socket(server_ip, server_port);
		ServerConnectionThread serverCon = new ServerConnectionThread(clientSocket);
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		new Thread(serverCon).start();
		
		System.out.println("> Hey there, Welcome! ");
		
		@SuppressWarnings("resource")
		Scanner sc= new Scanner(System.in); //System.in is a standard input stream
		System.out.println("> To start, enter your name: ");
		String n= sc.nextLine(); 
		System.out.print("> Welcome to the chat "+ n + "\n"); 
		System.out.println("> Type -> 'send' to say something or Type -> 'quit' to exit the chatbox \n");
		
		while(true) {
			
			String command = keyboard.readLine();
			out.println(n);
			out.println(command);			
		}
	}//Submitted by ISHIKA DHALL (40164795)
}