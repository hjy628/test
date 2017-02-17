package stream;

/**
 * Created by hjy on 17-2-17.
 * 需求: 如果发现type为grocery的所有交易, 然后返回以交易值降序排序的交易ID集合
 */
public class Transaction {

    private final int id;
    private final Integer value;
    private final Type type;

    public Transaction(int id, Integer value, Type type) {
        this.id = id;
        this.value = value;
        this.type = type;
    }

    public enum Type {
        A, B, C, D, GEOCERY
    }

    public int getId() {return id;}
    public Integer getValue() {return value;}
    public Type getType() {return type;}

}
