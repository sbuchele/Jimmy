import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;


public class RAWR implements Runnable {
	BufferedReader in;
	PrintWriter out;
	Socket socks;
	public RAWR(Socket server) {
		socks = server;
	}

	public synchronized void run() {
		boolean running = true;
		boolean waiting = false;
		boolean waitID = false;
		boolean waitNum = false;
		boolean waitLat = false;
		boolean waitLong = false;
		int id = -1;
		int numFriends = 0;
		double lat = 0;
		double lon = 0;
		ArrayList<Integer> frands = new ArrayList<Integer>();
		
		try {
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socks
			        .getOutputStream())), true);
			in = new BufferedReader(new InputStreamReader(socks.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       while(running)
		try {
			wait(500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			String dialogue = null;
			if(in.ready()){
			dialogue = in.readLine();
			}
			else dialogue = null;
			if (dialogue != null) {
				if (dialogue == "Have ID") {
					System.out.println("Client says: " + dialogue);
					waitID = true;
					out.println("Send Stuff");
					out.flush();
				} else if (dialogue == "Have num") {
					System.out.println("Client says: " + dialogue);
					waitNum = true;
					out.println("Send Stuff");
					out.flush();
				}
				else if(dialogue == "Have lat"){
					System.out.println("Client says: " + dialogue);
					waitLat = true;
					out.println("Send Stuff");
					out.flush();
				}
				else if(dialogue == "Have long"){
					System.out.println("Client says: " + dialogue);
					waitLong = true;
					out.println("Send Stuff");
					out.flush();
				}
				else if(waitID){
					System.out.println("Client says: " + dialogue);
				}
				else if(waitNum){
					int number = Integer.parseInt(dialogue);
					System.out.println("Client says: " + dialogue);
					if(!frands.contains(number)){
						frands.add(number);
					}
				}
				//socks.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
