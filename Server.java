//John R. & Matthew I.
import java.io.*;
import java.net.*;
public class Server {
	private static int LISTENING_PORT = 32007;
	private static BufferedReader incoming;
	private static PrintWriter outgoing;   // Stream for sending data.

	public static void main(String[] args) { 
		System.out.println("Listening on port " + LISTENING_PORT);
		try { 
			ServerSocket listener = new ServerSocket(LISTENING_PORT);  // Listens for incoming connections.
			while(true) { 
				Socket client1;      // For communication with the connecting program.
				Socket client2;
				client1 = listener.accept();
				client2 = listener.accept();
				WorkerThread worker = new WorkerThread(client1, client2);
				worker.start();
			}
			
		}
		catch(IOException e) { 
			System.out.println("ERROR:" + e);
		}
	}//end of main method 
	
}//end of class
