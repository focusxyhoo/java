package collections;

import exceptions.NoSuchElementException;

/**
 * 基于列表实现的元素迭代器。
 *
 * @author focusxyhoo
 * @date 2019-05-23 21:51
 */
public class IteratorOnElement implements Iterator {

    private List list;
    private Position nextPosition;

    // 默认构造方法
    public IteratorOnElement() {
        list = null;
    }

    public IteratorOnElement(List list) {
        this.list = list;
        if (list.isEmpty()) {
            nextPosition = null;
        } else nextPosition = list.first();
    }

    @Override
    public boolean hasNext() {
        return null != nextPosition;
    }

    @Override
    public Object getNext() {
        if (!hasNext()) throw new NoSuchElementException("没有下一个元素");
        Position currentPosition = nextPosition;
        if (currentPosition == list.last()) nextPosition = null;
        else nextPosition = list.getNext(currentPosition);
        return currentPosition.getElement();
    }
}
