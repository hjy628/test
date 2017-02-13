package nio3;

import java.nio.FloatBuffer;

/**
 *  使用 float 缓冲区。
 * Created by hjy on 17-2-13.
 * @since 1.5
 */
public class UseFloatBuffer {

    public static void main(String[] args) {
        // 分配一个容量为10的新的 float 缓冲区
        FloatBuffer buffer = FloatBuffer.allocate(10);
        for (int i = 0; i < buffer.capacity(); i++) {
            float f = (float) Math.sin((((float) i) / 10) * (2 * Math.PI));
            buffer.put(f);
        }
        // 反转此缓冲区
        buffer.flip();

        // 告知在当前位置和限制之间是否有元素
        while (buffer.hasRemaining()) {
            float f = buffer.get();
            System.out.println(f);
        }
    }

}
