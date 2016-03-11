package concurrent.ch01;

import java.io.File;

public class FileSearch implements Runnable {
	private String initPath;
	private String fileName;
	
	public FileSearch(String initPath, String fileName) {
		super();
		this.initPath = initPath;
		this.fileName = fileName;
	}

	@Override
	public void run() {
		File file = new File(initPath);
		if (file.isDirectory()) {
			try {
				directoryProcess(file);
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.out.printf("%s: The search has been interrupted",Thread.currentThread().getName());
			}
		}
	}
	private void directoryProcess(File file)throws InterruptedException {
		File list[] = file.listFiles();
		if (list!=null) {
			for (int i = 0; i < list.length; i++) {
				if (list[i].isDirectory()) {
					directoryProcess(list[i]);
				}else {
					processFile(list[i]);
				}
			}
		}
		if (Thread.interrupted()) {
			throw new InterruptedException();
		}
	}
	private void processFile(File file) throws InterruptedException {
		if (file.getName().equals(fileName)) {
			System.out.printf("%s : %s\n",Thread.currentThread().getName(),file.getAbsolutePath());
		}
		if (Thread.interrupted()) {
			throw new InterruptedException();
		}
	}
}
