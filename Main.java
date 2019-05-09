
class vehicle {
	protected int speed = 3;
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	

class car extends vehicle {
	public void printspd() {
		System.out.println(speed);
	}
}
	
class AA extends car{
}
	
}
public class Main {
	
	public static void main(String[] args) {
		BB a = new BB ();
		System.out.println(a.getSpeed());
		
	}

}
