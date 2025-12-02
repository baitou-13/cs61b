package deque;

import java.util.Comparator;
import java.util.Iterator;

// 继承 ArrayDeque，复用所有 Deque 方法（addFirst、removeLast 等）
public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private final Comparator<T> comparator; // 构造时传入的比较器

    // 题目要求的构造器：仅传入 Comparator（默认容量）
    public MaxArrayDeque(Comparator<T> c) {
        super(); // 调用 ArrayDeque 的无参构造（默认容量 8）
        this.comparator = c;
    }

    // 可选构造器：传入 Comparator 和自定义容量（保持灵活性）
    public MaxArrayDeque(Comparator<T> c, int capacity) {
        super(capacity); // 调用 ArrayDeque 的带容量构造
        this.comparator = c;
    }

    public T max() {
        // 复用带参数的 max 方法，传入构造器的 comparator
        return max(this.comparator);
    }

    public T max(Comparator<T> c) {
        // 1. 空队列直接返回 null
        if (isEmpty()) {
            return null;
        }
        // 2. 校验比较器非空（避免 NPE）
        if (c == null) {
            throw new IllegalArgumentException("Comparator cannot be null");
        }

        // 3. 遍历队列找最大值（使用父类的迭代器，避免重复实现遍历逻辑）
        T maxElement = null;
        Iterator<T> iterator = iterator(); // 复用 ArrayDeque 的 iterator
        while (iterator.hasNext()) {
            T current = iterator.next();
            if (maxElement == null || c.compare(current, maxElement) > 0) {
                maxElement = current;
            }
        }
        return maxElement;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o); // 复用父类 ArrayDeque 的 equals（若父类实现），或直接 return true
    }
}