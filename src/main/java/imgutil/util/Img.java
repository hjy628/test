package imgutil.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hjy on 17-12-23.
 * 图片　原组件
 */
public class Img implements ImgUtil{
    private  String  src;  //源图片或图片文件夹路径
    private  String  target;  //目标文件夹路径
    private  boolean  inner;  //true-包含子文件夹, false-仅当前文件夹

    @Override
    public List<File> dispose() {
        return copy();
    }

    /** 复制 - 被装饰者初始状态 */
    private List<File> copy() {
        if (src==null || "".equals(src) || target==null || "".equals(target)) {
            throw new RuntimeException("源路径或目标路径不能为空");
        }
        File srcFile = new File(src);
        List<File> list = new ArrayList<File>();

        File targetDir = new File(target);
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }
        a:
        if (srcFile.isDirectory()) {//源路径是文件夹
            File[] subs = srcFile.listFiles();
            if (subs.length<=0) {
                break a;
            }
            for (File sub: subs) {
                if (sub.isDirectory() && inner) {
                    list.addAll(new Img(sub.getPath(), target+File.separator+sub.getName(), true).copy());
                } else if (AbstractImgUtil.isImg(sub)) {
                    list.add(copy(sub, target));
                }
            }
        } else if (AbstractImgUtil.isImg(srcFile)) {//源路径是图片
            list.add(copy(srcFile, target));
        }
        return list;
    }

    private File copy(File file, String target) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        File newFile = null;
        try {
            fis = new FileInputStream(file);
            newFile = new File(target + File.separator + file.getName());
            fos = new FileOutputStream(newFile);
            byte[] bs = new byte[1024*10];
            int len = -1;
            while ((len=fis.read(bs))!=-1) {
                fos.write(bs, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis!=null) {
                    fis.close();
                }
                if (fos!=null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return newFile;
    }

    public Img() {}
    public Img(String src, String target) {
        this.src = src;
        this.target = target;
    }
    public Img(String src, String target, boolean inner) {
        this.src = src;
        this.target = target;
        this.inner = inner;
    }
    public String getSrc() {
        return src;
    }
    public void setSrc(String src) {
        this.src = src;
    }
    public String getTarget() {
        return target;
    }
    public void setTarget(String target) {
        this.target = target;
    }
    public boolean isInner() {
        return inner;
    }
    public void setInner(boolean inner) {
        this.inner = inner;
    }

    public static void main(String[] args) {

        Img img = new Img("/home/hjy/m7.jpg","/home/hjy/桌面/test/",false);
        img.dispose();
    }


}
