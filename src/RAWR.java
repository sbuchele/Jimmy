import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;


public class RAWR implements Runnable {
	BufferedReader in;
	PrintWriter out;
	Socket socks;
	public RAWR(Socket server) {
		socks = server;
	}

	public void run() {
		boolean running = true;
		boolean IDd = false;
		boolean waitingForID = false;
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
			String dialogue = in.readLine();
			if(!IDd){
				if (!waitingForID) {
					out.println("Who you?");
					waitingForID = true;
				}
				else if(waitingForID && dialogue != null){
					//Add ID to hashmap
					IDd = true;
					waitingForID = false;
				}
			}
			else if(dialogue == "Incoming contact"){
				//prepare for contact incoming
			}
			//socks.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
