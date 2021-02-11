import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerConnectionThread extends Thread {
	private Socket serverSocket;
	private BufferedReader inS;
	
	public ServerConnectionThread(Socket server) throws IOException {
		this.serverSocket = server;
		inS = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
				
	}
	@Override
	public void run() {
			try{
				while(true) {
					String serverResponse = inS.readLine();
					if(serverResponse == null) break;
					System.out.println(serverResponse);
			    }
			 }			
			
			//The exception handling and cleaning  part
			catch (IOException e) {
				System.out.println("Connection closed!");
			}
			finally {
				try {
					inS.close();
				} 
				catch (IOException e) {
					System.out.println("Connection closed!");
				}
			}
		}
	//Submitted by ISHIKA DHALL (40164795)
}
