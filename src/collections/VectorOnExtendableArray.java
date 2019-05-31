package collections;

import exceptions.BoundaryViolationException;

/**
 * 基于可扩充数组实现的向量类。
 * 可以更高效地利用存储空间。但是需要花费额外的时间——复制数组。
 * 注意：
 * 基于可扩充数组实现的向量，每次数组扩容的分摊运行时间为 O(1)。
 * java.util.ArrayList 和 java.util.Vector 类也提供了同样的功能。
 *
 * @author focusxyhoo
 * @date 2019-05-23 17:24
 */
public class VectorOnExtendableArray implements Vector {
    private int N = 8;
    private int n;
    private Object[] A;

    public VectorOnExtendableArray() {
        A = new Object[N];
        n = 0;
    }

    @Override
    public int getSize() {
        return n;
    }

    @Override
    public boolean isEmpty() {
        return 0 == n;
    }

    @Override
    public Object getAtRank(int rank) throws BoundaryViolationException {
        if (0 > rank || rank >= n) {
            throw new BoundaryViolationException("Rank out of boundary");
        }
        return A[rank];
    }

    @Override
    public Object replaceAtRank(int rank, Object object) throws BoundaryViolationException {
        if (0 > rank || rank >= n) {
            throw new BoundaryViolationException("Rank out of boundary");
        }
        Object element = A[rank];
        A[rank] = object;
        return element;
    }

    @Override
    public Object insertAtRank(int rank, Object object) throws BoundaryViolationException {
        // 注意在插入过程中，rank 的值是可以为 n 的。
        if (0 > rank || rank > n) {
            throw new BoundaryViolationException("Rank out of boundary");
        }
        // 这里即是与基于数组的实现方法的区别。
        if (n >= N) {
            N = N * 2;
            Object[] B = new Object[N];
            for (int i = 0; i < n; i++) {
                B[i] = A[i];
            }
            // 用 A 来指向新创建的数组，越来的数组会被垃圾收集器处理掉。
            A = B;
        }
        for (int i = n; i > rank; i--) {
            A[i] = A[i - 1];
        }
        A[rank] = object;
        n++;
        return object;
    }

    @Override
    public Object removeAtRank(int rank) throws BoundaryViolationException {
        if (0 > rank || rank >= n) {
            throw new BoundaryViolationException("Rank out of boundary");
        }
        Object object = A[rank];
        for (int i = rank; i < n; i++) {
            A[i] = A[i + 1];
        }
        n--;
        return object;
    }
}
