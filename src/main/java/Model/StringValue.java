package Model;

import java.util.Objects;

public class StringValue implements Value{
    String val;

    public StringValue(String s) {
        this.val = s;
    }

    public String getVal() {
        return val;
    }

    public String toString() {
        return val;
    }

    public Type getType() {
        return new StringType();
    }
    public Value deepCopy()
    {
        return new StringValue(this.val);
    }
    public boolean equals(Object anothervalue)
    {
        if(!(anothervalue instanceof IntValue))
        {
            return false;
        }
        StringValue castValue=(StringValue) anothervalue;
        return Objects.equals(this.val, castValue.val);
    }

}
