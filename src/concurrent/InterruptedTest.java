package concurrent;

/**
 * @author focusxyhoo
 * @date 2019-05-15 09:14
 */
public class InterruptedTest {

    public static void main(String[] args) throws InterruptedException {

        // 采用匿名类的形式
//        Thread t = new Thread(new Runnable() {
//            @Override
//            public void run() {
                // 通过检查当前线程中断标志来控制是否退出循环
//                while (!Thread.currentThread().isInterrupted())
//                    System.out.println(Thread.currentThread() + " hello");
//            }
//        });

        // 采用Lambda表达式的形式
        Thread t = new Thread(() -> System.out.println(Thread.currentThread() + " Hello"));

        t.start();

        Thread.sleep(1000);
        System.out.println("Main thread interrupts thread. ");
        // 主线程调用子线程的interrupt()方法设置了中断标志
        t.interrupt();
        t.join();
        System.out.println("Main thread is over. ");


    }
}
