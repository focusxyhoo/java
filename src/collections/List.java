package collections;

import exceptions.BoundaryViolationException;
import exceptions.PositionInvalidException;

/**
 * 列表接口。
 *
 * @author focusxyhoo
 * @date 2019-05-23 20:05
 */
public interface List {
    int getSize();

    boolean isEmpty();

    // 返回第一个元素的位置。
    Position first();

    // 返回最后一个元素的位置。
    Position last();

    // 返回紧接给定位置之后的元素的位置。
    Position getNext(Position position) throws PositionInvalidException, BoundaryViolationException;

    Position getPrev(Position position) throws PositionInvalidException, BoundaryViolationException;

    // 将 object 作为第一个元素插入到列表中。
    Position insertFirst(Object object);

    Position insertLast(Object object);

    Position insertAfter(Position position, Object object) throws PositionInvalidException;

    Position insertBefore(Position position, Object object) throws PositionInvalidException;

    Object remove(Position position) throws PositionInvalidException;

    Object removeFirst();

    Object removeLast();

    Object replace(Position position, Object object) throws PositionInvalidException;

    // 位置迭代器。
    Iterator positions();

    // 元素迭代器。
    Iterator elements();
}
