package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author focusxyhoo
 * @date 2019-05-14 15:06
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
        // 创建线程池
        ExecutorService pool = Executors.newCachedThreadPool();
        pool.execute(new MyRunnableA());
        pool.execute(new MyRunnableB());
        pool.shutdown();
    }
}

class MyRunnableA implements Runnable {
    @Override
    public void run() {
        System.out.println("Running Runnable A...");
        int i = 0;
        while (i < 20) {
            i ++;
            System.out.println("当前线程：" + Thread.currentThread().getName());
            for (int j = 0; j < 10; j++) {
                System.out.println("i = " + i);
            }
        }
    }
}

class MyRunnableB implements Runnable {
    @Override
    public void run() {
        char ch = 'A' - 1;
        while (ch < 'Z') {
            ch ++;
            System.out.println("当前的线程：" + Thread.currentThread().getName());
            for (int j = 0; j < 10; j++) {
                System.out.println("ch = " + ch);
            }
        }
    }
}
