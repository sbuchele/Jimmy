package augments;

import java.util.ArrayList;

public class Confetti implements Keyable {

	private int phoneNumber;
	private String name;
	private double lat;
	private double lon;
	private ArrayList<Friend> friends;

	public Confetti(int number){
		phoneNumber=number;
		friends=new ArrayList<Friend>();
	}
	
	public boolean addFriend(int number){
		for(int i=0;i<friends.size();i++){
			if(number==friends.get(i).key()){
				return false;
			}
		}
		friends.add(new Friend(number));
		return true;
	}
	
	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name=name;
	}

	public ArrayList<Friend> getFriends() {
		return friends;
	}

	@Override
	public int key() {
		return phoneNumber;
	}

}
