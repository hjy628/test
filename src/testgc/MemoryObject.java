package testgc;

/**
 * Created by hjy on 16-4-27.
 */
public class MemoryObject {
    private byte[] bytes;

    public MemoryObject(int objectSize) {
        this.bytes = new byte[objectSize];
    }
}
