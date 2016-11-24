package concurrent.ch01;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class NetWorkConnectionsLoader implements Runnable{

	@Override
	public void run() {
		System.out.printf("Beginning  NetWork  Connections loading: %s\n",new Date());
		try {
			TimeUnit.SECONDS.sleep(6);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Data NetWork Connections loading has finished: %s\n",new Date());

	}

}
