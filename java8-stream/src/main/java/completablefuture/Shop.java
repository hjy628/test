package completablefuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by hjy on 17-6-26.
 */
public class Shop {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Shop(String name) {
        this.name = name;
    }

    private Random random = new Random();
/*
    public double getPrice(String product){
       return calculatePrice(product);
    }
    */
    public String getPrice(String product){
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
       return String.format("%s:%.2f:%s",name,price,code);
    }
/*
    //同步改异步
    public Future<Double> getPriceAsync(String product){
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();  //创建CompletableFuture对象，它会包含计算的结果
        new Thread(() -> {
            double price = calculatePrice(product);
            futurePrice.complete(price);    //需长时间计算的任务结束并得出结果时，设置Future的返回值
        }).start();
        return futurePrice; //无须等待还没结束的计算，直接返回Future对象
    }*/


/*

    //同步改异步 代码优化添加错误处理
    public Future<Double> getPriceAsync(String product){
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();  //创建CompletableFuture对象，它会包含计算的结果
        new Thread(() -> {
            try {
                double price = calculatePrice(product);
                futurePrice.complete(price);    //需长时间计算的任务结束并正常结束得出结果时，设置Future的返回值
            }catch (Exception ex){
                futurePrice.completeExceptionally(ex); //否则就抛出导致失败的异常，完成这次Future操作
            }
        }).start();
        return futurePrice; //无须等待还没结束的计算，直接返回Future对象
    }
*/

    //使用工厂方法supplyAsync创建CompletableFuture对象
    public Future<Double> getPriceAsync(String product){
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));

    }

    private double calculatePrice(String product){
        delay();
      //  throw new RuntimeException("product not available");
        return random.nextDouble()*product.charAt(0)+product.charAt(1);
    }

    public static void delay(){
        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }
}
