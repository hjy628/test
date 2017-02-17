package stream;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by hjy on 17-2-17.
 * partitioningBy 按照未成年人和成年人归组
 * 在使用条件“年龄小于18”进行分组后可以看到，不到18岁的未成年人是一组，成年人是另外一组。
 */
public class AdultPartition {

    public static void main(String[] args) {
        AdultPartition adultPartition = new AdultPartition();
        Map<Boolean, List<User>> children = Stream.generate(adultPartition.new UserSupplier())
                .limit(100)
                .collect(Collectors.partitioningBy(p -> p.age > 18));
        System.out.println("Children number:" + children.get(false).size());
        System.out.println("Adult number:" + children.get(true).size());
    }

    class UserSupplier implements Supplier<User> {
        private int index = 0;
        private final Random random = new Random();
        @Override
        public User get() {
            return new User(index++, "name_" + index, random.nextInt(100));
        }
    }
    class User {
        public int no;
        public String name;
        public int age;
        public User(int no, String name, int age) { this.no = no;  this.name = name;  this.age = age; }
    }

}
