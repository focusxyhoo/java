package collections;

import exceptions.BoundaryViolationException;
import exceptions.PositionInvalidException;

/**
 * 基于双向链表是实现序列的最简单的方法。
 * 需要额外实现 Vector 接口中的一些方法。
 * 来自列表的每个方法都可以保持原先的时间复杂度 O(1)。
 * 而来自向量的方法就不能得到保证，为了显式地保留和维护各元素的秩，不得不从列表某一端逐一扫描各个元素，
 * 直到发现秩为 rank 的元素。
 * 因此这些操作的时间复杂度都为 O(n)。
 *
 * 而如果要基于数组来实现序列，我们需要对每一个位置对象设置一个引用。
 * 一种可行的办法是：
 * 不将具体的元素直接存放在数组的每一项中，而是代之以一种位置对象，而真正的元素则存放到各个位置中。
 * 这样，每个位置对象p不仅有一个下标，同时还存放了对应的元素。
 * 采用这种方法的话，插入和删除方法都需要消耗 O(n) 时间，其他基于位置的操作只需要 O(1) 时间。
 *
 * @author focusxyhoo
 * @date 2019-05-23 21:01
 */
public class SequenceOnDLNode extends ListOnDLNode implements Sequence {

    // 检查 rank 是否在 [0, n) 之间。
    private void checkRank(int rank, int n) {
        if (rank < 0 || rank >= n) throw new BoundaryViolationException("秩非法");
    }

    @Override
    public Object getAtRank(int rank) throws BoundaryViolationException {
        checkRank(rank, getSize());
        return rank2Position(rank).getElement();
    }

    @Override
    public Object replaceAtRank(int rank, Object object) throws BoundaryViolationException {
        checkRank(rank, getSize());
        return replace(rank2Position(rank), object);
    }

    @Override
    public Object insertAtRank(int rank, Object object) throws BoundaryViolationException {
        checkRank(rank, getSize() + 1);
        if (getSize() == rank) insertLast(object);
        else insertBefore(rank2Position(rank), object);
        return object;
    }

    @Override
    public Object removeAtRank(int rank) throws BoundaryViolationException {
        checkRank(rank, getSize());
        return remove(rank2Position(rank));
    }

    @Override
    public Position rank2Position(int rank) throws BoundaryViolationException {
        DLNode dlNode;
        checkRank(rank, getSize());
        if (rank < getSize() / 2) {
            // 若秩较小，从头开始扫描。
            dlNode = header.getNext();
            for (int i = 0; i < rank; i++) dlNode = dlNode.getNext();
        } else {
            // 若秩较大，从尾开始扫描。
            dlNode = tailer.getPrev();
            for (int i = 1; i < getSize() - rank; i++) {
                dlNode = dlNode.getPrev();
            }
        }
        return dlNode;
    }

    @Override
    public int positon2Rank(Position position) throws PositionInvalidException {
        DLNode dlNode = header.getNext();
        int rank = 0;
        while (dlNode != tailer) {
            if (dlNode == position) return rank;
            dlNode = dlNode.getNext();
            rank++;
        }
        throw new PositionInvalidException("序列中找不到该位置");
    }
}
