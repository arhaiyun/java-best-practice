package com.exodus.leetcode.bytedance;

import java.util.Queue;
import java.util.Stack;

/**
 * @author arhaiyun
 * @version 1.0
 * @date 2020/5/3 18:47
 * <p>
 * 使用栈的数据结构实现队列功能
 */
public class StackQueue {
    private Stack<Integer> stack1 = new Stack<>();
    private Stack<Integer> stack2 = new Stack<>();

    public void push(Integer value) {
        if (value == null) {
            throw new RuntimeException("value is null");
        }
        stack1.push(value);
    }

    public Integer pop() {
        if (stack1.size() == 0) {
            return null;
        }
        while (!stack1.empty()) {
            stack2.push(stack1.pop());
        }
        Integer value = stack2.pop();
        while (!stack2.empty()) {
            stack1.push(stack2.pop());
        }
        return value;
    }

    public static void main(String[] args) {
        StackQueue queue = new StackQueue();
        queue.push(1);
        queue.push(3);
        queue.push(5);
        queue.pop();
        queue.pop();
        queue.pop();
    }
}
