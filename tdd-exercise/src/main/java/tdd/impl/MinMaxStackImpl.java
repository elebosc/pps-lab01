package tdd.impl;

import tdd.api.MinMaxStack;
import tdd.utils.MinMaxPair;

import java.util.Stack;

public class MinMaxStackImpl implements MinMaxStack {

    private final Stack<Integer> stack;
    private final Stack<MinMaxPair> minMaxPairs;

    public MinMaxStackImpl() {
        this.stack = new Stack<>();
        this.minMaxPairs = new Stack<>();
    }

    @Override
    public void push(int value) {
        this.stack.push(value);
        if (minMaxPairs.isEmpty()) {
            minMaxPairs.push(new MinMaxPair(value, value));
        } else {
            final MinMaxPair lastMinMaxPair = minMaxPairs.peek();
            minMaxPairs.push(
                new MinMaxPair(
                    Math.min(value, lastMinMaxPair.min()),
                    Math.max(value, lastMinMaxPair.max())
                )
            );
        }
    }

    @Override
    public int pop() {
        if (this.stack.isEmpty()) {
            throw new IllegalStateException("The stack is empty.");
        }
        this.minMaxPairs.pop();
        return this.stack.pop();
    }

    @Override
    public int peek() {
        if (this.stack.isEmpty()) {
            throw new IllegalStateException("The stack is empty.");
        }
        return this.stack.peek();
    }

    @Override
    public int getMin() {
        return minMaxPairs.peek().min();
    }

    @Override
    public int getMax() {
        return minMaxPairs.peek().max();
    }

    @Override
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    @Override
    public int size() {
        return this.stack.size();
    }
}
