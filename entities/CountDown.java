package entities;

public class CountDown {
	public float timer = 0;
	
	public int getCount() {
		return 3 - (int) timer;
	}
}
