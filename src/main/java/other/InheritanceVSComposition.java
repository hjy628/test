package other;

/**
 * Created by hjy on 16-4-26.
 */
class Bee extends Insect {
    public Bee(int size, String color) {
        super(size, color);
    }

    public void move() {
        System.out.println("Fly");
    }

    public void attack() {
        move();
        super.attack();
    }
}

public class InheritanceVSComposition {
    public static void main(String[] args) {
        Insect i = new Bee(1, "red");
        i.attack();
    }
}
