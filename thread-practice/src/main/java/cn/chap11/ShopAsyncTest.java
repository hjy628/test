package cn.chap11;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * Created by hjy on 17-2-9.
 */
public class ShopAsyncTest {

    List<Shop> shops = Arrays.asList(new Shop("BestPrice"), new Shop("LetsSaveBig"),new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"));

    private final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100),
            new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread t = new Thread(r);
                    t.setDaemon(true);
                    return null;
                }
            });

    public static void main(String[] args) {



        Shop shop = new Shop("BestShop");
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after "+ invocationTime + "msecs");

        //执行更多任务，比如查询其他商店
        //doSomethingElse
        System.out.println("查询其他商店");
        //在计算商品价格的同时
        try {
            double price = futurePrice.get();
            System.out.printf("Price is %.2f%n",price);
            }catch (Exception e){
            throw new RuntimeException(e);
        }
        long retrievalTime = ((System.nanoTime() - start)/1_000_000);
        System.out.println("Price returned after " + retrievalTime + "msecs");


        // ---------------------
        long start1 = System.nanoTime();
        System.out.println(new ShopAsyncTest().findPrices("myPhone27S"));
        long duration = (System.nanoTime() - start1) / 1_000_000;
        System.out.println("Done in "+ duration + "msecs");



        }



    public List<String> findPrices(String product){
      /*  return shops.stream().map(shop -> String.format("%s price is %.2f",shop.getName(),shop.getPrice(product)))
                .collect(Collectors.toList());*/
/*
      return shops.parallelStream().map(shop -> String.format("%s price is %.2f",shop.getName(),shop.getPrice(product)))
              .collect(Collectors.toList());*/

        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> shop.getName() + " price is " + shop.getPrice(product)
                )).collect(Collectors.toList());

        return priceFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

}
