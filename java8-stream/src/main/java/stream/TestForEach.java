package stream;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hjy on 17-2-17.
 * 打印姓名 (forEach 和pre-java8的对比) 【forEach 不能修改自己包含的本地变量值，也不能用break/return 之类的关键字提前结束循环】
 */
public class TestForEach {

    public static void main(String[] args) {
        List<Person> roster = new ArrayList<>();
        roster.add(new Person(Person.Sex.FEMALE, "Lisa"));
        roster.add(new Person(Person.Sex.MALE, "King"));
        roster.add(new Person(Person.Sex.MALE, "Jake"));
        // JDK 8
        roster.stream().filter(p -> p.gender == Person.Sex.MALE)
                .forEach(p -> System.out.println(p.name));
        // JDK 7
        for (Person p : roster) {
            if(p.gender == Person.Sex.MALE){
                System.out.println(p.name);
            }
        }
    }
}
class Person {
    Sex gender;
    String name;
    public enum Sex { MALE, FEMALE }
    public Person(Sex gender, String name) {
        this.gender = gender;
        this.name = name;
    }

}
