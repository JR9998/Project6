//John R. & Matthew I.
import java.io.*;
import java.net.*;
public class WorkerThread extends Thread {
	private Socket client1; 
	private Socket client2; 
	private BufferedReader incoming1;//reader for client 1
	private BufferedReader incoming2; //reader for client 2
	private PrintWriter outgoing1; //writer for client1
	private PrintWriter outgoing2; //writer for client2
	
	public WorkerThread(Socket client1, Socket client2) { 
		this.client1 = client1;
		this.client2 = client2;
	}//end of WorkerThread method
	
	public void run() { 
		try { 
			incoming1 = new BufferedReader(new InputStreamReader(client1.getInputStream()));
			incoming2 = new BufferedReader(new InputStreamReader(client2.getInputStream()));
			outgoing1 = new PrintWriter(client1.getOutputStream(), true);
			outgoing2 = new PrintWriter(client2.getOutputStream(), true);
			System.out.println("Connected to client1: " + client1.getInetAddress().getHostAddress());
			outgoing1.println("Player 1");
			System.out.println("Connected to client2: " + client2.getInetAddress().getHostAddress());	
			outgoing2.println("Player 2");
			String play1incoming = incoming1.readLine();
			while(play1incoming != null) { 
				if(play1incoming.equals("QUIT")) { 
					outgoing2.println("QUIT");
					break;
				}
				outgoing2.println(play1incoming);
				outgoing2.flush();
				play1incoming = incoming1.readLine();
			}
			
		}
		catch(IOException e) { 
			System.out.println("ERROR:" + e);
		}
	}//end of run method
	
}//end of class
