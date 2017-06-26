package completablefuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

/**
 * Created by hjy on 17-6-26.
 */
public class ShopServer {



    List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("ShopEasy"),
            new Shop("BuyItAll"));


    private final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100), //创建一个线程池，线程池中线程的数目为100和商店数目二者中较小的一个值
            new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread t = new Thread(r);
                    t.setDaemon(true); //使用守护线程-这种方式不会阻止程序的关停
                    return t;
                }
            });

    public static void main(String[] args) {
        long start = System.nanoTime();
        ShopServer shopServer = new ShopServer();
        System.out.println(shopServer.findPrices("myPhone27S"));
        long duration = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Done in "+ duration + "msecs");
    }
/*

    public List<String> findPrices(String product){
        return shops.stream().map(shop -> String.format("%s price is %.2f",shop.getName(),shop.getPrice(product)))
                .collect(Collectors.toList());
    }*/
/*
    public List<String> findPrices(String product){  //使用并行流
        return shops.parallelStream().map(shop -> String.format("%s price is %.2f",shop.getName(),shop.getPrice(product)))
                .collect(Collectors.toList());
    }*/

/*
    //会得到一个List<CompletableFuture<String>>, 列表中的每个CompletableFuture对象在计算完成后都包含商店的String类型的名称
    //由于用CompletableFuture实现的findPrices方法要求返回一个List<String> ,需要等待所有的future执行完毕，将其包含的值抽取出来，填充到列表中才能返回
    public List<CompletableFuture<String>> findPrices(String product){  //使用CompletableFuture
        return shops.stream().map(shop -> CompletableFuture.supplyAsync(() -> String.format("%s price is %.2f",shop.getName(),shop.getPrice(product))))
                .collect(Collectors.toList());
    }
    */
/*

     public List<String> findPrices(String product){  //使用CompletableFuture以异步方式计算每种商品的价格
        List<CompletableFuture<String>> priceFutures =  shops.stream().map(shop -> CompletableFuture.supplyAsync(() -> String.format("%s price is %.2f",shop.getName(),shop.getPrice(product))))
                .collect(Collectors.toList());
         return priceFutures.stream().map(CompletableFuture::join).collect(Collectors.toList()); //等待所有异步操作结束
    }
*/
/*

    public List<CompletableFuture<String>> findPrices(String product){  //使用CompletableFuture
        return shops.stream().map(shop -> CompletableFuture.supplyAsync(() -> shop.getName()+"price is"+shop.getPrice(product),executor))
                .collect(Collectors.toList());
    }
*/
/*

    public List<String> findPrices(String product){
        return shops.stream().map(shop -> shop.getPrice(product)) //取得每个shop对象中商品的原始价格
                .map(Quote::parse)  //在Quote对象中对shop返回的字符串进行转换
                .map(Discount::applyDiscount) //联系Discount服务，为每个Quote申请折扣
                .collect(Collectors.toList());
    }
*/

    public List<String> findPrices(String product){  //使用CompletableFuture以异步方式计算每种商品的价格
        List<CompletableFuture<String>> priceFutures =  shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product),executor)) //以异步方式取得每个shop中指定产品的原始价格
                .map(future -> future.thenApply(Quote::parse))  //Quote对象存在时，对其返回的值进行转换
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(   //使用另一个异步任务构造期望的Future,申请折扣
                        () -> Discount.applyDiscount(quote),executor)))
                .collect(Collectors.toList());
        return priceFutures.stream().map(CompletableFuture::join).collect(Collectors.toList()); //等待所有异步操作结束,并提取各自的返回值
    }


}
