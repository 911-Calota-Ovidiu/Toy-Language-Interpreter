package Model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyDictionary<T1,T2> implements MyIDictionary<T1,T2> {
    HashMap<T1,T2> dictionary;
    public MyDictionary()
    {
        this.dictionary=new HashMap<>();
    }
    public boolean isDefined(T1 key){
        return this.dictionary.containsKey(key);
    }
    public void put(T1 key, T2 value)
    {
        this.dictionary.put(key,value);
    }
    public T2 lookUp(T1 key)throws ADTException
    {
        if(!isDefined(key))
        {
            System.out.println("Undefined");
            return null;
        }
        return this.dictionary.get(key);
    }
    public void update(T1 key, T2 value)throws ADTException
    {
        if(!isDefined(key))
        {
            System.out.println("Undefined");
            return;
        }
        this.dictionary.put(key,value);
    }
    public Collection<T2> values()
    {
        return this.dictionary.values();
    }
    public void remove(T1 key)throws ADTException
    {
        if(!isDefined(key))
        {
            System.out.println("Undefined");
            return;
        }
        this.dictionary.remove(key);
    }
    public Set<T1> keySet()
    {
        return this.dictionary.keySet();
    }
    public Map<T1, T2> getContent()
    {
        return dictionary;
    }
    public String toString() {
        return this.dictionary.toString();
    }
    public MyIDictionary<T1, T2> deepCopy() throws ADTException
    {
        MyIDictionary<T1, T2> toReturn = new MyDictionary<>();
        for (T1 key: keySet())
            toReturn.put(key, lookUp(key));
        return toReturn;
    }
}

