package Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.RandomAccess;

/**
 * created with IntelliJ IDEA
 * author      : focusxyhoo
 * date        : 2019-06-01
 * time        : 17:44
 * description :
 */
public class IteratorTest {
    public static void main(String[] args) {
//        Integer[] a = {1, 2, 3, 4, 5};
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
            it.remove();
            System.out.println("the length of list is: " + list.size());
            System.out.println("now the list is: " + list);
        }

        System.out.println(list instanceof RandomAccess);
    }
}
