package Model;

public class BoolType implements Type{
    public boolean equals(Type anothertype)
    {
        return anothertype instanceof BoolType;
    }
    public Value defaultValue()
    {
        return new BoolValue(false);
    }
    public Type deepcopy()
    {
        return new BoolType();
    }
    public String toString()
    {
        return "bool";
    }
}
