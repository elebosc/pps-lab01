package tdd.impl;

import tdd.api.CircularQueue;

import java.util.ArrayList;
import java.util.List;

public class CircularQueueImpl implements CircularQueue {

    private final int[] queue;
    private int actualSize;
    private int front;
    private int rear;

    public CircularQueueImpl(int size) {
        this.queue = new int[size];
        this.actualSize = 0;
        this.front = 0;
        this.rear = 0;
    }

    @Override
    public int getAllocatedSize() {
        return this.queue.length;
    }

    @Override
    public int getActualSize() {
        return this.actualSize;
    }

    @Override
    public boolean isEmpty() {
        return this.actualSize == 0;
    }

    @Override
    public void enqueue(int element) {
        if (this.actualSize == this.getAllocatedSize()) {
            this.front = (this.front + 1) % this.getAllocatedSize();
            this.actualSize--;
        }
        this.queue[this.rear] = element;
        this.rear = (this.rear + 1) % this.getAllocatedSize();
        this.actualSize++;
    }

    @Override
    public int dequeue() {
        if (this.isEmpty()) {
            throw new IllegalStateException("The queue is empty.");
        }
        int element = this.queue[this.front];
        this.front = (this.front + 1) % this.getAllocatedSize();
        this.actualSize--;
        return element;
    }

    @Override
    public int peekFront() {
        if (this.isEmpty()) {
            throw new IllegalStateException("The queue is empty.");
        }
        return this.queue[this.front];
    }

    @Override
    public int peekRear() {
        if (this.isEmpty()) {
            throw new IllegalStateException("The queue is empty.");
        }
        final int index = (this.rear - 1 + this.getAllocatedSize()) % this.getAllocatedSize();
        return this.queue[index];
    }

}
