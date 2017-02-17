package stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hjy on 17-2-17.
 * limit和skip对sorted后的运行次数无影响
 */
public class LimitSkipTest2 {

    public static void main(String[] args) {
        List<LimitSkipTest2.User> users = new ArrayList<>();
        LimitSkipTest2 limitSkipTest2 = new LimitSkipTest2();
        for (int i = 0; i < 5; i++) {
            users.add(limitSkipTest2.new User(i, "name_" + i));
        }
        // 对users做了13次微调，首先对5个元素的Stream排序，然后进行limit操作
        List<String> userList = users.stream()
                .sorted((p1, p2) -> p1.getName().compareTo(p2.getName()))
                .map(User::getName) // name_1,name_0,name_2,name_1,name_3,name_2,name_4,name_3,name_0,name_1,
                .limit(2)
                .collect(Collectors.toList());

        System.out.println(userList);// [name_0, name_1]


        /*
        *排序前进行limit和skip (这种优化是有business logic上的局限性的: 既不需要排序后再取值)
        */
        List<String> userList1 = users.stream()
                .limit(2)
                .sorted((p1, p2) -> p1.getName().compareTo(p2.getName()))
                .map(User::getName) // name_1,name_0,name_0,name_1,
                .collect(Collectors.toList());
        System.out.println(userList1);// [name_0, name_1]


    }
    // 内部类
    class User {
        public int no;
        private final String name;
        public User(int no, String name) { this.no = no; this.name = name; }
        public String getName() { System.out.print(name); return name; }
    }

}
