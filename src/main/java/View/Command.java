package View;

public abstract class Command {
    String key,desc;
    public Command(String key, String val)
    {
        this.key=key;
        this.desc=val;
    }
    public abstract void execute();
    public String getKey(){
        return this.key;
    }
    public String getDesc()
    {
        return this.desc;
    }
}
