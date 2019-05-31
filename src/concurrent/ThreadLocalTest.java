package concurrent;

/**
 * 这个例子展示了 ThreadLocal 的使用。
 *
 * @author focusxyhoo
 * @date 2019-05-15 09:58
 */
public class ThreadLocalTest {
    private static void print(String s) {
        System.out.println(s + ": " + localVariable.get());
        localVariable.remove();
    }

    private static ThreadLocal<String> localVariable = new ThreadLocal<>();

    public static void main(String[] args) {

        Thread tOne = new Thread(() -> {
            localVariable.set("tOne local variable");
            print("tOne");
            System.out.println("tOne remove after: " + localVariable.get());
        });

        Thread tTwo = new Thread(() -> {
            localVariable.set("tTwo local variable");
            print("tTwo");
            System.out.println("tTwo remove after: " + localVariable.get());
        });

        tOne.start();
        tTwo.start();
    }
}
