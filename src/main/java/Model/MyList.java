package Model;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements MyIlist<T> {
     List<T> list;
     public MyList()
    {
        this.list=new ArrayList<T>();
    }
    public void add(T elem)
    {
        this.list.add(elem);
    }
    public T pop() throws ADTException
    {
        if(this.list.isEmpty())
            throw new ADTException("List is empty");
        return this.list.remove(0);

    }
    public boolean isEmpty()
    {
        return this.list.isEmpty();
    }
    public List<T> getList()
    {
        return this.list;
    }

    @Override
    public String toString() {
        return this.list.toString();
    }
}
