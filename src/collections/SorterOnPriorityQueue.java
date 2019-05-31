package collections;

/**
 * created with IntelliJ IDEA
 * author      : focusxyhoo
 * date        : 2019-05-30
 * time        : 10:16
 * description : 基于优先队列的排序器
 * 当优先队列基于无序列表实现时，delMin()方法时间复杂度为 O(n)，因此总的时间复杂度为 O(n^2)。
 * 当优先队列基于有序列表实现时，delMin()方法可以在常数时间内完成，因此sort()方法后一阶段的时间复杂度可以降至 O(n)。
 * 然后这并非没有代价。为了维护列表的有序性，前一阶段的每一次 insert() 操作，都需要对列表进行扫描，时间复杂度为 O(n^2)。
 *
 */
public class SorterOnPriorityQueue implements Sorter {
    private Comparator C;

    public SorterOnPriorityQueue() {
        this(new DefaultComparator());
    }

    public SorterOnPriorityQueue(Comparator comp) {
        C = comp;
    }

    public void sort(Sequence S) {
        // 为批处理建立优先队列而准备的序列
        Sequence T = new SequenceOnDLNode();

        while (!S.isEmpty()) {
            Object e = S.removeFirst(); // 从 S 中逐一取出元素
            T.insertLast(new DefaultEntry(e, e)); // 用节点元素本身作为关键码
        }
//        PriorityQueue queue = new PriorityQueueOnUnsortedList(C, T);
        PriorityQueue queue = new PriorityQueueOnSortedList(C, T);
//        PriorityQueue queue = new PriorityQueueOnHeap(C, T);

        while (!queue.isEmpty()) {
            S.insertLast(queue.delMin().getValue());
            System.out.println("\t: \t" + S.last().getElement());

        }
    }
}
