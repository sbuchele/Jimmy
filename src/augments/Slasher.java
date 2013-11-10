package augments;

import java.util.Date;

public class Slasher<T extends Keyable> {
	
	
	public static Slasher<Confetti> slashTable=new Slasher<Confetti>(100000);
	private Keyable[] table;
	private boolean[] valid;
			
	public Slasher(int size){
		table=new Keyable[size];
		valid=new boolean[size];
	}
	
	public boolean add(T item){
		int hash=item.key()%valid.length;
		if(!valid[hash]){
			valid[hash]=true;
			table[hash]=item;
			return true;
		}
		for(int i=1;i<valid.length;i++){
			if(!valid[(hash+i)%valid.length]){
				valid[(hash+i)%valid.length]=true;
				table[(hash+i)%valid.length]=item;
				return true;
			}
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public T sling(int key){
		int hash=key%valid.length;
		if(!valid[hash]){
			return null;
		}
		if(table[hash].key()==key)
			return (T)(table[hash]);
		for(int i=1;i<valid.length;i++){
			if(valid[(hash+i)%valid.length]){
				if(table[(hash+i)%valid.length].key()==key){
					return (T)(table[(hash+i)%valid.length]);
				}	
			}else
				return null;
		}
		return null;
	}
	
	
	
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
