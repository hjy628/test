package other.zuhe;

/**
 * Created by hjy on 16-4-26.
 */
public class InheritanceVSComposition2 {
    public static void main(String[] args) {
        Bee a = new Bee(1, "black", new AttackImpl("fly", "move"));
        a.attack();

        // if you need another implementation of move()
        // there is no need to change Insect, we can quickly use new method to attack

        Bee b = new Bee(1, "black", new AttackImpl("fly", "sting"));
        b.attack();
    }
}
