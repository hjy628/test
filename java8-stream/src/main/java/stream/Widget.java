package stream;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hjy on 17-2-17.
 * 一个流的操作示例
 */
public class Widget {

    private final Color color;
    private final int weight;
    enum Color {RED, BLACK, BLUE}

    public Widget(Color color, int weight) {
        this.color = color;
        this.weight = weight;
    }

    public Color getColor() {return color;}
    public int getWeight() {return weight;}

    public static void main(String[] args) {
        List<Widget> widgets = new ArrayList<>();
        widgets.add(new Widget(Color.RED, 1));
        widgets.add(new Widget(Color.RED, 2));
        widgets.add(new Widget(Color.BLACK, 3));
        widgets.add(new Widget(Color.BLUE, 4));
        // stream() 获取当前的source, filter 和 mapToInt为intermediate操作, 进行数据筛选和转换,
        // 最后一个sum为terminal操作，对符合条件的全部widget做重量求和
        int sum = widgets.stream()
                .filter(w -> w.getColor() == Color.RED)
                .mapToInt(w -> w.getWeight())
                .sum();
        System.out.println(sum);// 3
    }

}
