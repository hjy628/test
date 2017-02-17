package stream;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by hjy on 17-2-17.
 * groupingBy 按照年龄归组
 */
public class AdultGroup {

    public static void main(String[] args) {
        AdultGroup adultGroup = new AdultGroup();
        Map<Integer, List<User>> children = Stream.generate(adultGroup.new UserSupplier())
                .limit(100)
                .collect(Collectors.groupingByConcurrent(User::getAge));

        Iterator it = children.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, List<User>> users = (Map.Entry) it.next();
            System.out.println("Age: " + users.getKey() + "=" + users.getValue().size());
        }
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
        public int getAge() { return age; }
    }

}
