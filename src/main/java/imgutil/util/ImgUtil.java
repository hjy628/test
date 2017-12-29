package imgutil.util;

import java.io.File;
import java.util.List;

/**
 * Created by hjy on 17-12-23.
 * 装饰者模式实现图片处理工具类
 * 类似java的io流 -
 * Img类似低级流可以独立使用
 * Press和Resize类似高级流
 * 需要依赖于低级流
 */
public interface ImgUtil {

    /** 装饰方法 - 处理图片 */
    List<File> dispose();

}
