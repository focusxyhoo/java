package concurrent;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 下面是一个通过多线程使用 AtomicLong 来统计 0 的个数的例子。
 * 在没有原子类的情况下，实现计数器需要一定的同步措施，如使用 synchronized 关键字，
 * 但是这会造成一定的性能损耗。
 * 在高并发情况下 AtomicLong 也会存性能问题，
 * 这时可以使用在高并发下性能更好的 LongAdder 类。
 *
 * @author focusxyhoo
 * @date 2019-05-15 14:57
 */
public class AtomicLongTest {
    private static AtomicLong atomicLong = new AtomicLong();
    private static Integer[] arrayOne = new Integer[]{0, 1, 2, 3, 0, 5, 6, 0, 56, 0};
    private static Integer[] arrayTwo = new Integer[]{10, 1, 2, 3, 0, 5, 6, 0, 56, 0};

    public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(() -> {
            int size = arrayOne.length;
            for (int i = 0; i < size; ++i) {
                if (arrayOne[i] == 0) {
                    atomicLong.incrementAndGet();
                }
            }
        });

        Thread threadTwo = new Thread(() -> {
            int size = arrayTwo.length;
            for (int i = 0; i < size; ++i) {
                if (arrayTwo[i] == 0) {
                    atomicLong.incrementAndGet();
                }
            }
        });

        threadOne.start();
        threadTwo.start();

        threadOne.join();
        threadTwo.join();

        System.out.println("count 0 : " + atomicLong.get());
    }

}
