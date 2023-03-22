package Model;

import java.util.HashMap;
import java.util.Set;

public class MyHeap implements MyIHeap{
    HashMap<Integer,Value> heap;
    Integer freeLocationValue;
    public MyHeap()
    {
        this.heap=new HashMap<>();
        this.freeLocationValue=1;
    }
    public int newValue() {
        freeLocationValue += 1;
        while (freeLocationValue == 0 || heap.containsKey(freeLocationValue))
            freeLocationValue += 1;
        return freeLocationValue;
    }
    public int getFreeValue()
    {
        return freeLocationValue;
    }
    public HashMap<Integer, Value> getContent()
    {
        return heap;
    }
    public void setContent(HashMap<Integer, Value> newMap)
    {
        this.heap=newMap;
    }
    public int add(Value value)
    {
        heap.put(freeLocationValue,value);
        Integer toReturn=freeLocationValue;
        freeLocationValue=newValue();
        return toReturn;
    }
    public void update(Integer position, Value value) throws ADTException
    {
        if(!heap.containsKey(position))
            throw new ADTException(String.format("%s is not part of the heap",position));
        heap.put(position,value);
    }
    public Value get(Integer position) throws ADTException
    {
        if(!heap.containsKey(position))
            throw new ADTException(String.format("%s is not part of the heap",position));
        return heap.get(position);
    }
    public boolean containsKey(Integer position)
    {
        return this.heap.containsKey(position);
    }
    public void remove(Integer key) throws ADTException
    {
        if(!heap.containsKey(key))
        {
            throw new ADTException(String.format("%s is not defined",key));
        }
        freeLocationValue=key;
        this.heap.remove(key);
    }
    public Set<Integer> keySet()
    {
        return heap.keySet();
    }
    public String toString()
    {
        return this.heap.toString();
    }
}
