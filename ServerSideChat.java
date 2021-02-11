import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerSideChat {

	private static final int port = 1998;
	static ArrayList<ClientHandleThread> clients= new ArrayList<>();
		
	private static ExecutorService pool = Executors.newFixedThreadPool(4);
		
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException{
		
		ServerSocket serverSocket = new ServerSocket(port);    
        
        while (true) {
    		System.out.println("Server waiting for a client connection...");
			Socket clientSocket = serverSocket.accept();
			System.out.println("A client joined the chat room");
				
			ClientHandleThread ct = new ClientHandleThread(clientSocket, clients);
			clients.add(ct);
			
			pool.execute(ct);
		}
	}	
	//Submitted by ISHIKA DHALL (40164795)
}