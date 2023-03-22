package Model;

import java.util.*;

public class MyStack<T> implements MyIStack<T> {
    Stack<T> stack;
    public MyStack()
    {
        this.stack=new Stack<>();
    }
    public T pop() throws ADTException
    {
        return this.stack.pop();
    }
    public void push(T element)
    {
        this.stack.push(element);
    }
    public T peek()
    {
        return this.stack.peek();
    }
    public boolean isEmpty()
    {
        return this.stack.isEmpty();
    }
    public List<T> getReversed()
    {
        List<T> list= Arrays.asList((T[]) this.stack.toArray());
        Collections.reverse(list);
        return list;
    }
}
