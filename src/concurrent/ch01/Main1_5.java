package concurrent.ch01;


public class Main1_5 {
	public static void main(String[] args) {
		FileSearch searcher = new FileSearch("C:\\", "8c441216a6724025bc80dfb5ccdb4d35.jpg");
		for (int i = 0; i <100; i++) {
			Thread thread = new Thread(searcher);
			thread.start();
		}
/*		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		thread.interrupt();*/
	}
}
