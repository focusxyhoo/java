package collections;

import exceptions.BoundaryViolationException;

/**
 * 基于数组实现的向量类。
 * 这一实现所占有的空间量取决于向量的最大规模，而与实际规模无关，为 O(N)。
 * 只有插入和删除的时间复杂度为 O(N)，其他操作的都为常量时间。这是因为插入和删除操作都需要移动大量的元素。
 * 缺点：
 * 数组容量N是固定的，因而在实际应用中不灵活。
 *
 * @author focusxyhoo
 * @date 2019-05-23 17:04
 */
public class VectorOnArray implements Vector {

    // 数组的默认大小。
    private final int N = 1024;
    // 向量的实际规模。
    private int n = 0;
    // 对象数组。
    private Object[] A;

    // 构造方法
    public VectorOnArray() {
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
        if (0 > rank || rank > n) {
            throw new BoundaryViolationException("Rank out of boundary");
        }
        if (n >= N) {
            throw new BoundaryViolationException("数组越界");
        }
        // 后续元素依次后移。
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
        Object element = A[rank];
        // 后续元素依次前移。
        for (int i = rank; i < n; i++) {
            A[i] = A[i + 1];
        }
        n--;
        return element;
    }
}
