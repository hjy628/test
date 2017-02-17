package stream;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hjy on 17-2-17.
 *allMatch: Stream 中全部元素符合传入的predicate，返回true
 * anyMatch: Stream 中只要有一个元素符合传入的predicate，返回true
 * noneMatch: Stream 中没有一个元素符合传入的predicate，返回true
 */
public class MatchTest {

    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        MatchTest matchTest = new MatchTest();
        for (int i = 0; i < 5; i++) {
            users.add(matchTest.new User(i, "name_" + i, i * 5));
        }

        boolean isAllAdult = users.stream().allMatch(p -> {
            System.out.println(p.age); // 0 和 private final int age; ??
            return p.age > 18;
        });
        System.out.println("All are adult? " + isAllAdult); // All are adult? false
        boolean isAnyChild = users.stream().anyMatch(p -> p.age < 12);
        System.out.println("Any Child? " + isAnyChild); // Any Child? true
        boolean noneOldThan19 = users.stream().noneMatch(p -> p.age > 19);
        System.out.println("none Old Than 19? " + noneOldThan19);// none Old Than 19? false
        boolean noneOldThan50 = users.stream().noneMatch(p -> p.age > 50);
        System.out.println("none Old Than 50? " + noneOldThan50);// none Old Than 50? true
    }

    class User {
        public int no;
        public String name;
        private final int age;
        public User(int no, String name, int age) { this.no = no; this.name = name; this.age = age; }
    }

}
