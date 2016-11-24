package concurrent.ch01;

public class Main1_4 {
	public static void main(String[] args) {
		Thread task = new PrimeGenerator();
		task.start();
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		task.interrupt();
	}
}
