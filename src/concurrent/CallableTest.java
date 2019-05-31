package concurrent;

/**
 * @author focusxyhoo
 * @date 2019-05-14 14:57
 */

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Runnable是执行工作的独立任务，但是它不返回任何值。
 * 如果你希望任务在完成的能返回一个值，那么可以实现Callable接口而不是Runnable接口。
 */

public class CallableTest {
    public static void main(String[] args) {

        Callable<Integer> c = new MyThread2();

        FutureTask<Integer> futureTask = new FutureTask<>(c);
        new Thread(futureTask, "线程名：有返回值的线程").start();

        try {
            System.out.println("子线程的返回值：" + futureTask.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class MyThread2 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("当前线程 " + Thread.currentThread().getName());
        int i = 0;
        for (; i < 5; i++) {
            System.out.println("循环变量 i 的值为：" + i);
        }
        return i;
    }
}
