package Model;

public class BoolValue implements Value{
    boolean value;
    public BoolValue(boolean value) {
        this.value = value;
    }
    public Type getType() {
        return new BoolType();
    }
    public boolean equals(Object anothervalue)
    {
        if(!(anothervalue instanceof BoolValue))
            return false;
        BoolValue castValue=(BoolValue) anothervalue;
        return this.value==castValue.value;
    }

    @Override
    public Value deepCopy() {
        return new BoolValue(value);
    }
    public boolean getValue()
    {
        return this.value;
    }
    public String toString()
    {
        return this.value ? "true":"false";
    }
}
