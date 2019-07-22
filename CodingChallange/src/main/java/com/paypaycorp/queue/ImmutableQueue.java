package com.paypaycorp.queue;

import com.paypaycorp.queue.comparator.QueueComparator;
import org.apache.log4j.Logger;

import java.util.Comparator;

public final class ImmutableQueue<T> implements Queue {

    private static final Logger LOGGER = Logger.getLogger(ImmutableQueue.class);
    private static final long serialVersionUID = -6849794470754667710L;
    private int size;

    private static int front = 0; //Starting position would be 0 always for front.
    private static int rear = 0; //Starting position would be 0 always rear.

    //Future provision if we wish to sort the Queue we can use this comparator.
    public static final Comparator CASE_INSENSITIVE_ORDER = new QueueComparator();

    private Object[] elements;

    public ImmutableQueue(Object[] elements) {
        this.elements = elements;
    }

    public ImmutableQueue(int size) {
        this.size = size;
        this.elements = new Object[size];
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
            this.elements[front] = null;
            front++;
            if (isEmpty()) {
                LOGGER.info("Queue is Empty now.");
                rear = front = 0;
            }
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

    public Object[] getElements() {
        return elements;
    }

}
