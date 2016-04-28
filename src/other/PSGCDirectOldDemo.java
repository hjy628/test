package other;

/**
 * Created by hjy on 16-3-17.
 */
public class PSGCDirectOldDemo {
    public static void main(String[] args) throws Exception{
        byte[] bytes=new byte[1024*1024*2];
        byte[] bytes2=new byte[1024*1024*2];
        byte[] bytes3=new byte[1024*1024*2];
        System.out.println("ready to direct allocate to old");
        Thread.sleep(10000);
        byte[] bytes4 = new byte[1024*1024*4];
        Thread.sleep(3000);
    }
}
