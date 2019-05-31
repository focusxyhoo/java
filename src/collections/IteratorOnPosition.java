package collections;

import exceptions.NoSuchElementException;

/**
 * 基于列表实现的位置迭代器。
 *
 * @author focusxyhoo
 * @date 2019-05-23 21:42
 */
public class IteratorOnPosition implements Iterator {

    private List list;
    private Position nextPosition; // 当前（下一个）位置

    public IteratorOnPosition() {
        list = null;
    }

    public IteratorOnPosition(List list) {
        this.list = list;
        if (list.isEmpty()) nextPosition = null;
        else nextPosition = list.first();
    }


    @Override
    public boolean hasNext() {
        return nextPosition != null;
    }

    @Override
    public Object getNext() throws NoSuchElementException {
        if (!hasNext()) throw new NoSuchElementException("没有下一个位置");
        Position currentPosition = nextPosition;
        if (currentPosition == list.last()) nextPosition = null;
        else nextPosition = list.getNext(currentPosition);
        return currentPosition;
    }
}
