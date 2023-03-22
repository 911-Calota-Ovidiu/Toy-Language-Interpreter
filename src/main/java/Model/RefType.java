package Model;

public class RefType implements Type{
    Type inner;
    public RefType(Type inner)
    {
        this.inner=inner;
    }
    Type getInner(){return inner;}
    public boolean equals(Type anothertype)
    {
        if(anothertype instanceof RefType)
            return inner.equals(((RefType) anothertype).getInner());
        else return false;
    }
    public Value defaultValue()
    {
        return new RefValue(0,inner);
    }
    public Type deepcopy()
    {
        return new RefType(inner.deepcopy());
    }
    public String toString()
    {
        return String.format("Ref(%s)",inner);
    }
}
