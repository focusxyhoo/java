package concurrent;

/**
 * @author focusxyhoo
 * @date 2019-05-14 14:40
 */

/**
 * Runnable是可以共享数据的，多个Thread可以同时加载一个Runnable
 * 当各自Thread获得CPU时间片的时候开始运行Runnable，Runnable里面的资源是被共享的
 * 可以与ThreadTest中的结果进行比较
 */

public class ThreadTest {
    public static void main(String[] args) {
        new MyThread().start();
        new MyThread().start();
        new MyThread().start();
    }
}


class MyThread extends Thread {
    private int tickets = 5;

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if (tickets > 0) {
                System.out.println(Thread.currentThread() + ": 第 " + tickets-- + " 张车篇");
            }
        }
    }
}