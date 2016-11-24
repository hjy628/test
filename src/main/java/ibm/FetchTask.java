package ibm;

/**
 * Created by hjy on 16-3-4.
 */
public class FetchTask implements Runnable  {
    private final String name;

    public FetchTask(String name) {
        this.name = name;
    }

    public String toString() {
        return "FetchTask: " + name;
    }

    public void run() {  /* Fetch remote resource */  }
}
