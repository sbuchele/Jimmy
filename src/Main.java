import java.util.Date;
import augments.Confetti;
import augments.Friend;
import augments.Slasher;


public class Main {
	
	public static void main(String[] args){
		TheMonocle monocle = new TheMonocle();
		Thread thread = new Thread(monocle);
		thread.start();
	}
	
	
	public static Slasher<Confetti> slashTable=new Slasher<Confetti>(100000);
	
	public static boolean addConfetti(int number){
		Confetti con=new Confetti(number);
		return slashTable.add(con);
	}
	
	public static double updateLoc(int number, double lat, double lon){
		double power=0;
		Confetti con=slashTable.sling(number);
		if(con==null)
			return 0;//prevent null pointer
		con.setLat(lat);
		con.setLon(lon);
		for(Friend foe:con.getFriends()){
			Confetti foeCon=slashTable.sling(foe.key());
			if(foeCon!=null){
				power+=foe.dist(lat, lon, foeCon.getLat(), foeCon.getLon());
			}
		}
		return power;
	}
	
	public static double[] getGraphLoc(int conNum, int friNum){
		Confetti con=slashTable.sling(conNum);
		for(Friend foe:con.getFriends()){
			if(foe.key()==friNum){
				return foe.getDistance();
			}
		}
		return null;
	}
	
	public static Date[] getGraphTime(int conNum, int friNum){
		Confetti con=slashTable.sling(conNum);
		for(Friend foe:con.getFriends()){
			if(foe.key()==friNum){
				return foe.getTime();
			}
		}
		return null;
	}
}
