package Model;

public interface Type {
    boolean equals(Type anothertype);
    Value defaultValue();
    Type deepcopy();
}
