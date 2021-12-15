package MainPackage;

import java.util.concurrent.TimeUnit;

public class CountDown {
	int time; //in seconds

	public CountDown(int time) {
		this.time = time;
	}
	
	public void Initiate() {
		while(time>0) {
			try {
				//System.out.println(time+" seconds remaining.");
				TimeUnit.SECONDS.sleep(1);
				time--;
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			//if(time==0) System.out.println("Countdown over.");
		}
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	public static void main(String[] args) throws InterruptedException {
		CountDown c=new CountDown(5);
		c.Initiate();
		TimeUnit.SECONDS.sleep(2);
		System.out.println(c.getTime());
	}
	
}
