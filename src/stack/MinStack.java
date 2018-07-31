package stack;

import java.util.Stack;

/**
 * 实现一个带有取最小值min方法的栈，min方法将返回当前栈中的最小值。
 *　你实现的栈将支持push，pop 和 min 操作，所有操作要求都在O(1)时间内完成。
 *
 * 思路：用另一个栈来维护每一步对应的最小值
 */
public class MinStack {
    private final Stack<Integer> s1 = new Stack<>();
    private final Stack<Integer> s2 = new Stack<>(); //记录没插入（弹出）一个值后相应的最小值

    public void push(int number) {
        s1.push(number);
        if (s2.size() == 0 || number <= s2.peek() ) {
            s2.push(number); //当前最小值为value
        } else {
            s2.push(s2.peek());//当前最小值不变
        }
    }

    public int pop() {
        s2.pop();
        return s1.pop();
    }

    public int min() {
        return s2.peek();
    }
}
