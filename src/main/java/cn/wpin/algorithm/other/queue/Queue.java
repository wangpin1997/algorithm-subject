package cn.wpin.algorithm.other.queue;

/**
 * 自己来实现一个队列
 * @author wangpin
 */
public interface Queue<E> {

    /**
     * 添加元素，队列满了就会抛异常
     * @param e
     * @return
     */
    boolean add(E e);

    /**
     * 也是添加元素，队列满了就返回false
     * @param e
     * @return
     */
    boolean offer(E e);

    /**
     * 删除第一个元素，队列为空再删除时抛异常
     * @return
     */
    E remove();

    /**
     * 删除第一个元素，队列为空返回null
     * @return
     */
    E poll();

    /**
     * 返回队头元素，当队列为空就抛出异常
     * @return
     */
    E element();

    /**
     * 返回队头元素，当队列为空就返回null
     * @return
     */
    E peek();


    boolean isFull();

    boolean isEmpty();



}
