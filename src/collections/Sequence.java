package collections;

import exceptions.BoundaryViolationException;
import exceptions.PositionInvalidException;

/**
 * 序列接口。
 * 通过 rank 和 position 的转换来将秩和位置的概念联系起来。
 * 通过多重继承来继承 Vector 和 List 的方法。
 * 实现序列的最简单的方式就是利用双向链表。
 *
 * @author focusxyhoo
 * @date 2019-05-23 20:55
 */
public interface Sequence extends Vector, List {
    // 若 0 <= rank < getSize()，则返回秩为 rank 的元素的所在位置；否则报错。
    Position rank2Position(int rank) throws BoundaryViolationException;

    // 若 position 是序列中的合法位置，则返回存放在 position 处的元素的秩；否则报错。
    int positon2Rank(Position position) throws PositionInvalidException;
}
