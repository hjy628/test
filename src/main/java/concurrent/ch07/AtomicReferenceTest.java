package concurrent.ch07;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by hjy on 16-2-26.
 */
public class AtomicReferenceTest {
    public static AtomicReference<User> atomicUserRef = new AtomicReference<User>();


    public static void main(String[] args) {
        User user = new User("hjy",25);
        atomicUserRef.set(user);
        User updateUser = new User("huangjiyu",24);
        atomicUserRef.compareAndSet(user,updateUser);
        System.out.println(atomicUserRef.get().getName());
        System.out.println(atomicUserRef.get().getOld());
    }




    static class User{
        private String name;
        private int old;

        public User(String name, int old) {
            this.name = name;
            this.old = old;
        }

        public String getName() {
            return name;
        }

        public int getOld() {
            return old;
        }
    }
}
