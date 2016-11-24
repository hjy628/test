package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hjy on 15-8-20.
 */
public class tt {
    public static void main(String[] args) {
        String url = "http://uu.ttsales.cn/ttsales-cms/whky036/commonShake/init.do";
/*
        String regEx ="(/ttsales-cms)()(/shake)";
        Pattern pat = Pattern.compile(regEx);
        Matcher mat = pat.matcher(url);
        boolean rs = mat.find();
        System.out.println(rs);
        for(int i=tt;i<=mat.groupCount();i++){
            System.out.println("---------");
             System.out.println(mat.group(i));
        }
*/

    /*    System.out.println(url.indexOf("/shake"));
        System.out.println(url.indexOf("ttsales-cms/"));*/
        System.out.println(url.substring(url.indexOf("ttsales-cms/")+12,url.indexOf("/commonShake")));

    }
}
