package collections;

/**
 * 抽象出一个位置概念的接口。
 * 方便设置位置对象，以及返回位置对象的方法。
 *
 * @author focusxyhoo
 * @date 2019-05-23 15:41
 */
public interface Position {
    // 返回存放于该位置的元素。
    Object getElement();

    // 将给定元素放至该位置，返回此前存放的位置。
    Object setElement(Object element);
}
