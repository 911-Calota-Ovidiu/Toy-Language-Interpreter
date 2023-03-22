package Model;

public class IntType implements Type {
    public boolean equals(Type anothertype)
    {
        return anothertype instanceof IntType;
    }
    public Value defaultValue()
    {
        return new IntValue(0);
    }
    public Type deepcopy()
    {
        return new IntType();
    }
    public String toString()
    {
        return "int";
    }
}
