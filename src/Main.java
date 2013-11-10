import java.util.Date;
import augments.Confetti;
import augments.Friend;
import augments.Slasher;


public class Main {
	
	public static void main(String[] args){
		addConfetti(3);
		addConfetti(123456789);
		addConfetti(567891234);
		addConfetti(135792468);
		addConfetti(987654321);
		addConfetti(246813579);
		Confetti you=slashTable.sling(123456789);
		you.addFriend(567891234);
		you.addFriend(135792468);
		you.addFriend(987654321);
		you.addFriend(246813579);
		Confetti him=slashTable.sling(3);
		him.addFriend(567891234);
		him.addFriend(135792468);
		him.addFriend(987654321);
		him.addFriend(246813579);
		
		slashTable.sling(123456789).setLat(36.15);
		slashTable.sling(123456789).setLat(-95.94);
		slashTable.sling(135792468).setLat(36.1595);
		slashTable.sling(135792468).setLat(-95.9);
		slashTable.sling(987654321).setLat(36.1245);
		slashTable.sling(987654321).setLat(-95.949);
		slashTable.sling(246813579).setLat(36.161);
		slashTable.sling(246813579).setLat(-95.941);
		slashTable.sling(567891234).setLat(36.384);
		slashTable.sling(567891234).setLat(-95.36);
		
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
