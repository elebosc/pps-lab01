package tdd.impl;

import tdd.api.CircularQueue;

import java.util.ArrayList;
import java.util.List;

public class CircularQueueImpl implements CircularQueue {

    private final List<Integer> queue;
    private final int allocatedSize;

    public CircularQueueImpl(int size) {
        this.queue = new ArrayList<>(size);
        this.allocatedSize = size;
    }

    @Override
    public int getAllocatedSize() {
        return this.allocatedSize;
    }

    @Override
    public int getActualSize() {
        return this.queue.size();
    }

    @Override
    public boolean isEmpty() {
        return this.queue.isEmpty();
    }

    @Override
    public void enqueue(int element) {
        this.queue.add(element);
        if (this.queue.size() > this.allocatedSize) {
            this.queue.remove(0);
        }
    }

    @Override
    public int dequeue() {
        if (this.isEmpty()) {
            throw new IllegalStateException("The queue is empty.");
        }
        return this.queue.remove(0);
    }

    @Override
    public int peekFront() {
        if (this.isEmpty()) {
            throw new IllegalStateException("The queue is empty.");
        }
        return this.queue.get(0);
    }

    @Override
    public int peekRear() {
        if (this.isEmpty()) {
            throw new IllegalStateException("The queue is empty.");
        }
        return this.queue.get(this.getActualSize() - 1);
    }

}
