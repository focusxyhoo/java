package concurrent;

/**
 * @author focusxyhoo
 * @date 2019-05-14 14:49
 */
public class RunnableTest {

    public static void main(String[] args) {
        Runnable r = new MyThread1();
        new Thread(r).start();
        new Thread(r).start();
    }
}

class MyThread1 implements Runnable {
    private int tickets = 10;

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if (tickets > 0) {
                System.out.println(Thread.currentThread() + ": 第 " + tickets-- + " 张车篇");
            }
        }
    }
}
