package web;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by hjy on 16-2-26.
 */
public class Serialize implements Serializable{
    private static final long serialVersionUID = -3246543455L;
    public int num = 1390;

    public static void main(String[] args) {
        try {
            FileOutputStream fos = new FileOutputStream("/home/hjy/桌面/object.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            Serialize serialize = new Serialize();
            oos.writeObject(serialize);
            oos.flush();
            oos.close();
        }catch (Exception e){

        }
    }
}
