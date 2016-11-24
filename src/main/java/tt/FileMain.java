package tt;

import java.util.concurrent.TimeUnit;

public class FileMain {
		public static void main(String[] args) {
			FileClock clock = new FileClock();
			Thread thread = new Thread(clock);
			thread.start();
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			thread.interrupt();
			System.out.println(thread.interrupted());
		}
}
