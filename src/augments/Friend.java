package augments;

import java.util.Calendar;
import java.util.Date;

public class Friend implements Keyable{
	private int phoneNum;
	private double[] distance;
	private Date[] time;
	int start,size;

	public double dist(double lat1, double lon1, double lat2, double lon2){
		lat1=Math.toRadians(lat1);
		lat2=Math.toRadians(lat2);
		lon1=Math.toRadians(lon1);
		lon2=Math.toRadians(lon2);
		double deltaLat=(lat1)-(lat2);
		double deltaLon=(lon1)-(lon2);
		double a = Math.pow((Math.sin(deltaLat/2)),2)
				+ Math.cos(lat1) * Math.cos(lat2) 
				* Math.pow(Math.sin(deltaLon/2),2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		
		Calendar cal = Calendar.getInstance();
    	time[(start + size + 1)%50]=cal.getTime();
    	
		distance[(start + size + 1)%50]=6371*c;
		return calcPower(6371*c);
	}
	
	public double calcPower(double dist){
		double sigma=2;//km
		double a=Math.pow(Math.sqrt(2*Math.PI)*sigma,-1);
		double b=-Math.pow(dist, 2)/(2*Math.pow(sigma, 2));
		return 1000.0/(dist+1)*a*Math.pow(Math.E, b);
	}
	
	public double[] getDistance() {
		return distance;
	}

	public Friend(int number){
		phoneNum=number;
		distance=new double[50];
		time=new Date[50];
		start=0;
		size=0;
	}

	public Date[] getTime() {
		return time;
	}

	@Override
	public int key() {
		return phoneNum;
	}
}
