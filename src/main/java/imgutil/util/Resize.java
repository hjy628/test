package imgutil.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by hjy on 17-12-23.
 * 缩放 装饰者
 */
public class Resize extends AbstractImgUtil {
    /**
     * 比例不同边界颜色
     */
    private static final Color color = new Color(230, 230, 230);
    private List<File> src;  //图片路径集合
    private int height;  //处理后高度
    private int width;  //处理后宽度
    private double ratio;  //处理后高宽比
    private boolean bb;    //比例不对时是否补白


    @Override
    public List<File> dispose() {
        src = super.dispose();
        return resize();
    }

    /**
     * 缩放 - 具体装饰方法
     */
    private List<File> resize() {
        if (src.size() <= 0) {
            return src;
        }
        if (ratio > 0) {
            for (File f : src) {
                resize(f.getPath(), f.getParent(), ratio, bb);
            }
        } else if (height > 0 && width > 0) {
            for (File f : src) {
                resize(f.getPath(), f.getParent(), height, width, bb);
            }
        }
        return src;
    }


    public Resize() {
    }

    public Resize(ImgUtil imgUtil, int height, int width, boolean bb) {
        super(imgUtil);
        this.height = height;
        this.width = width;
        this.bb = bb;
    }

    public Resize(ImgUtil imgUtil, double ratio, boolean bb) {
        super(imgUtil);
        this.ratio = ratio;
        this.bb = bb;
    }


    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

    public boolean isBb() {
        return bb;
    }

    public void setBb(boolean bb) {
        this.bb = bb;
    }


    /*图片缩放，按照尺寸*/
    private static void resize(String src, String target, int height, int width, boolean bb) {
        File newFile = null;
        try {
            double ratio = 0;   //缩放比例
            File f = new File(src);
            BufferedImage bi = ImageIO.read(f);
            Image itemp = bi.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
            //计算比例
            if (Double.valueOf(bi.getHeight()) / bi.getWidth() > Double.valueOf(height) / width) {
                ratio = Double.valueOf(height) / bi.getHeight();
            } else {
                ratio = Double.valueOf(width) / bi.getWidth();
            }
            AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);
            itemp = op.filter(bi, null);
            if (bb) {
                BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                Graphics2D g = image.createGraphics();
                g.setColor(color);
                g.fillRect(0, 0, width, height);
                if (width == itemp.getWidth(null))
                    g.drawImage(itemp, 0, (height - itemp.getHeight(null) / 2), itemp.getWidth(null), itemp.getHeight(null), color, null);
                g.dispose();
                itemp = image;
            }
            File dir = new File(target);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            newFile = new File(dir + File.separator + f.getName());
            ImageIO.write((BufferedImage) itemp, "jpg", newFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*图片缩放，按照尺寸*/
    private static void resize(String src, String target, double ratio, boolean bb) {
        File newFile = null;
        try {
            File f = new File(src);
            BufferedImage bi = ImageIO.read(f);

            //计算尺寸
            int width = bi.getWidth();
            int height = bi.getHeight();
            if (Double.valueOf(height) / width < ratio) {
                height = (int) (width * ratio);
            } else {
                width = (int) (Double.valueOf(height) / ratio);
            }

            Image itemp = bi.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
            AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);
            itemp = op.filter(bi, null);
            if (bb) {
                BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                Graphics2D g = image.createGraphics();
                g.setColor(color);
                g.fillRect(0, 0, width, height);
                if (width == itemp.getWidth(null))
                    g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2, itemp.getWidth(null), itemp.getHeight(null), color, null);
                else
                    g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0, itemp.getWidth(null), itemp.getHeight(null), color, null);
                g.dispose();
                itemp = image;
            }
            File dir = new File(target);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            newFile = new File(dir + File.separator + f.getName());
            ImageIO.write((BufferedImage) itemp, "jpg", newFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
