package concurrent;

/**
 * 这个例子展示了线程死锁的情况。
 * 满足死锁的四个条件：互斥条件、请求并持有条件、不可剥夺条件、环路等待条件。
 * 要避免死锁，只要破坏至少上述一个条件即可。
 * 目前只有请求并持有条件和环路等待条件是可以被破坏的。
 * 资源的有序分配也可以避免死锁。
 *
 * @author focusxyhoo
 * @date 2019-05-15 09:36
 */
public class DeadLockTest {

    private static Object resourceA = new Object();
    private static Object resourceB = new Object();

    public static void main(String[] args) {

        Thread ta = new Thread(() -> {
            synchronized (resourceA) {
                System.out.println(Thread.currentThread().getName() + " get resourceA.");

                try {
                    // 休眠是为了让对方线程能够拿到resourceB的锁
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + " waiting for resourceB.");
                synchronized (resourceB) {
                    System.out.println(Thread.currentThread().getName() + " get resourceB.");
                }
            }
        });

        Thread tb = new Thread(() -> {
            synchronized (resourceB) {
                System.out.println(Thread.currentThread().getName() + " get resourceB.");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + " waiting for resourceA.");
                synchronized (resourceA) {
                    System.out.println(Thread.currentThread().getName() + " get resourceA.");
                }
            }
        });


        ta.start();
        tb.start();

    }
}
