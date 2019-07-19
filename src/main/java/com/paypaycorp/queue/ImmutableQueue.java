package com.paypaycorp.queue;

import com.paypaycorp.queue.comparator.QueueComparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Comparator;

public final class ImmutableQueue<T> implements Queue {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImmutableQueue.class);

    private static final long serialVersionUID = -6849794470754667710L;

    private static int front; //Starting position would be 0 always for front.
    private static int rear; //Starting position would be 0 always rear.

    public static final Comparator CASE_INSENSITIVE_ORDER = new QueueComparator();

    Object[] elements = new Object[10];

    public ImmutableQueue(Object[] elements) {
        this.elements = elements;
    }

    public ImmutableQueue() {
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
            LOGGER.info("DeQueued : {} ", o);
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

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ImmutableQueue<?> that = (ImmutableQueue<?>) o;
        return Arrays.equals(elements, that.elements);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(elements);
    }
}
