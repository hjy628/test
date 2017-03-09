package chap8;

import java.util.Set;

/**
 * Created by hjy on 17-3-9.
 *
 * 表示“搬箱子”之类谜题的抽象类
 *
 * Puzzle给出了谜题的抽象类，其中的类型参数P和M表示位置类和移动类
 *
 */
public interface Puzzle<P,M> {
    P initialPosition();
    boolean isGoal(P position);
    Set<M> legalMoves(P position);
    P move(P position,M move);
}

