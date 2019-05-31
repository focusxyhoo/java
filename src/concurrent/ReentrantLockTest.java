package concurrent;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用 ReentrantLock 来实现一个简单的线程安全的 list。
 * ReentrantLock 是独占锁，
 * 在读多写少的情况下性能很差。
 *
 * @author focusxyhoo
 * @date 2019-05-15 15:50
 */
public class ReentrantLockTest {

    private ArrayList<String> arrayList = new ArrayList<>();
    private volatile Lock lock = new ReentrantLock();

    public void add(String s) {
        lock.lock();
        try {
            arrayList.add(s);
        } finally {
            lock.unlock();
        }
    }

    public void remove(String s) {
        lock.lock();
        try {
            arrayList.remove(s);
        } finally {
            lock.unlock();
        }
    }

    public String get(int index) {
        lock.lock();
        try {
            return arrayList.get(index);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

    }
}
