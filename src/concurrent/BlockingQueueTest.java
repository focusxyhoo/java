package concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author focusxyhoo
 * @date 2019-05-14 16:47
 */
public class BlockingQueueTest {
    public static void main(String[] args) {
        BlockingQueue<Integer> q = new ArrayBlockingQueue<>(20);
        Producer p = new Producer(q);
        Producer pp = new Producer(q);
        Consumer c1 = new Consumer(q);
        Consumer c2 = new Consumer(q);
        Consumer c3 = new Consumer(q);

        new Thread(p).start();
        new Thread(pp).start();
        new Thread(c1).start();
        new Thread(c2).start();
        new Thread(c3).start();

    }
}

class Producer implements Runnable {

    private final BlockingQueue<Integer> queue;
    private int i = 0;

    Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                queue.put(produce());
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private int produce() {
        while (true) {
            i++;
            System.out.println("生产者线程 " + Thread.currentThread().getName() + " 将 " + i + " 放入阻塞队列中");
            return i;
        }
    }
}

class Consumer implements Runnable {
    private final BlockingQueue<Integer> queue;

    Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                consume(queue.take());
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void consume(int x) {
        System.out.println("消费者线程 " + Thread.currentThread().getName() + " 从阻塞队列中取出：" + x);
    }
}