package stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hjy on 16-11-23.
 */
public class Test {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(new Dish("pork",false,800, Dish.Type.MEAT),
                new Dish("beef",false,700, Dish.Type.MEAT),
                new Dish("chicken",false,400, Dish.Type.MEAT),
                new Dish("french fries",true,530, Dish.Type.OTHER),
                new Dish("rice",true,350, Dish.Type.OTHER),
                new Dish("season fruit",true,120, Dish.Type.OTHER),
                new Dish("pizza",true,550, Dish.Type.OTHER),
                new Dish("prawns",false,300, Dish.Type.FISH),
                new Dish("salmon",false,450, Dish.Type.FISH)
                );

        List<String> threeHighCaloricDishNames = menu.stream() //从menu获得流
                .filter(d -> d.getCalories() >300)  //建立操作流水线：首先选出高热量的菜肴
                .map(Dish::getName) //获取菜名
                .limit(3)   //只选择3个
                .collect(Collectors.toList());  //将结果保存在另一个List中
        System.out.println(threeHighCaloricDishNames);      //结果是[pork, beef, chicken]



    }
}
