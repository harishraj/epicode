package com.additional.ds;

import java.util.Stack;

public class MaxStack {

    long max;
    Stack<Long> stack;

    public MaxStack() {
        stack = new Stack<>();
    }

    public static void main(String args[]) {

        MaxStack maxStack = new MaxStack();
        maxStack.push(5);
        maxStack.push(-1);
        maxStack.push(3);
        maxStack.push(2);
        maxStack.push(-2);

        maxStack.getMax();
        maxStack.pop();

        maxStack.getMax();
        maxStack.pop();

        maxStack.getMax();
        maxStack.pop();

        maxStack.getMax();
        maxStack.pop();

        maxStack.getMax();
        maxStack.pop();
    }

    public void push(int x) {
        if (stack.isEmpty()) {
            stack.push(0L);
            max = x;
        } else {
            stack.push(x - max);//Could be negative if min value needs to change
            if (x > max) max = x;
        }
    }

    public void pop() {
        if (stack.isEmpty()) return;

        long pop = stack.pop();

        if (pop < 0) max = max - pop;//If negative, increase the min value

    }

    public int top() {
        long top = stack.peek();
        if (top > 0) {
            return (int) (top + max);
        } else {
            return (int) (max);
        }
    }

    public int getMax() {
        return (int) max;
    }
}

