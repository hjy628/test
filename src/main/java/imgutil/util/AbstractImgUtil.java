package imgutil.util;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by hjy on 17-12-23.
 * 抽象图片工具类 - 抽象装饰者
 */
public abstract class AbstractImgUtil implements ImgUtil{

    private ImgUtil imgUtil;

    @Override
    public List<File> dispose() {
        return imgUtil.dispose();
    }

    public AbstractImgUtil(){}
    public AbstractImgUtil(ImgUtil imgUtil) {
        this.imgUtil = imgUtil;
    }

    /**
     *   @Author  hjy
     *   @Description 判断文件是不是图片
     *   @Param   file 被判断的文件
     *   @return 图片返回true 非图片返回false
     *   @throws java.io.IOException
     *   @Date: 下午8:34 17-12-23
     */
    public static boolean isImg(File file){
        if (file.isDirectory()){
            return false;
        }
        try {
            ImageInputStream iis = ImageIO.createImageInputStream(file);
            Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
            if (!iter.hasNext()){   //文件不是图片
                return false;
            }
            return true;
        }catch (IOException e){
            return false;
        }
    }

}
