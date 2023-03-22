package Model;

public class IntValue implements Value {
    int val;

    public IntValue(int v) {
        this.val = v;
    }

    public int getVal() {
        return val;
    }

    public String toString() {
        return String.format("%d",this.val);
    }

    public Type getType() {
        return new IntType();
    }
    public Value deepCopy()
    {
        return new IntValue(this.val);
    }
    public boolean equals(Object anothervalue)
    {
        if(!(anothervalue instanceof IntValue))
        {
            return false;
        }
        IntValue castValue=(IntValue) anothervalue;
        return this.val==castValue.val;
    }
}
