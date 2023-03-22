package Model;
;

;

public class RefValue implements Value {
    int address;
    Type locationType;
    public RefValue(int address,Type locationType)
    {
        this.address=address;
        this.locationType=locationType;
    }
    public Type getType()
    {
        return new RefType(locationType);
    }
    public int getAddress()
    {
        return address;
    }
    public Type getLocationType()
    {
        return locationType;
    }
    public Value deepCopy()
    {
        return new RefValue(address,locationType.deepcopy());
    }
    public String toString()
    {
        return String.format("(%d,%s)",address,locationType);
    }
}
