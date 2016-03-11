package concurrent.ch01;

import java.util.Date;
import java.util.Deque;

public class CleanerTask extends Thread {
	private Deque<Event> deque;
	public CleanerTask(Deque<Event> deque){
		this.deque=deque;
		setDaemon(true);
	}
	
	@Override
	public void run() {
		System.out.println("clean is start");
		while (true) {
			Date date = new Date();
			clean(date);
		}
	}
	
	private void clean(Date date) {
		long defference;
		boolean delete;
		if (deque.size()==0) {
			return;
		}
		delete = false;
		do {
			Event e = deque.getLast();
			System.out.println(date.getTime());
			System.out.println(e.getDate().getTime());
			defference = date.getTime() - e.getDate().getTime();
		//	System.out.println(defference+"ms");
			if (defference > 10000) {
				System.out.printf("Cleaner: %s\n",e.getEvent());
				System.out.println(e.getEvent());
				deque.removeLast();
				delete=true;
			}
		}while (defference > 10000); 
		if (delete) {
			System.out.printf("Cleaner: Size of the queue: %d\n",deque.size());
		}
	}
	
}
