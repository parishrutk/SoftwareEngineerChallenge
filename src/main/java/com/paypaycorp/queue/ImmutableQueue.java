package com.paypaycorp.queue;

import com.paypaycorp.queue.comparator.QueueComparator;
import org.apache.log4j.Logger;

import java.util.Comparator;

public final class ImmutableQueue<T> implements Queue {

    private static final Logger LOGGER = Logger.getLogger(ImmutableQueue.class);
    private static final long serialVersionUID = -6849794470754667710L;

    private static int front = 0; //Starting position would be 0 always for front.
    private static int rear = 0; //Starting position would be 0 always rear.

    public static final Comparator CASE_INSENSITIVE_ORDER = new QueueComparator();

    Object[] elements = new Object[10];

    public ImmutableQueue(Object[] elements) {
        this.elements = elements;
    }

    @Override
    public Queue enQueue(Object element) {

        elements[rear] = element;
        rear++;
        return this;
    }

    @Override
    public Queue deQueue() {
        if (rear != front) {
            Object o = this.elements[front];
            LOGGER.info("DeQueued : " + o);
            System.out.println("DeQueued : " + o);
            this.elements[front] = null;

            front++;
        }
        return this;
    }

    @Override
    public Object head() {
        return elements[front];
    }

    @Override
    public boolean isEmpty() {
        return front == rear;
    }

    @Override
    public boolean isFull() {
        return (rear == elements.length);
    }

    @Override
    public int getSize() {
        return rear - front;
    }

    public static int getFront() {
        return front;
    }

    public static int getRear() {
        return rear;
    }
}
