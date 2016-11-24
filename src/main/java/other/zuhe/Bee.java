package other.zuhe;

/**
 * Created by hjy on 16-4-26.
 */
// 这个封装类封装了一个Attack类型的对象
public class Bee extends Insect implements Attack {
    private Attack attack;

    public Bee(int size, String color, Attack attack) {
        super(size, color);
        this.attack = attack;
    }

    @Override
    public void move() {
        attack.move();
    }

    @Override
    public void attack() {
        attack.attack();
    }
}
