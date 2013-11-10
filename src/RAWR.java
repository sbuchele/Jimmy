import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import augments.Confetti;
import augments.Friend;
import augments.Slasher;



public class RAWR implements Runnable {
	BufferedReader in;
	PrintWriter out;
	Socket socks;
	public RAWR(Socket server) {
		socks = server;
	}

	public synchronized void run() {
		System.out.println("RAWR has taken the socket connection.");
		boolean running = true;
		boolean waiting = false;
		boolean waitID = false;
		boolean waitNum = false;
		boolean waitLat = false;
		boolean waitLong = false;
		int powerCalc = 0;
		int id = -1;
		int numFriends = 0;
		double lat = 0;
		double lon = 0;
		ArrayList<Integer> frands = new ArrayList<Integer>();
		
		Confetti item = null; // = new Confetti(numFriends);
		
		
		while (running) {
			powerCalc++;
			if(id != -1 && Main.slashTable.sling(id)!=null){
				item = Main.slashTable.sling(id);
			}
			else if(id != -1){
				item = new Confetti(id);
				Main.slashTable.add(item);
			}
			try {
				//System.out.println("Waiting");
				wait(100);
				//System.out.println(in.readLine());
				//System.out.println("NotWaiting");
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				out = new PrintWriter(new BufferedWriter(
						new OutputStreamWriter(socks.getOutputStream())), true);
				in = new BufferedReader(new InputStreamReader(
						socks.getInputStream()));
				String dialogue = null;
				if (!waiting) {
					System.out.println("Had dialogue");
					dialogue = in.readLine();
					System.out.println(dialogue);
				}
				if (dialogue != null) {
					if (dialogue.equals("Have ID")) {
						System.out.println("Client says: " + dialogue);
						waitID = true;
						out.println("Send Stuff");
						out.flush();
						//waiting = true;
					} else if (dialogue.equals("Have num")) {
						System.out.println("Client says: " + dialogue);
						waitNum = true;
						out.println("Send Stuff");
						out.flush();
						//waiting = true;
					} else if (dialogue == "Have lat") {
						System.out.println("Client says: " + dialogue);
						waitLat = true;
						out.println("Send Stuff");
						out.flush();
					} else if (dialogue.equals("Have long")) {
						System.out.println("Client says: " + dialogue);
						waitLong = true;
						out.println("Send Stuff");
						out.flush();
						//waiting = true;
					} else if (waitID) {
						System.out.println("Client says: " + dialogue);
						id = Integer.parseInt(dialogue);
						waitID = false;
						waiting = false;
					} else if (waitNum) {
						int number = Integer.parseInt(dialogue);
						System.out.println("Client says: " + dialogue);
						/*if (!frands.contains(number)) {
							frands.add(number);
						}*/
						item.addFriend(number);
						System.out.println("Client says: " + dialogue);
						waiting = false;
						waitNum = false;
					}
					else if(waitLong){
						System.out.println("Client says: " + dialogue);
						item.setLon(Double.parseDouble(dialogue));
						waiting = false;
						waitLong = false;
					}
					else if(waitLat){
						System.out.println("Client says: " + dialogue);
						item.setLat(Double.parseDouble(dialogue));
						waitLat = false;
						waiting = false;
					}
					else if(dialogue.equals("Power time")){
						System.out.println("Sending Power");
						out.println(Main.updateLoc(id, item.getLat(), item.getLon()));
						out.flush();
					}
					//socks.close();
				}
				else{socks.close();
				running = false;}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
