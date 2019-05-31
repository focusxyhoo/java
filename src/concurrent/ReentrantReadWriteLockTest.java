package concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 使用 ReentrantReadWriteLock 来实现线程安全的 list。
 *
 * @author focusxyhoo
 * @date 2019-05-15 15:59
 */
public class ReentrantReadWriteLockTest {

    // 线程不安全的 list
    private List<String> arrayList = new ArrayList<>();
    // 独占锁
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    // 添加元素
    public void add(String s) {
        writeLock.lock();
        try {
            arrayList.add(s);
        } finally {
            writeLock.unlock();
        }
    }

    // 删除元素
    public void remove(String s) {
        writeLock.lock();
        try {
            arrayList.remove(s);
        } finally {
            writeLock.unlock();
        }
    }

    // 获取元素
    public String get(int index) {
        readLock.lock();
        try {
            return arrayList.get(index);
        } finally {
            readLock.unlock();
        }
    }

    public static void main(String[] args) {

    }
}
