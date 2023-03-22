package Model;

public class StringType implements Type{
    public boolean equals(Type anothertype)
    {
        return anothertype instanceof StringType;
    }
    public Value defaultValue()
    {
        return new StringValue("");
    }
    public Type deepcopy()
    {
        return new StringType();
    }
    public String toString()
    {
        return "str";
    }
}
