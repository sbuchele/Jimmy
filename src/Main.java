
public class Main {
	
	public static void main(String[] args){
		TheMonocle monocle = new TheMonocle();
		Thread thread = new Thread(monocle);
		thread.start();
	}
}
