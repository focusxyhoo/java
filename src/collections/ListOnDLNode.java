package collections;

import exceptions.BoundaryViolationException;
import exceptions.ListEmptyException;
import exceptions.PositionInvalidException;

/**
 * 基于双向链表实现列表结构。
 *
 * @author focusxyhoo
 * @date 2019-05-23 20:16
 */
public class ListOnDLNode implements List {

    // 列表的实际规模。
    protected int numElement;
    // 作为哨兵的首节点和末节点。
    protected DLNode header, tailer;

    // 构造方法
    public ListOnDLNode() {
        numElement = 0;
        header = new DLNode();
        tailer = new DLNode();
        header.setNext(tailer);
        tailer.setPrev(header);
    }

    // 辅助方法：检查给定位置在列表中是否合法，若是，则类型转换为 DLNode。
    private DLNode checkPosition(Position position) throws PositionInvalidException {
        if (null == position) {
            throw new PositionInvalidException("位置对象为 null");
        }
        if (header == position) throw new PositionInvalidException("头节点哨兵位置非法");
        if (tailer == position) throw new PositionInvalidException("尾节点哨兵位置非法");
        return (DLNode) position;
    }

    @Override
    public int getSize() {
        return numElement;
    }

    @Override
    public boolean isEmpty() {
        return 0 == numElement;
    }

    @Override
    public Position first() {
        if (isEmpty()) {
            throw new ListEmptyException("List Empty");
        }
        return header.getNext();
    }

    @Override
    public Position last() {
        if (isEmpty()) {
            throw new ListEmptyException("List Empty");
        }
        return tailer.getPrev();
    }

    @Override
    public Position getNext(Position position) throws PositionInvalidException, BoundaryViolationException {
        DLNode dlNode = checkPosition(position);
        DLNode next = dlNode.getNext();
        if (next == tailer) throw new BoundaryViolationException("企图越过列表后端");
        return next;
    }

    @Override
    public Position getPrev(Position position) throws PositionInvalidException, BoundaryViolationException {
        DLNode dlNode = checkPosition(position);
        DLNode prev = dlNode.getPrev();
        if (prev == header) throw new BoundaryViolationException("企图越过列表前端");
        return prev;
    }

    @Override
    public Position insertFirst(Object object) {
        numElement++;
        DLNode dlNode = new DLNode(object, header, header.getNext());
        header.getNext().setPrev(dlNode);
        header.setNext(dlNode);
        return dlNode;
    }

    @Override
    public Position insertLast(Object object) {
        numElement++;
        DLNode dlNode = new DLNode(object, tailer.getPrev(), tailer);
        if (null == tailer.getPrev()) System.out.println("Prev of tailer is NULL");
        tailer.getPrev().setNext(dlNode);
        tailer.setPrev(dlNode);
        return dlNode;
    }

    @Override
    public Position insertAfter(Position position, Object object) throws PositionInvalidException {
        DLNode p = checkPosition(position);
        numElement++;
        DLNode dlNode = new DLNode(object, p, p.getNext());
        p.getNext().setPrev(dlNode);
        p.setNext(dlNode);
        return dlNode;
    }

    @Override
    public Position insertBefore(Position position, Object object) throws PositionInvalidException {
        DLNode p = checkPosition(position);
        numElement++;
        DLNode dlNode = new DLNode(object, p.getPrev(), p);
        p.getPrev().setNext(dlNode);
        p.setPrev(dlNode);
        return dlNode;
    }

    @Override
    public Object remove(Position position) throws PositionInvalidException {
        DLNode p = checkPosition(position);
        numElement--;
        DLNode pPrev = p.getPrev();
        DLNode pNext = p.getNext();
        pPrev.setNext(pNext);
        pNext.setPrev(pPrev);
        Object pElement = p.getElement();
        // 将该节点（位置）从列表中移除，以便系统回收空间。
        p.setPrev(null);
        p.setNext(null);
        return pElement;
    }

    @Override
    public Object removeFirst() {
        return remove(header.getNext());
    }

    @Override
    public Object removeLast() {
        return remove(tailer.getPrev());
    }

    @Override
    public Object replace(Position position, Object object) throws PositionInvalidException {
        DLNode p = checkPosition(position);
        Object pElement = p.getElement();
        p.setElement(object);
        return pElement;
    }

    @Override
    public Iterator positions() {
        return new IteratorOnPosition(this);
    }

    @Override
    public Iterator elements() {
        return new IteratorOnElement(this);
    }
}
