package augments;

public class Friend {
	private int phoneNum;
	private double[] distance;
	
	public int getPhoneNum() {
		return phoneNum;
	}

	public double[] getDistance() {
		return distance;
	}

	public Friend(int number){
		phoneNum=number;
		distance=new double[50];
	}
}
