package bloomfilter;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.junit.Test;

import java.util.*;

/**
 * @auther: hjy
 * @Date: 19-9-23 16:38
 * @Description:
 */

public class BloomFilterDemo {

    private static final int insertions = 1000001;


    @Test
    public void bfTest1(){
        //初始化一个存储string数据的布隆过滤器，初始化大小100w,不能设置为0
        BloomFilter<String> bf = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), insertions,0.001);
        //初始化一个存储string数据的set，初始化大小100w
        Set<String> sets = new HashSet<>(insertions);
        //初始化一个存储string数据的set，初始化大小100w
        List<String> lists = new ArrayList<>(insertions);

        //向三个容器初始化100万个随机并且唯一的字符串---初始化操作
        for (int i = 0; i < insertions; i++) {
            String uuid = UUID.randomUUID().toString();
            bf.put(uuid);
            sets.add(uuid);
            lists.add(uuid);
        }
        //布隆过滤器错误判断的次数
        int wrong = 0;
        //布隆过滤器正确判断的次数
        int right = 0;
        for (int i = 0; i < 10000; i++) {
            //按照一定比例选择bf中肯定存在的字符串
            String test = i%100==0?lists.get(i/100):UUID.randomUUID().toString();
            if(bf.mightContain(test)){
                if(sets.contains(test)){
                    right ++;
                }else{
                    wrong ++;
                }
            }
        }

        //100
        System.out.println("=================right====================="+right);
        System.out.println("=================wrong====================="+wrong);
    }

    @Test
    public void bfTest2() {
        //预计要插入多少数据
        int size = 1000000;
        //期望的误判率
        double fpp = 0.01;
        BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size, fpp);
        //插入数据
        for (int i = 0; i < 1000000; i++) {
            bloomFilter.put(i);
        }
        int count = 0;
        for (int i = 0; i < 1000000; i++) {
            if (!bloomFilter.mightContain(i)) {
                count++;
                System.out.println(i + "误判了");
            }
        }
        for (int i = 1000000; i < 2000000; i++) {
            if (bloomFilter.mightContain(i)) {
                count++;
                System.out.println(i + "误判了");
            }
        }
        System.out.println("总共的误判数:" + count);

    }



}
