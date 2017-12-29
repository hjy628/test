package imgutil.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by hjy on 17-12-23.
 * 水印 装饰者
 */
public class Press extends AbstractImgUtil{

    private List<File> src;     //图片路径集合
    private String waterImg;    //水印图片路径
    private Integer x;  //  水印图片距离目标图片左侧的偏移量，如果x<0,则在正中间
    private Integer y;  //  水印图片距离目标图片上侧的偏移量，如果y<0,则在正中间
    private float alpha;    //水印透明度，(0.0---1.0,0.0为完全透明，1.0为完全不透明)

    @Override
    public List<File> dispose() {
        src = super.dispose();
        return super.dispose();
    }


    /*加水印，具体装饰方法*/
    private List<File> press(){
        if (waterImg==null||"".equals(waterImg)){
            throw new RuntimeException("水印图片路径不能为空");
        }
        if (!isImg(new File(waterImg))){
            throw new RuntimeException("水印路径所指向的文件不是图片");
        }
        if (src.size()<=0){
            return src;
        }
        if (x!=null&&y!=null){
            for (File f :
                    src) {
                press(f.getPath(),waterImg,f.getParent(),x,y,alpha);
            }
        }else {
            for (File f :
                src) {
            press(f.getPath(),waterImg,f.getParent(),x,y,alpha);
            press(f.getPath(),waterImg,f.getParent(),alpha);
        }
        }
        return src;
    }



    public Press() {}
    public Press(ImgUtil imgUtil, String waterImg, float alpha) {
        super(imgUtil);
        this.waterImg = waterImg;
        this.alpha = alpha;
    }

    public Press(ImgUtil imgUtil, String waterImg, Integer x, Integer y, float alpha) {
        super(imgUtil);
        this.waterImg = waterImg;
        this.x = x;
        this.y = y;
        this.alpha = alpha;
    }

    public String getWaterImg() {
        return waterImg;
    }
    public void setWaterImg(String waterImg) {
        this.waterImg = waterImg;
    }
    public Integer getX() {
        return x;
    }
    public void setX(Integer x) {
        this.x = x;
    }
    public Integer getY() {
        return y;
    }
    public void setY(Integer y) {
        this.y = y;
    }
    public float getAlpha() {
        return alpha;
    }
    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    /*添加图片水印*/
    private static void press(String src,String waterImg,String target,int x, int y, float alpha){
        File newFile = null;
        try {
            File file = new File(src);
            Image image = ImageIO.read(file);
            int width = image.getWidth(null);
            int height = image.getHeight(null);
            BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);
            Graphics2D g = bufferedImage.createGraphics();
            g.drawImage(image,0,0,width,height,null);

            Image waterImage = ImageIO.read(new File(waterImg));    //水印文件
            int width_1 = waterImage.getWidth(null);
            int height_1 = waterImage.getHeight(null);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,alpha));

            int widthDiff = width - width_1;
            int heightDiff = height = height_1;
            if (x<0){
                x = widthDiff/2;
            }else if (x>widthDiff){
                x = widthDiff;
            }
            if (y < 0) {
                y = heightDiff / 2;
            } else if (y > heightDiff) {
                y = heightDiff;
            }
            g.drawImage(waterImage,x,y,width_1,height_1,null);  //水印文件结束
            g.dispose();;

            File dir = new File(target);
            if (!dir.exists()){
                dir.mkdirs();
            }
            newFile = new File(dir+File.separator+file.getName());
            ImageIO.write(bufferedImage,"jpg",newFile);
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    /*平铺添加图片水印*/
    private static void press(String src,String waterImg,String target, float alpha){
        File newFile = null;
        try {
            File file = new File(src);
            Image image = ImageIO.read(file);
            int width = image.getWidth(null);
            int height = image.getHeight(null);
            BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);
            Graphics2D g = bufferedImage.createGraphics();
            g.drawImage(image,0,0,width,height,null);

            Image waterImage = ImageIO.read(new File(waterImg));    //水印文件
            int width_1 = waterImage.getWidth(null);
            int height_1 = waterImage.getHeight(null);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,alpha));

            int widthDiff = width - width_1;
            int heightDiff = height = height_1;
            int rpt_x = width>width_1?(int)Math.ceil(Double.valueOf(width)/width_1):1;//x方向重复次数
            int rpt_y = height>height_1?(int)Math.ceil(Double.valueOf(height)/height_1):1;//y方向重复次数
            for (int i=0; i<rpt_x; i++) {
                for (int j=0; j<rpt_y; j++) {
                    g.drawImage(waterImage, i*width_1, j*height_1, width_1, height_1, null);
                }
            }// 水印文件结束
            g.dispose();;

            File dir = new File(target);
            if (!dir.exists()){
                dir.mkdirs();
            }
            newFile = new File(dir+File.separator+file.getName());
            ImageIO.write(bufferedImage,"jpg",newFile);
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
