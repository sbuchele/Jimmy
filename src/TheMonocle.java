import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;


public class TheMonocle implements Runnable {

	
	//public class isASir implements Runnable{

		
		private ServerSocket serverSocket;// = new ServerSocket(8888);
			@Override
		   public void run()
		   {
		      while(true)
		      {
		         try
		         {
		        	boolean running = true;
		        	serverSocket = new ServerSocket(8888);
		            System.out.println("Waiting for client on port " +
		            serverSocket.getLocalPort() + "...");
		            Socket server = serverSocket.accept();
		            System.out.println("Just connected to "
		                  + server.getRemoteSocketAddress());
		            RAWR rawr = new RAWR(server);
		            new Thread(rawr).start();
		            break;
		         }catch(SocketTimeoutException s)
		         {
		            System.out.println("Socket timed out!");
		            break;
		         }catch(IOException e)
		         {
		            e.printStackTrace();
		            break;
		         }
		      }
		   }
		
	//}
}
