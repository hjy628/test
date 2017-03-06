package chap5;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Created by hjy on 17-3-6.
 * 使用FutureTask来提前加载稍后需要的数据
 */
public class Preloader {
    private final FutureTask<ProductInfo> futureTask = new FutureTask<ProductInfo>(new Callable() {
        @Override
        public ProductInfo call() throws Exception {
            return new ProductInfo("1","2");
        }
    });

    private final Thread thread = new Thread(futureTask);

    public void start(){thread.start();}

    public ProductInfo get()throws InterruptedException{
        try {
            return (ProductInfo)futureTask.get();
        }catch (Exception e){
            Throwable cause = e.getCause();
            e.printStackTrace();
        }finally {
            return null;
        }
    }


    class  ProductInfo{
        private String name;
        private String price;

        public ProductInfo(String name, String price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}
