import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;


public class TheMonocle {

	
	public class isASir implements Runnable{

		
		private ServerSocket serverSocket;// = new ServerSocket(8888);
			@Override
		   public void run()
		   {
		      while(true)
		      {
		         try
		         {
		        	serverSocket = new ServerSocket(8888);
		            System.out.println("Waiting for client on port " +
		            serverSocket.getLocalPort() + "...");
		            Socket server = serverSocket.accept();
		            System.out.println("Just connected to "
		                  + server.getRemoteSocketAddress());
		            DataInputStream in =
		                  new DataInputStream(server.getInputStream());
		            System.out.println(in.readUTF());
		            DataOutputStream out =
		                 new DataOutputStream(server.getOutputStream());
		            out.writeUTF("Thank you for connecting to "
		              + server.getLocalSocketAddress() + "\nGoodbye!");
		            server.close();
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
		
	}
}
