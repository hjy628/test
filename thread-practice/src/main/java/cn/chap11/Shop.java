package cn.chap11;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by hjy on 17-2-9.
 */
public class Shop {
    private static Random random = new Random();

    private String name;

    public Shop(String name) {
        this.name = name;
    }

    public static void delay(){
        try {
            TimeUnit.MILLISECONDS.sleep(1000L);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }


    public double getPrice(String product){
        return calculatePrice(product);
    }

    public String getPriceStr(String product){
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s",name,price,code);
    }


    public Future<Double> getPriceAsync(String product){
/*        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread( () -> {
            try {
                double price = calculatePrice(product);
                futurePrice.complete(price);
            }catch (Exception ex){
                futurePrice.completeExceptionally(ex);
            }
        }).start();
        return futurePrice;*/

        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }


    private double calculatePrice(String product){
        delay();
        return random.nextDouble()*product.charAt(0)+product.charAt(1);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
