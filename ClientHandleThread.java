import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandleThread extends Thread {
	private Socket clientSocket;
	private BufferedReader inS;
	private PrintWriter outS;
	private ArrayList<ClientHandleThread> clients;
	private String n;
	
	public ClientHandleThread(Socket client, ArrayList<ClientHandleThread> clients) throws IOException {
	this.clientSocket = client;
	this.clients = clients;
	inS = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	outS = new PrintWriter(clientSocket.getOutputStream(), true);	
	}
	
	@Override
	public void run() {
		
		try {
			while(true) {
				this.n = inS.readLine();
				String req = inS.readLine();
				
				if (req.startsWith("send")) {
					int firstSpace = req.indexOf(" ");
					if(firstSpace != -1) {
						outToAll(req.substring(firstSpace+1));
					}	
				}
				
				else if (req.startsWith("quit")) {
					System.out.println("> " + n + " left the chat!");
					outS.println("> Bye!");
					for(ClientHandleThread aClient: clients) {
						if(this.n!=aClient.n) 
						{aClient.outS.println("> " + n + " left the chat");}
					}
					clients.remove(this); break;
				    //clientSocket.close();
				}
				
				else {
				outS.println("> Type -> 'send' to say something or 'quit' to exit the chatbox");
				  }
			}
		}
		
		//Handling exceptions and closing connections
		catch (IOException e) {
			System.out.println("Connection closed!");	
		 } 
		finally {
			outS.close();
			try {
				inS.close();
			} 
			catch (IOException e) {
				System.out.println("Connection closed!");
			}
			}	
	}
	
	//Broadcasting method
	private void outToAll(String message) {
		for(ClientHandleThread aClient: clients) {
			
			if(this.n != aClient.n) {
			String yourmsg = this.n + ": "+ message;
			aClient.outS.println(yourmsg);
		    }		
		}
		//Submitted by ISHIKA DHALL (40164795)
	}
}