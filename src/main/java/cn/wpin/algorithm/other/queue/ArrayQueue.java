package cn.wpin.algorithm.other.queue;

import java.util.NoSuchElementException;

/**
 * 基于数组实现的队列
 *
 * @author wangpin
 */
public class ArrayQueue<E> implements Queue<E> {

    /**
     * 实际装元素的数组
     */
    private Object[] elements;

    /**
     * 头指针
     */
    private int head;

    /**
     * 尾指针
     */
    private int tail;

    public ArrayQueue(int capacity) {
        elements = new Object[capacity];
        head = -1;
        tail = -1;
    }


    @Override
    public boolean add(E o) {
        if (!offer(o)) {
            throw new IllegalStateException("元素已满，无法添加");
        }
        return true;
    }

    @Override
    public boolean offer(E o) {
        if (isFull()) {
            return false;
        }
        elements[++tail] = o;
        return true;
    }

    @Override
    public E remove() {
        E e = poll();
        if (e == null) {
            throw new NoSuchElementException("队列为空，无法删除元素");
        }
        return e;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E e = (E) elements[++head];
        elements[head] = null;
        return e;
    }

    @Override
    public E element() {
        E e = peek();
        if (e == null) {
            throw new NoSuchElementException("队列为空，无法查询队头");
        }
        return e;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return (E) elements[head];
    }

    @Override
    public boolean isFull() {
        return elements.length - 1 == tail;
    }

    @Override
    public boolean isEmpty() {
        return head == tail && tail == -1;
    }
}
