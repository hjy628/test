package concurrent.ch01;

import java.util.concurrent.TimeUnit;

public class Main1_11 {
	public static void main(String[] args) {
		ThreadGroup threadGroup = new ThreadGroup("Searcher");
		Result result = new Result();
		SearchTask searchTask = new SearchTask(result);
		for (int i = 0; i < 5; i++) {
			Thread thread = new Thread(threadGroup, searchTask);
			thread.start();
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.printf("Number of Threads: %d\n",threadGroup.activeCount());
			System.out.printf("Information about the Thread Group\n");
			threadGroup.list();
			System.out.println(threadGroup.getMaxPriority());
			System.out.println(threadGroup.getParent());
			System.out.println(threadGroup.getName());
			Thread[] threads= new Thread[threadGroup.activeCount()];
			threadGroup.enumerate(threads);
			for (int j = 0; j < threadGroup.activeCount(); j++) {
				System.out.printf("Thread %s: %s\n",threads[j].getName(),threads[j].getState());
			}
			waitFinish(threadGroup);
			threadGroup.interrupt();
		}
	}
	private static void waitFinish(ThreadGroup group) {
		while (group.activeCount() > 9){
			try {
					TimeUnit.SECONDS.sleep(1);
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
	}
}
