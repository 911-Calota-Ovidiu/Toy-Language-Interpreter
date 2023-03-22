package Model;

import java.util.List;

public interface MyIlist<T> {
    void add(T elem);
    T pop() throws ADTException;
    boolean isEmpty();
    List<T> getList();
}
