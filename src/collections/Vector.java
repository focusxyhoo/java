package collections;

import exceptions.BoundaryViolationException;

/**
 * 向量接口。
 *
 * @author focusxyhoo
 * @date 2019-05-23 16:58
 */
public interface Vector {

    int getSize();

    boolean isEmpty();

    Object getAtRank(int rank) throws BoundaryViolationException;

    // 在删除和替换某个位置的元素时，都会返回此位置中的原来元素。
    Object replaceAtRank(int rank, Object object) throws BoundaryViolationException;

    // 返回插入的元素，方便链式编程。
    Object insertAtRank(int rank, Object object) throws BoundaryViolationException;

    Object removeAtRank(int rank) throws BoundaryViolationException;
}
