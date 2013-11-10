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

	public void run() {
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
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socks
			        .getOutputStream())), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socks.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       while(running)
		
		try {
			String dialogue = null;
			if(in.ready()){
			dialogue = in.readLine();
			}
			else dialogue = null;
			if (dialogue != null) {
				if (dialogue == "Have ID") {
					waitID = true;
					out.println("Send Stuff");
					out.flush();
				} else if (dialogue == "Have num") {
					waitNum = true;
					out.println("Send Stuff");
					out.flush();
				}
				else if(dialogue == "Have lat"){
					waitLat = true;
					out.println("Send Stuff");
					out.flush();
				}
				else if(dialogue == "Have long"){
					waitLong = true;
					out.println("Send Stuff");
					out.flush();
				}
				else if(waitID){
					
				}
				else if(waitNum){
					int number = Integer.parseInt(dialogue);
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
